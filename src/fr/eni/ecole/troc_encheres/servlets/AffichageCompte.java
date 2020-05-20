package fr.eni.ecole.troc_encheres.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import fr.eni.ecole.troc_encheres.bll.EncheresManager;
import fr.eni.ecole.troc_encheres.bll.exceptions.BLLException;
import fr.eni.ecole.troc_encheres.bo.Utilisateur;

/**
 * Servlet implementation class AffichageCompte
 * @author Lucille/Dominika
 */
@WebServlet("/AffichageCompte")
public class AffichageCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE = "/WEB-INF/jsp/affichage_compte.jsp";
	private EncheresManager manager;
	int noUtilisateur =-1;
	Utilisateur util = null;

	@Override
	public void init() throws ServletException {
		manager = EncheresManager.get();
	}
	
	
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AffichageCompte() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// verifier si la session existe
		HttpSession session = request.getSession();
		if (session.getAttribute("sessionUtilisateur") == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/connexion");
		}else {
			noUtilisateur = ((Utilisateur) session.getAttribute("sessionUtilisateur")).getNumero();


			try {
				util = manager.getUtil(noUtilisateur);
				
				session.setAttribute("pseudo", util.getPseudo());
				session.setAttribute("nom", util.getNom());
				session.setAttribute("prenom", util.getPrenom());
				session.setAttribute("email", util.getEmail());
				session.setAttribute("tel", util.getTel());
				session.setAttribute("rue", util.getRue());
				session.setAttribute("cp", util.getCp());
				session.setAttribute("ville", util.getVille());
				session.setAttribute("mdp", util.getMdp());
				session.setAttribute("pseudo", util.getPseudo());
				request.getRequestDispatcher(VUE).forward(request, response);
				
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		String cp = request.getParameter("cp");
		String ville = request.getParameter("ville");
		
		
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

}
