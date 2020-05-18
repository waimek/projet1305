package fr.eni.ecole.troc_encheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ecole.troc_encheres.bo.Utilisateur;

/**
 * Servlet implementation class TestCheminServlet
 */
@WebServlet("/TestCheminServlet")
public class TestCheminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestCheminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/some_redirection.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/SomeRedirection.jsp").forward(request, response);
	}
	protected void traitement(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//		String pseudo = request.getParameter("pseudo");
//		String mdp = request.getParameter("mdp");
//
//		Utilisateur utilisateur = new Utilisateur(pseudo, mdp);
//		HttpSession session = request.getSession();
//		session.setAttribute("utilisateur", utilisateur);
//		
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/some_redirection.jsp");
//		try {
//			dispatcher.forward(request, response);
//		} catch (ServletException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
