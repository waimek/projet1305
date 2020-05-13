package fr.eni.ecole.troc_encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import fr.eni.ecole.troc_encheres.bo.Utilisateur;
import fr.eni.ecole.troc_encheres.dal.exceptions.DALException;
import fr.eni.ecole.troc_encheres.dal.ConnectionProvider;
import fr.eni.ecole.troc_encheres.dal.DAO;

public class UtilisateurDAOJdbcImpl implements DAO<Utilisateur>{

	@Override
	public void insert(Utilisateur util) throws DALException {
		
		try(
				Connection con = ConnectionProvider.getConnection();
				PreparedStatement stmt = con.prepareStatement("INSERT INTO utilisateurs (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe) VALUES (?,?,?,?,?,?,?,?,md5(?))");
				) {

			stmt.setString(1, util.getPseudo());
			stmt.setString(2, util.getNom());
			stmt.setString(3, util.getPrenom());
			stmt.setString(4, util.getEmail());
			stmt.setString(5, util.getTel());
			stmt.setString(6, util.getRue());
			stmt.setString(7, util.getCp());
			stmt.setString(8, util.getVille());
			stmt.setString(9, util.getMdp());
			stmt.executeUpdate();
		} catch (SQLException throwables) {
			throw new DALException("Erreur insert");
		} 
	}

	@Override
	public void update(Utilisateur util) throws DALException {
		Connection con = null;

		try {
			con = ConnectionProvider.getConnection();
			PreparedStatement query = con.prepareStatement("UPDATE utilisateurs SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=md5(?) WHERE no_utilisateur=?");
			query.setString(1, util.getPseudo());
			query.setString(2, util.getNom());
			query.setString(3, util.getPrenom());
			query.setString(4, util.getEmail());
			query.setString(5, util.getTel());
			query.setString(6, util.getRue());
			query.setString(7, util.getCp());
			query.setString(8, util.getVille());
			query.setString(9, util.getMdp());
			query.setInt(10, util.getNumero());
			
			int nbRows = query.executeUpdate();
			if (nbRows == 0 || nbRows == -1) {
				throw new DALException("Erreur de mise à jour");
			}
			query.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Erreur update");
		} finally {
			try {
				con.close();

			} catch (Exception e) {
				throw new DALException("Erreur fermeture");
			}
		}
		
	}

	@Override
	public Utilisateur selectById(int idUtil) throws DALException {
		Utilisateur util = null;
		Connection con = null;
		Statement stmt = null;
		try {
			con = ConnectionProvider.getConnection();
			stmt = con.createStatement();
			PreparedStatement query = con.prepareStatement(
					"select * from utilisateurs where id = ?");
			query.setInt(1, idUtil);
			ResultSet rs = query.executeQuery();
			if (rs.next()) {
				util = new Utilisateur(rs.getInt("numero"),rs.getString("pseudo"),rs.getString("nom"), rs.getString("prenom"), rs.getString("email"),
						rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"));
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
		return util;
	}
	
	
	@Override
	public List<Utilisateur> selectAll() throws DALException {
		return null;
	}

	//Itération 2
	@Override
	public void delete(int idUtil) throws DALException {
		Connection con = null;
		Statement stmt = null;

		try {
			con = ConnectionProvider.getConnection();
			stmt = con.createStatement();
			PreparedStatement query = con.prepareStatement("delete from utilisateurs where no_utilisateur = ?");
			query.setInt(1, idUtil);
			int nbRows = query.executeUpdate();
			if (nbRows != 1) {
				throw new DALException("Erreur delete");
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
	}
	
	
	//Ajouter au besoin selectByX pour les recherches
}
