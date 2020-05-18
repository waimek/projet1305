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

import fr.eni.ecole.troc_encheres.bll.EncheresManager;
import fr.eni.ecole.troc_encheres.bll.exceptions.BLLException;

/**
 * @Author Dominika
 * Servlet implementation class NewAccountServlet
 */
@WebServlet("/nouveauCompte") 
public class NouveauCompteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Affiche la page de création du compte (nouveauCompte.jsp)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/nouveauCompte.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * Récupère les données saisies dans le formulaire, 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

		//lecture repas
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


		if (!mdp.equals(mdpConf)) {

			listeErreur.add("Entrez 2 fois le même mot de passe.");
		}	
		else	{

			try {
				EncheresManager.get().addUtilFromServlet(pseudo,  nom,  prenom,  email,  tel,  rue, cp,  ville,  mdp);
			}

			catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				listeErreur.add(e.getMessage());
			}
		}
		
		
		request.setAttribute("listeErreur", listeErreur);
		if(listeErreur.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/page_connexion.jsp");
			rd.forward(request, response);	
		}else {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/nouveauCompte.jsp");
		rd.forward(request, response);
		}
	}

}
