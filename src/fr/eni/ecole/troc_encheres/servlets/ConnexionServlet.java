package fr.eni.ecole.troc_encheres.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ecole.troc_encheres.bo.Utilisateur;
import fr.eni.ecole.troc_encheres.form.ConnexionForm;

/**
 * Servlet implementation class ServletDeConnexion
 * @author Lucille
 */
@WebServlet("/connexion")
public class ConnexionServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ATT_USER = "utilisateur";
	public static final String ATT_FORM = "form";
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String VUE = "/WEB-INF/jsp/page_connexion.jsp";
	public static final String REDIRECTION = "/RechercheServlet";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Affichage de la page de connexion */
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Préparation de l'objet formulaire */
		ConnexionForm form = new ConnexionForm();

		/* Traitement de la requête et récupération du bean en résultant */
		Utilisateur utilisateur = form.connecterUtilisateur(request);

		/* Récupération de la session depuis la requête */
		HttpSession session = request.getSession();

		/**
		 * Si aucune erreur de validation n'a eu lieu, alors ajout du bean Utilisateur à
		 * la session, sinon suppression du bean de la session.
		 */
		if (form.getErreurs().isEmpty()) {
			session.setAttribute(ATT_SESSION_USER, utilisateur);
			/* Stockage du formulaire et du bean dans l'objet request */
			request.setAttribute(ATT_FORM, form);
			request.setAttribute(ATT_USER, utilisateur);

			response.sendRedirect(getServletContext().getContextPath() + REDIRECTION);
		} else {
			session.setAttribute(ATT_SESSION_USER, null);
			request.setAttribute(ATT_FORM, form);
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		}

	}
}
