package fr.eni.ecole.troc_encheres.bll;

import java.util.Date;
import java.util.List;

import fr.eni.ecole.troc_encheres.bll.exceptions.BLLException;
import fr.eni.ecole.troc_encheres.bo.Categorie;
import fr.eni.ecole.troc_encheres.bo.Enchere;
import fr.eni.ecole.troc_encheres.bo.Utilisateur;
import fr.eni.ecole.troc_encheres.bo.Vente;
import fr.eni.ecole.troc_encheres.dal.DAO;
import fr.eni.ecole.troc_encheres.dal.Factory;
import fr.eni.ecole.troc_encheres.dal.exceptions.DALException;
import fr.eni.ecole.troc_encheres.dal.jdbc.EnchereDAOJdbcImpl;
import fr.eni.ecole.troc_encheres.dal.jdbc.VenteDAOJdbcImpl;
/*
 * @author Edouard / Matthieu
 * 
 */
public class EncheresManager {

	private static EncheresManager instance;
	private DAO<Utilisateur> utilDAO;
	private DAO<Vente> venteDAO;
	private DAO<Categorie> categorieDAO;
	private DAO<Enchere> enchereDAO;

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
		enchereDAO = Factory.getEnchereDAO();
	}

	public void updateUtil(Utilisateur util) throws BLLException {
		try {
			validerUtil(util);
			utilDAO.update(util);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur update");
		}
	}

	
	/*
	 * @author Dominika
	 * méthode addUtilFromServlet permet créer un nouveau compte à partir d'un formulaire
	 */
	public void addUtilFromServlet( String pseudo, String nom, String prenom, String email, String tel, String rue,
			String cp, String ville, String mdp ) throws BLLException {
		
		// construction utilisateur depuis la servlet
		Utilisateur util = new Utilisateur( pseudo,  nom,  prenom,  email,  tel,  rue, cp,  ville,  mdp);
		try {
			validerPseudo(pseudo);
			validerTel(tel);
			validerEmail(email);
			
		} catch (DALException e) {
			// TODO: handle exception
		}
		
		// Ajout de l'utilisateur 
		addUtil(util);

	}
	/*
	 * @author Edouard
	 * ajout d'un nouveau utilisateur dans la BDD
	 */
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
	
	/*
	 * author Dominika
	 * méthode qui permet de vérifier si le pseudo est déjà utilisé dans la base de données 
	 */
	public void validerPseudo(String pseudo) throws BLLException, DALException{
		Utilisateur util = null;
		util = utilDAO.selectByPseudo( pseudo);
		
		if (util != null) {
			throw new BLLException("Pseudo déjà utilisé");
		}
	}
	
	/*
	 * author Dominika
	 * méthode qui permet de vérifier si le numéro de téléphone est déjà utilisé dans la base de données 
	 */
	public void validerTel(String tel) throws BLLException, DALException{
		Utilisateur util = null;
		util = utilDAO.selectByTel(tel);
		
		if (util != null) {
			throw new BLLException("Numéro de téléphone déjà utilisé");
		}
	}
	
	/*
	 * author Dominika
	 * méthode qui permet de vérifier si l'adresse email est déjà utilisé dans la base de données 
	 */
	public void validerEmail(String tel) throws BLLException, DALException{
		Utilisateur util = null;
		util = utilDAO.selectByEmail(tel);
		
		if (util != null) {
			throw new BLLException("Adresse email déjà utilisé");
		}
	}
	
	/*
	 * Validation des attributs d'utilisateur
	 */
	public void validerUtil(Utilisateur util) throws BLLException {
		boolean valide = true;
		StringBuffer sb = new StringBuffer();

		if (util == null) {
			throw new BLLException("utilisateur null");
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
			sb.append("Téléphone obligatoire.\n");
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
	
	public void annulerVente(int idVente) throws BLLException{
		try {
			venteDAO.delete(idVente);
		}catch(DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur annulation vente");
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
			throw new BLLException("vente null");
		}

		if (vente.getNomArticle().trim().length() ==0) {
			sb.append("Nom de l'article obligatoire.\n");
			valide = false;
		}
		if (vente.getDescription().trim().length() ==0) {
			sb.append("Description obligatoire.\n");
			valide = false;
		}
		if (vente.getCategorie() ==null) {
			sb.append("Catégorie obligatoire.\n");
			valide = false;
		}
		if (vente.getMiseAPrix() == 0) {
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


	public void encherir(Enchere enchere) throws BLLException {
		try {
			validerEnchere(enchere);
			validerVente(enchere.getVente());
			venteDAO.update(enchere.getVente());
			Utilisateur util = enchere.getUtil();
			util.setCredit(util.getCredit()-enchere.getVente().getPrixVente());
			utilDAO.update(util);
			enchereDAO.insert(enchere);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur insert");
		}
	}
	public Enchere getDerniereEnchere(int noVente) throws BLLException {
		Enchere enchere = null;
		try {
			enchere =  ((EnchereDAOJdbcImpl) enchereDAO).getDerniereEnchere(noVente);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return enchere;
	}
	/* 
	public void supprimerDerniereEnchere(int noVente) throws BLLException{
		try {
			Enchere derniereEnchere = ((EnchereDAOJdbcImpl) enchereDAO).getDerniereEnchere(noVente);
			Utilisateur utilDerniereEnchere = derniereEnchere.getUtil();
			Vente vente = venteDAO.selectById(noVente);
			utilDerniereEnchere.setCredit(utilDerniereEnchere.getCredit()+vente.getPrixVente());
			((EnchereDAOJdbcImpl) enchereDAO).supprimerDerniereEnchere(noVente);
			derniereEnchere =  ((EnchereDAOJdbcImpl) enchereDAO).getDerniereEnchere(noVente);
			vente.setPrixVente(derniereEnchere);
			int prixVente = vente.getPrixVente();
			
		}catch(DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur suppression derniere enchere");
		}
		
	}*/
	
	public void validerEnchere(Enchere enchere) throws BLLException {
		boolean valide = true;
		StringBuffer sb = new StringBuffer();

		if (enchere == null) {
			throw new BLLException("enchère null");
		}

		if (enchere.getDateEnchere()==null) {
			sb.append("Date obligatoire.\n");
			valide = false;
		}
		if (enchere.getVente()==null) {
			sb.append("Description obligatoire.\n");
			valide = false;
		}
		if (enchere.getUtil() == null) {
			sb.append("Utilisateur obligatoire.\n");
			valide = false;
		}
		if (!valide) {
			throw new BLLException(sb.toString());
		}
	}

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

	public List<Vente> getListFiltreRecherche(String nomArticle, int monNoUtilisateur, 
			int noCategorie, boolean mesVentes, boolean mesEncheresEnCours, boolean mesAcquisitions, 
			boolean autresEncheres) throws BLLException {
		List<Vente> listFiltreRecherche = null;
		try {
			listFiltreRecherche = ((VenteDAOJdbcImpl)venteDAO).selectByPlusieursChamps(nomArticle, monNoUtilisateur, 
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
	public Categorie getCategorie(int noCategorie) throws BLLException{
		Categorie categorie = null;
		try{
			categorie = categorieDAO.selectById(noCategorie);
		}catch (DALException e ) {
			e.printStackTrace();
			throw new BLLException("Erreur selectById categorie");
		}
		return categorie;
	}

}
