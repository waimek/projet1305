package fr.eni.ecole.troc_encheres.form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import fr.eni.ecole.troc_encheres.bo.Utilisateur;
import fr.eni.ecole.troc_encheres.dal.Factory;
import fr.eni.ecole.troc_encheres.dal.exceptions.DALException;
import fr.eni.ecole.troc_encheres.dal.jdbc.UtilisateurDAOJdbcImpl;

public final class ConnexionForm {
	private static final String IDENTIFIANT = "identifiant";
	private static final String TEXTMDP = "textMdp";

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public Utilisateur connecterUtilisateur(HttpServletRequest request) {
		System.out.println("Début de la procédure de connexion");
		/* Récupération des champs du formulaire */
		String patternRegexMail = "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)";
		String variableIdentifiant = getValeurChamp(request, IDENTIFIANT);
		String email = null;
		String pseudo = null;

		if (variableIdentifiant.matches(patternRegexMail)) {
			email = variableIdentifiant;
		} else {
			pseudo = variableIdentifiant;
		}
		System.out.println("Le mail est : " + email);

		System.out.println("Le pseudo est : " + pseudo);

		String mdp = getValeurChamp(request, TEXTMDP);
		System.out.println("Le mdp est : " + mdp);

		Utilisateur utilisateur = new Utilisateur();
		UtilisateurDAOJdbcImpl daoUtilisateur;
		/* Si le champs choisi est pseudo */
		if (variableIdentifiant.equals(pseudo)) {
			/* Validation du champ pseudo. */
			try {
				validationPseudo(pseudo);
				utilisateur.setPseudo(pseudo);
			} catch (Exception e) {
				setErreur(pseudo, e.getMessage());
			}
			// utilisateur.setPseudo était là
		} else if (variableIdentifiant.equals(email)) {
			try {
				validationEmail(email);
				utilisateur.setEmail(email);
			} catch (Exception e) {
				setErreur(email, e.getMessage());
			}
			// utilisateur.setEmail était là
		}

		/* Validation du champ mot de passe. */
//		Identification avec email OU identification avec pseudo
		if (variableIdentifiant.equals(pseudo)) {
			/*
			 * Si je me connecte avec un pseudo, il faut que j'utilise la méthode de
			 * connexion avec le pseudo
			 */
			validationCouplePseudoMdp(pseudo, mdp);
			utilisateur.setMdp(mdp);
		} else {
			/* Sinon, j'utilise la methode avec email */
			try {
				validationCoupleEmailMdp(email, mdp);
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			utilisateur.setMdp(mdp);

		}

//		try
//		{
//			validationMotDePasse(mdp);
//			utilisateur.setMdp(mdp);
//		} catch (Exception e) {
//			setErreur(TEXTMDP, e.getMessage());
//		}
		// utilisateur.setMdp était là

		/* Initialisation du résultat global de la validation. */
		if (erreurs.isEmpty()) {
			resultat = "Succès de la connexion.";
		} else {
			resultat = "Échec de la connexion.";
//			Il faut sortir de là si l'utilisateur n'existe pas
			System.out.println("Voici l'erreur : " + erreurs.toString());
		}

		/* Récupération id utilisateur */
		/* Etape 1 : Récupération de la dao grâce à la factory */
		daoUtilisateur = (UtilisateurDAOJdbcImpl) Factory.getUtilisateurDAO();

		/* On utilise la DAO pour récupérer l'id à partir du pseudo */
		try {
			if (variableIdentifiant.equals(pseudo)) {

				/* On set l'id à l'utilisateur */
				utilisateur.setNumero(daoUtilisateur.selectIdByUser(pseudo));
			} else {

				utilisateur.setNumero(daoUtilisateur.selectIdByEmail(email));
			}
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("l'id de l'utilisateur : " + utilisateur.getNumero());
		System.out.println("Fin de la procédure de connexion");
		return utilisateur;
	}

	private void validationCoupleEmailMdp(String email, String mdp) throws DALException {
		UtilisateurDAOJdbcImpl daoUtilisateur;
		int emailMdpOk;

//		1)	Récupération daofactory + utilisation procédure et requetage dessus
		daoUtilisateur = (UtilisateurDAOJdbcImpl) Factory.getUtilisateurDAO();
		emailMdpOk = daoUtilisateur.validationMdpByEmail(email, mdp);
		System.out.println("Test couple email mdp " + emailMdpOk);
		

	}

	private void validationCouplePseudoMdp(String pseudo, String mdp) {
		UtilisateurDAOJdbcImpl daoUtilisateur;
		String pseudoMdpOk;
		
//		1)	Récupération daofactory + utilisation procédure et requetage dessus
		daoUtilisateur = (UtilisateurDAOJdbcImpl) Factory.getUtilisateurDAO();
		pseudoMdpOk = daoUtilisateur.validationMdpByPseudo(pseudo, mdp);
		System.out.println("Test couple pseudo mdp " + pseudoMdpOk);
	}

	/**
	 * 
	 * Valide le pseudo saisi
	 */
	private void validationPseudo(String pseudo) throws Exception {
		UtilisateurDAOJdbcImpl daoUtilisateur;
		String pseudoOk = null;

//		Vérifie si le pseudo est null
		if (pseudo == null) {
			throw new Exception("Merci de saisir un pseudo valide.");
		}

//		Vérifier si le pseudo existe dans la base
//		1)	Récupération daofactory + utilisation procédure et requetage dessus
		daoUtilisateur = (UtilisateurDAOJdbcImpl) Factory.getUtilisateurDAO();

		pseudoOk = daoUtilisateur.pseudoExist(pseudo);
		System.out.println("Ce qu'il y a dans ma variable " + pseudoOk);
		if (pseudoOk.equals(pseudo)) {
			resultat = "Le pseudo est bon.";
			System.out.println("Le pseudo est bon");
		} else {
			resultat = "Le pseudo n'est pas bon. Veuillez recommencer";
			System.out.println("Le pseudo ne correspond pas");
		}
	}

	/**
	 * Valide le email saisi
	 */
	private void validationEmail(String email) throws Exception {
		UtilisateurDAOJdbcImpl daoUtilisateur;
		String emailOK = null;
		if (email == null) {
			throw new Exception("Merci de saisir un email valide.");
		}
//		Vérifier si le mail existe dans la base
//		Récupération daofactory + utilisation procédure et requetage dessus
		daoUtilisateur = (UtilisateurDAOJdbcImpl) Factory.getUtilisateurDAO();

		emailOK = daoUtilisateur.emailExist(email);
		System.out.println("Trace de email " + emailOK);
//		System.out.println("Ce qu'il y a dans ma variable " + emailOK);
		if (emailOK.equals(email)) {
			resultat = "Le mail est bon.";
			System.out.println("Le mail est bon");
		} else {
			resultat = "Le mail n'est pas bon. Veuillez recommencer";
			System.out.println("Le mail ne correspond pas");
		}
	}

	/**
	 * Valide le mot de passe saisi.
	 */
	private void validationMotDePasse(String mdp) throws Exception {
//		UtilisateurDAOJdbcImpl daoUtilisateur;
//		String mdpOK = null;
		if (mdp != null) {
			if (mdp.length() < 3) {
				throw new Exception("Le mot de passe doit contenir au moins 3 caractères.");
			}
		} else {
			throw new Exception("Merci de saisir votre mot de passe.");
		}
//		Test de correspondance
//		daoUtilisateur = (UtilisateurDAOJdbcImpl) Factory.getUtilisateurDAO();
//		mdpOK = daoUtilisateur.mdpExist(mdp);
//		Pour le test de mot de passe, il faut des couples [pseudo, mdp] ou [email, mdp]
	}

	/*
	 * Ajoute un message correspondant au champ spécifié à la map des erreurs.
	 */
	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}

	/*
	 * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
	 * sinon.
	 */
	private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur;
		}
	}
}
