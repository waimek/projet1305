package fr.eni.ecole.troc_encheres.bll;

import java.util.Date;
import java.util.List;

import fr.eni.ecole.troc_encheres.bll.exceptions.BLLException;
import fr.eni.ecole.troc_encheres.bo.Categorie;
import fr.eni.ecole.troc_encheres.bo.Utilisateur;
import fr.eni.ecole.troc_encheres.bo.Vente;
import fr.eni.ecole.troc_encheres.dal.DAO;
import fr.eni.ecole.troc_encheres.dal.Factory;
import fr.eni.ecole.troc_encheres.dal.exceptions.DALException;
import fr.eni.ecole.troc_encheres.dal.jdbc.VenteDAOJdbcImpl;

public class EncheresManager {

	private static EncheresManager instance;
	private DAO<Utilisateur> utilDAO;
	private DAO<Vente> venteDAO;
	private DAO categorieDAO;
	// test push
	
	// test bean
	// guerzgyrpugrprqugr
	//Urgh Lucille - rgregregegesgesr

	public static EncheresManager get() {
		if (instance == null) {
			instance = new EncheresManager();
		}
		return instance;
	}

	private EncheresManager() {
		utilDAO = Factory.getUtilisateurDAO();
		venteDAO = Factory.getVenteDAO();
		categorieDAO = Factory.getCategorieDAO();
	}
//	Hello
	public void updateUtil(Utilisateur util) throws BLLException {
		try {
			validerUtil(util);
			utilDAO.update(util);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur update");
		}
	}

	public void addUtil(Utilisateur util) throws BLLException {
		try {
			validerUtil(util);
			utilDAO.insert(util);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur insert");
		}
	}

	public Utilisateur getUtil(int idUtil) throws BLLException {
		Utilisateur util=null;
		try {
		 util = (Utilisateur) utilDAO.selectById(idUtil);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur get");
		}
		return util;
	}

	public void deleteUtil(int idUtil) throws BLLException {
		try {
			
			utilDAO.delete(idUtil);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur delete");
		}
	}

	public void validerUtil(Utilisateur util) throws BLLException {
		boolean valide = true;
		StringBuffer sb = new StringBuffer();

		if (util == null) {
			throw new BLLException("equipe null");
		}
		// Les attributs des equipes sont obligatoires
		
		if (util.getPseudo().trim().length() ==0) {
			sb.append("Pseudo obligatoire.\n");
			valide = false;
		}
		if (util.getNom().trim().length() ==0) {
			sb.append("Nom obligatoire.\n");
			valide = false;
		}
		if (util.getPrenom().trim().length() ==0) {
			sb.append("Prenom obligatoire.\n");
			valide = false;
		}
		if (util.getEmail().trim().length() ==0) {
			sb.append("Email obligatoire.\n");
			valide = false;
		}
		if (util.getTel().trim().length() ==0) {
			sb.append("T�l�phone obligatoire.\n");
			valide = false;
		}
		if (util.getRue().trim().length() ==0) {
			sb.append("Rue obligatoire.\n");
			valide = false;
		}
		if (util.getCp().trim().length() ==0) {
			sb.append("Code postal obligatoire.\n");
			valide = false;
		}
		if (util.getVille().trim().length() ==0) {
			sb.append("Ville obligatoire.\n");
			valide = false;
		}
		if (util.getMdp().trim().length() ==0) {
			sb.append("Mot de passe obligatoire.\n");
			valide = false;
		}
		if (!valide) {
			throw new BLLException(sb.toString());
		}
	}
	
	/*************************************************************/
	/******************** GESTION VENTE **************************/ 
	/*************************************************************/
	
	// ajouter une vente
	public void addVente(Vente vente) throws BLLException {
		try {
			validerVente(vente);
			venteDAO.insert(vente);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur insert");
		}
	}
	
