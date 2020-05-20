package fr.eni.ecole.troc_encheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class ModificationProfilServlet
 */
@WebServlet("/modifierProfil")
public class ModificationProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificationProfilServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if (session.getAttribute("sessionUtilisateur") == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/connexion");
		}else {

			request.getRequestDispatcher("/WEB-INF/jsp/modif_compte.jsp").forward(request, response);



		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		int noUtilisateur = ((Utilisateur) session.getAttribute("sessionUtilisateur")).getNumero();
		
		String pseudo = null;
		String nom = null ; 
		String prenom = null; 
		String email = null;
		String tel = null; 
		String rue = null; 
		String cp = null;
		String ville = null; 
		String mdp = null; 
		String mdpConf = null; 
		List<String> listeErreur=new ArrayList<>();

		
		
		
		pseudo = request.getParameter("pseudo");
		nom = request.getParameter("nom");
		prenom = request.getParameter("prenom");
		email = request.getParameter("email");
		tel = request.getParameter("tel");
		rue = request.getParameter("rue");
		cp = request.getParameter("cp");
		ville = request.getParameter("ville");
		mdp = request.getParameter("mdp");
		mdpConf = request.getParameter("mdpConf");

		

		Utilisateur util = new Utilisateur(noUtilisateur, pseudo,  nom,  prenom,  email,  tel,  rue, cp,  ville,  mdp);
		System.out.println(util);
		if (!mdp.equals(mdpConf)) {

			listeErreur.add("Veuillez saisir 2 fois le même mot de passe.");
		}	
		else	{

			try {
				EncheresManager.get().updateUtil(util);
			}

			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				listeErreur.add(e.getMessage());
			}
		}
		
		
		
		request.setAttribute("listeErreur", listeErreur);
		if(listeErreur.isEmpty()) {
			session.setAttribute("pseudo", pseudo);
			session.setAttribute("nom", nom);
			session.setAttribute("prenom", prenom);
			session.setAttribute("email", email);
			session.setAttribute("tel", tel);
			session.setAttribute("rue", rue);
			session.setAttribute("cp", cp);
			session.setAttribute("ville", ville);
			session.setAttribute("mdp", mdp);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/affichage_compte.jsp");
			rd.forward(request, response);	
		}else {

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/modif_compte.jsp");
		rd.forward(request, response);
		}
	}

}
