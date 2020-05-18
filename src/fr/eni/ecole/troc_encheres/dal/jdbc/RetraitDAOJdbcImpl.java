package fr.eni.ecole.troc_encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import fr.eni.ecole.troc_encheres.bo.Categorie;
import fr.eni.ecole.troc_encheres.bo.Retrait;
import fr.eni.ecole.troc_encheres.bo.Utilisateur;
import fr.eni.ecole.troc_encheres.bo.Vente;
import fr.eni.ecole.troc_encheres.dal.ConnectionProvider;
import fr.eni.ecole.troc_encheres.dal.DAO;
import fr.eni.ecole.troc_encheres.dal.exceptions.DALException;
/*
 * @author Edouard
 */
public class RetraitDAOJdbcImpl implements DAO<Retrait>{

	@Override
	public void insert(Retrait retrait) throws DALException {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ConnectionProvider.getConnection();
			stmt = con.prepareStatement("insert into retraits (no_vente, rue, code_postal, ville) values (?,?,?,?)");
			stmt.setInt(1, retrait.getVente().getNumero());
			stmt.setString(2, retrait.getRue());
			stmt.setString(3, retrait.getCp());
			stmt.setString(4, retrait.getVille());
			
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
	public void update(Retrait obj) throws DALException {
		
	}

	@Override
	public Retrait selectById(int noVente) throws DALException {
		Retrait retrait = null;
		Connection con = null;
		Statement stmt = null;
		try {
			con = ConnectionProvider.getConnection();
			stmt = con.createStatement();
			PreparedStatement query = con.prepareStatement(
					"select * from retraits join ventes on retrait.no_vente=ventes.no_vente "
					+ "join categories on categories.no_categorie = ventes.no_categorie "
					+ "join utilisateurs on ventes.no_utilisateur = utilisateurs.no_utilisateur "
					+ "where no_vente = ?");
			query.setInt(1, noVente);
			ResultSet rs = query.executeQuery();
			if (rs.next()) {
				retrait = new Retrait(
						new Vente(rs.getInt("no_vente"), rs.getString("nom_article"), rs.getString("description"), rs.getDate("dateFinEncheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"), 
								new Utilisateur(rs.getInt("numero"),rs.getString("pseudo"),rs.getString("nom"), rs.getString("prenom"), rs.getString("email"),
										rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit")), 
								new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"))),
						rs.getString("rue"),rs.getString("code_postal"), rs.getString("ville"));
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
		return retrait;
	}

	@Override
	public List<Retrait> selectAll() throws DALException {		return null;
	}

	@Override
	public void delete(int idObj) throws DALException {
	}

}
