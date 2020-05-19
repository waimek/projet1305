package fr.eni.ecole.troc_encheres.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import fr.eni.ecole.troc_encheres.bo.Enchere;
import fr.eni.ecole.troc_encheres.bo.Retrait;
import fr.eni.ecole.troc_encheres.bo.Utilisateur;
import fr.eni.ecole.troc_encheres.bo.Vente;

/**
 * Servlet implementation class GestionVenteServlet
 * 
 * @author Edouard
 */
@WebServlet("/GestionVenteServlet")
public class GestionVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EncheresManager manager;
	private List<Categorie> listeCategories;

	public void init() {
		manager = EncheresManager.get();
		listeCategories = manager.getCategories();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = "";
		if (request.getParameterMap().containsKey("action")) {
			action = request.getParameter("action");
		}
		if (action.equals("nouvelleVente")) {
			request.setAttribute("listeCategories", listeCategories);
			request.getRequestDispatcher("/WEB-INF/jsp/nouvelle_vente.jsp").forward(request, response);
		}
		if (action.equals("detailsVente")) {
			int noVente = Integer.parseInt(request.getParameter("noVente"));
			Vente vente = manager.getVente(noVente);

			HttpSession session = request.getSession();
			session = request.getSession();
			String page = "";
			if (vente.getUtil().getNumero() == ((Utilisateur) session.getAttribute("sessionUtilisateur")).getNumero()) {
				page = "/WEB-INF/jsp/vente.jsp";
			} else {
				page = "/WEB-INF/jsp/enchere.jsp";
			}
			if (vente.getDateFinEncheres().compareTo(new Date()) > 0) {
				Enchere derniereEnchere = null;
				try {
					derniereEnchere = manager.getDerniereEnchere(vente.getNumero());

				} catch (BLLException e) {
					e.printStackTrace();
				}
				if (derniereEnchere != null) {
					request.setAttribute("acheteur", derniereEnchere.getUtil());
				}
			}
			request.setAttribute("vente", vente);
			request.getRequestDispatcher(page).forward(request, response);
		}
		if (action.equals("annulerVente")) {
			try {
				manager.annulerVente(Integer.parseInt(request.getParameter("noVente")));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/*
		 * if(action.equals("annulerEnchere")) { try {
		 * manager.supprimerDerniereEnchere(Integer.parseInt(request.getParameter(
		 * "noVente"))); }catch(Exception e) { e.printStackTrace(); } }
		 * if(action.equals("validerRetrait")) {
		 * 
		 * }
		 */
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = "";
		if (request.getParameterMap().containsKey("action")) {
			action = request.getParameter("action");
		}
		if (action.equals("nouvelleVente")) {
			try {
				String nomArticle = request.getParameter("article");
				HttpSession session = request.getSession();
				String description = request.getParameter("description");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date dateFinEncheres = sdf.parse(request.getParameter("dateFinEncheres"));
				int miseAPrix = Integer.parseInt(request.getParameter("miseAPrix"));
				int prixVente = 0;
				Utilisateur util = (Utilisateur) session.getAttribute("sessionUtilisateur");
				int noCategorie = Integer.parseInt(request.getParameter("categorie"));
				Categorie categorie = manager.getCategorie(noCategorie);
				Vente vente = new Vente(nomArticle, description, dateFinEncheres, miseAPrix, prixVente, util,
						categorie);
				vente.setRetrait(new Retrait(vente, request.getParameter("rue"), request.getParameter("ville"),
						request.getParameter("codePostal")));
				manager.addVente(vente);
				request.setAttribute("vente", vente);
				request.getRequestDispatcher("/WEB-INF/jsp/vente.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equals("encherir")) {
			try {
				int montant = Integer.parseInt(request.getParameter("proposition"));
				int noVente = Integer.parseInt(request.getParameter("noVente"));
				HttpSession session = request.getSession();
				Vente vente = manager.getVente(noVente);
				Utilisateur util = (Utilisateur) session.getAttribute("sessionUtilisateur");
				System.out.println(util);
				Date date = new Date();
				String alert = "";
				if (util.getCredit() <= montant) {
					if (montant > vente.getPrixVente()) {
						vente.setPrixVente(montant);
						manager.encherir(new Enchere(date, util, vente));
						alert = "<div class=\"alert alert-success\" role=\"alert\">Merci pour votre enchère </div>";
					} else {
						alert = "<div class=\"alert alert-danger\" role=\"alert\">Vous ne pouvez pas proposer un montant inférieur à la meilleure offre</div>";
					}
				}else {
					alert = "<div class=\"alert alert-danger\" role=\"alert\">Vous n'avez pas assez de points</div>";
				}
				request.setAttribute("alert", alert);
				request.setAttribute("vente", vente);
				request.getRequestDispatcher("/WEB-INF/jsp/enchere.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
