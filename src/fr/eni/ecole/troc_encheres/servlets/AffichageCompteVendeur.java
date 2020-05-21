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
import fr.eni.ecole.troc_encheres.bo.Utilisateur;
import fr.eni.ecole.troc_encheres.bo.Vente;

/**
 * Servlet implementation class AffichageCompteVendeur LUCILLE
 */
@WebServlet("/AffichageCompteVendeur")
public class AffichageCompteVendeur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE = "/WEB-INF/jsp/test_lien_compte_autre.jsp";
	private EncheresManager manager;
	int idVendeur =-1;
	Utilisateur vendeur = null;
	private List<Vente> listeVentes;

	@Override
	public void init() throws ServletException {
		manager = EncheresManager.get();
		listeVentes = manager.getVentes();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AffichageCompteVendeur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameterMap().containsKey("noUtilisateur")) {
			idVendeur = Integer.parseInt(request.getParameter("noUtilisateur"));
		}
			try {
				vendeur = manager.getUtil(idVendeur);
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("pseudo", vendeur.getPseudo());
			request.setAttribute("nom", vendeur.getNom());
			request.setAttribute("prenom", vendeur.getPrenom());
			request.setAttribute("email", vendeur.getEmail());
			request.setAttribute("tel", vendeur.getTel());
			request.setAttribute("rue", vendeur.getRue());
			request.setAttribute("cp", vendeur.getCp());
			request.setAttribute("ville", vendeur.getVille());
			request.setAttribute("mdp", vendeur.getMdp());
			request.setAttribute("credit", vendeur.getCredit());
			
			request.getRequestDispatcher(VUE).forward(request, response);
			

		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
