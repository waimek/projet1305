package fr.eni.ecole.troc_encheres.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ecole.troc_encheres.bll.EncheresManager;
import fr.eni.ecole.troc_encheres.bll.exceptions.BLLException;
import fr.eni.ecole.troc_encheres.bo.Categorie;
import fr.eni.ecole.troc_encheres.bo.Vente;

/**
 * Servlet implementation class RechercheServlet
 */
@WebServlet("/RechercheServlet")
public class RechercheServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EncheresManager manager;
	private List<Categorie> listeCategories;
	
	public void init(){
		manager = EncheresManager.get();
		listeCategories = manager.getCategories();
	}
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("listeCategories", listeCategories);
		request.getRequestDispatcher("/WEB-INF/jsp/recherche.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// recuperation des elements de recherches de l'utilisateur
		// champs dispo dans le formulaire

		System.out.println("mesVentes : " + request.getParameter("mesVentes"));
		System.out.println("mesEncheresEnCours : " + request.getParameter("mesEncheresEnCours"));
		System.out.println("mesAcquisitions : " + request.getParameter("mesAcquisitions"));
		System.out.println("autresEncheres : " + request.getParameter("autresEncheres"));
		System.out.println("choixCategorie : " + request.getParameter("choixCategorie"));
		System.out.println("info saisie : " + request.getParameter("textRecherche"));
		
		// traitement des informations dans le filtre
		boolean mesAcquisitions = ("on".equals(request.getParameter("mesAcquisitions")))?true:false;
		boolean mesEncheresEnCours = ("on".equals(request.getParameter("mesEncheresEnCours")))?true:false;
		boolean mesVentes = ("on".equals(request.getParameter("mesVentes")))?true:false;
		boolean autresEncheres = ("on".equals(request.getParameter("autresEncheres")))?true:false;
		
		// traitement du choix de la/les categorie/s
		// si no categorie = 0 alors on selection l'ensemble des categories
		int noCategorie = Integer.parseInt(request.getParameter("choixCategorie"));
		
		// traitement du nom rechercher
		String recherche = request.getParameter("textRecherche").trim();
		
		// faire la recherche par rapport à l'utilisateur
		HttpSession session = request.getSession();
		// TODO gerer le cas si l'utilisateur n'a plus de session
		//int monNoUtilisateur = (int) session.getAttribute("utilisateur");
		// test avec un utilisateur en dur
		int monNoUtilisateur = 3;
		
		System.out.println("monNoUtilisateur : " + monNoUtilisateur);
		
		List<Vente> listeRecherche = null;
		try {
			listeRecherche = manager.getListFiltreRecherche(recherche, monNoUtilisateur, 
					noCategorie, mesVentes, mesEncheresEnCours, mesAcquisitions, autresEncheres);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		//System.out.println("listeRecherche : " + listeRecherche);
		
		request.setAttribute("listeRecherche", listeRecherche);
		
		
		request.setAttribute("listeCategories", listeCategories);
		request.getRequestDispatcher("/WEB-INF/jsp/recherche.jsp").forward(request, response);                
	}

}
