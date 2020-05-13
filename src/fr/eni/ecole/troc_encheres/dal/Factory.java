package fr.eni.ecole.troc_encheres.dal;

import fr.eni.ecole.troc_encheres.dal.jdbc.CategorieDAOJdbcImpl;
import fr.eni.ecole.troc_encheres.dal.jdbc.EnchereDAOJdbcImpl;
import fr.eni.ecole.troc_encheres.dal.jdbc.RetraitDAOJdbcImpl;
import fr.eni.ecole.troc_encheres.dal.jdbc.UtilisateurDAOJdbcImpl;
import fr.eni.ecole.troc_encheres.dal.jdbc.VenteDAOJdbcImpl;

public class Factory {
	
	public static DAO getUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
	}
	
	public static DAO getCategorieDAO() {
		return new CategorieDAOJdbcImpl();
	}
	
	public static DAO getRetraitDAO() {
		return new RetraitDAOJdbcImpl();
	}
	
	public static DAO getEnchereDAO() {
		return new EnchereDAOJdbcImpl();
	}
	
	public static DAO getVenteDAO() {
		return new VenteDAOJdbcImpl();
	}
}