	public List<Vente> getVentes(){
		List<Vente> ventes = null;
		try {
			ventes = venteDAO.selectAll();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return ventes; 
	}
	public Vente getVente(int idVente){
		Vente vente = null;
		try {
			vente = venteDAO.selectById(idVente);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return vente; 
	}
	
	public void validerVente(Vente vente) throws BLLException {
		boolean valide = true;
		Date dateDuJour = new Date();
		StringBuffer sb = new StringBuffer();

		if (vente == null) {
			throw new BLLException("equipe null");
		}
		
		if (vente.getNomArticle().trim().length() ==0) {
			sb.append("Nom de l'article obligatoire.\n");
			valide = false;
		}
		if (vente.getDescription().trim().length() ==0) {
			sb.append("Description obligatoire.\n");
			valide = false;
		}
		if (vente.getCategorie().getLibelle().trim().length() ==0) {
			sb.append("Catégorie obligatoire.\n");
			valide = false;
		}
		if (vente.getMiseAPrix() != 0) {
			sb.append("La mise à prix doit être différent de 0.\n");
			valide = false;
		}
		if (vente.getDateFinEncheres().compareTo(dateDuJour) <= 0) {
			sb.append("La date de fin d'enchère doit être supérieur à la date du jour.\n");
			valide = false;
		}
		
		if (!valide) {
			throw new BLLException(sb.toString());
		}
	}
	
	/*************************************************************/
	/******************** GESTION ENCHERE ************************/ 
	/*************************************************************/
	
	// ajouter une enchere
	/*
	public void addEnchere(Enchere enchere) throws BLLException {
		try {
			validerVente(vente);
			venteDAO.insert(vente);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur insert");
		}
	}
	*/
	
	/*************************************************************/
	/******** GESTION DES LISTES D'ACHAT OU DE VENTE *************/ 
	/*************************************************************/
	
	// liste des ventes d'un utilisateur

	public List<Vente> getListVenteUtilisateur(int idUtilisateur) throws BLLException {
		List<Vente> listVentes =null;
		try {
			listVentes = ((VenteDAOJdbcImpl)venteDAO).selectByUtil(idUtilisateur);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur get");
		}
		return listVentes;
	}
/*	
	// liste des achats d'un utilisateur
<<<<<<< Updated upstream
//	public List<Vente> getListAchatUtilisateur(int idUtilisateur) throws BLLException {
//		List<Vente> achat = null;
//		try {
//			achat = ((VenteDAOJdbcImpl)venteDAO).selectAchatsByUtilisateur(idUtilisateur);
//		} catch (DALException e) {
//			e.printStackTrace();
//			throw new BLLException("Erreur get");
//		}
//		return achat;
//	}
<<<<<<< HEAD

=======
	public List<Vente> getListAchatUtilisateur(int idUtilisateur) throws BLLException {
		List<Vente> achat = null;
		try {
			achat = ((VenteDAOJdbcImpl)venteDAO).selectAchatsByUtilisateur(idUtilisateur);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur get");
		}
		return achat;
	}
*/

public List<Vente> getListFiltreRecherche(String nomArticle, int idArticle, int monNoUtilisateur, 
			int noCategorie, boolean mesVentes, boolean mesEncheresEnCours, boolean mesAcquisitions, 
			boolean autresEncheres) throws BLLException {
		List<Vente> listFiltreRecherche = null;
		try {
			listFiltreRecherche = ((VenteDAOJdbcImpl)venteDAO).selectByPlusieursChamps(nomArticle, idArticle, monNoUtilisateur, 
					noCategorie, mesVentes, mesEncheresEnCours, mesAcquisitions, autresEncheres);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur get");
		}
		return listFiltreRecherche;
	}
	
	public List<Categorie> getCategories(){
		List<Categorie> categories = null ; 
		try {
			categories = categorieDAO.selectAll();
		} catch (DALException e) {
			e.printStackTrace();
		}
		return categories;
	}

	
}
