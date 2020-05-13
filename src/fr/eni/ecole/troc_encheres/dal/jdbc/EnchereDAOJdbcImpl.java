package fr.eni.ecole.troc_encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import fr.eni.ecole.troc_encheres.bo.Enchere;
import fr.eni.ecole.troc_encheres.dal.ConnectionProvider;
import fr.eni.ecole.troc_encheres.dal.DAO;
import fr.eni.ecole.troc_encheres.dal.exceptions.DALException;

public class EnchereDAOJdbcImpl implements DAO<Enchere>{

	@Override
	public void insert(Enchere enchere) throws DALException {
		Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionProvider.getConnection();
            stmt = con.prepareStatement("insert into encheres (date_enchere, no_utilisateur, no_vente) values (?,?,?)");
            stmt.setDate(1,(Date)enchere.getDateEnchere());
            stmt.setInt(2, enchere.getUtil().getNumero());
            stmt.setInt(2, enchere.getVente().getNumero());
            int rows = stmt.executeUpdate();

            if (rows != 1) {
                throw new DALException("Erreur insert");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DALException("Erreur insert");
        } finally {
            try {
                con.close();
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
                throw new DALException("Erreur close");
            }
        }
		
	}

	@Override
	public void update(Enchere obj) throws DALException {
		
	}

	@Override
	public Enchere selectById(int idObj) throws DALException {
		return null;
	}

	@Override
	public List<Enchere> selectAll() throws DALException {
		return null;
	}

	@Override
	public void delete(int idObj) throws DALException {
		
	}

}
