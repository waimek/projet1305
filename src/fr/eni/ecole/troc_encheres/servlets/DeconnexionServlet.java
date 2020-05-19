package fr.eni.ecole.troc_encheres.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DeconnexionServlet
 * @author Lucille
 */
@WebServlet("/deconnexion")
public class DeconnexionServlet extends HttpServlet {
    public static final String REDIRECTION = "/connexion";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Récupération et destruction de la session en cours */
        HttpSession session = request.getSession();
        session.invalidate();

        /* Affichage de la page de connexion */
        this.getServletContext().getRequestDispatcher( REDIRECTION ).forward( request, response );
    }

}
