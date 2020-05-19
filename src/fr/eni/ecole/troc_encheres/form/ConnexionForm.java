package fr.eni.ecole.troc_encheres.form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import fr.eni.ecole.troc_encheres.bo.Utilisateur;
import fr.eni.ecole.troc_encheres.dal.Factory;
import fr.eni.ecole.troc_encheres.dal.exceptions.DALException;
import fr.eni.ecole.troc_encheres.dal.jdbc.UtilisateurDAOJdbcImpl;

public final class ConnexionForm {
	private static final String PSEUDO = "identifiant";
	private static final String MDP = "mdp";
	private static final boolean CHOIX = true;

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
		String pseudo = getValeurChamp(request, PSEUDO);
		System.out.println("Le pseudo est : " + pseudo);
		String mdp = getValeurChamp(request, MDP);
		System.out.println("Le mdp est : " + mdp);
		String email = getValeurChamp(request, PSEUDO);
		System.out.println("Le mail est : " + email);

		Utilisateur utilisateur = new Utilisateur();
		UtilisateurDAOJdbcImpl daoUtilisateur;
		/* Si le champs choisi est pseudo */
		if (CHOIX) {
			/* Validation du champ pseudo. */
			try {
				validationPseudo(pseudo);
			} catch (Exception e) {
				setErreur(PSEUDO, e.getMessage());
			}
			utilisateur.setPseudo(pseudo);
		} else if (CHOIX == false) {
			try {
				validationEmail(email);
			} catch (Exception e) {
				setErreur(PSEUDO, e.getMessage());
			}
			utilisateur.setEmail(email);
		}

		/* Validation du champ mot de passe. */
		try
		{
			validationMotDePasse(mdp);
		} catch (Exception e) {
			setErreur(MDP, e.getMessage());
		}
		utilisateur.setMdp(mdp);

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
			if (CHOIX) {

				/* On set l'id à l'utilisateur */
				utilisateur.setNumero(daoUtilisateur.selectIdByUser(pseudo));
			} else {

				utilisateur.setNumero(daoUtilisateur.selectIdByUserMail(email));
			}
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("l'id de l'utilisateur : " + utilisateur.getNumero());
		System.out.println("Fin de la procédure de connexion");
		return utilisateur;
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
		System.out.println("Ce qu'il y a dans ma variable " + emailOK);
		if (emailOK.equals(email)) {
			resultat = "Le pseudo est bon.";
			System.out.println("Le pseudo est bon");
		} else {
			resultat = "Le pseudo n'est pas bon. Veuillez recommencer";
			System.out.println("Le pseudo ne correspond pas");
		}
	}

	/**
	 * Valide le mot de passe saisi.
	 */
	private void validationMotDePasse(String mdp) throws Exception {
		UtilisateurDAOJdbcImpl daoUtilisateur;
		String mdpOK = null;
		if (mdp != null) {
			if (mdp.length() < 3) {
				throw new Exception("Le mot de passe doit contenir au moins 3 caractères.");
			}
		} else {
			throw new Exception("Merci de saisir votre mot de passe.");
		}
//		Test de correspondance
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
