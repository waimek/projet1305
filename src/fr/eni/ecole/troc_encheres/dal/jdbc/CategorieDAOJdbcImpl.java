package fr.eni.ecole.troc_encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.troc_encheres.bo.Categorie;
import fr.eni.ecole.troc_encheres.dal.ConnectionProvider;
import fr.eni.ecole.troc_encheres.dal.DAO;
import fr.eni.ecole.troc_encheres.dal.exceptions.DALException;
/*
 * @author Edouard
 */

public class CategorieDAOJdbcImpl implements DAO<Categorie>{

	@Override
	public void insert(Categorie obj) throws DALException {
		
	}

	@Override
	public void update(Categorie obj) throws DALException {
		
	}

	@Override
	public Categorie selectById(int idCat) throws DALException {
		Categorie categorie= null;
        Connection con = null;
        Statement stmt = null;
        try {
            con = ConnectionProvider.getConnection();
            stmt = con.createStatement();
            PreparedStatement query = con.prepareStatement("select * from categories where no_categorie = ?");
            query.setInt(1, idCat);
            ResultSet rs = query.executeQuery();
            if (rs.next()) {
                categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
            }
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
                throw new DALException("Erreur closeResult");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                con.close();
                stmt.close();
            } catch (Exception e) {
                throw new DALException("Erreur fermeture");
            }
        }
        return categorie;
	}

	@Override
	public List<Categorie> selectAll() throws DALException {
		   List<Categorie> categories = new ArrayList<>();
	        Connection con = null;
	        Statement stmt = null;
	        try {
	            con = ConnectionProvider.getConnection();
	            stmt = con.createStatement();
	            ResultSet rs = stmt.executeQuery("select * from categories order by libelle asc");
	            Categorie categorie = null;
	            while (rs.next()) {
	            	categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
	                categories.add(categorie);
	            }
	        } catch (SQLException throwables) {
	            throwables.printStackTrace();
	        } finally {
	            try {
	                stmt.close();
	                con.close();

	            } catch (Exception e) {
	                throw new DALException("Erreur fermeture");
	            }
	        }
	        return categories;
	}

	@Override
	public void delete(int idObj) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int selectIdByUser(String pseudo) throws DALException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectIdByTel(String tel) throws DALException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectIdByEmail(String email) throws DALException {
		// TODO Auto-generated method stub
		return 0;
	}


	

	

}
