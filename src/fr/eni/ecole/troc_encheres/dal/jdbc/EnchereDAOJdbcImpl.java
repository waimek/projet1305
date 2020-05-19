package fr.eni.ecole.troc_encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import fr.eni.ecole.troc_encheres.bo.Categorie;
import fr.eni.ecole.troc_encheres.bo.Enchere;
import fr.eni.ecole.troc_encheres.bo.Utilisateur;
import fr.eni.ecole.troc_encheres.bo.Vente;
import fr.eni.ecole.troc_encheres.dal.ConnectionProvider;
import fr.eni.ecole.troc_encheres.dal.DAO;
import fr.eni.ecole.troc_encheres.dal.exceptions.DALException;
/*
 * @author Edouard
 */
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

	/*public void supprimerDerniereEnchere(int noVente) throws DALException {
		Connection con = null;
		Statement stmt = null;

		try {
			con = ConnectionProvider.getConnection();
			stmt = con.createStatement();
			PreparedStatement query = con.prepareStatement("delete from encheres where (select max(date_enchere) from encheres where no_vente= ?)");
			query.setInt(1, noVente);
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
*/
	public Enchere getDerniereEnchere(int noVente) throws DALException {
		Connection con = null;
		Statement stmt = null;
		Enchere enchere = null; 
		try {
			con = ConnectionProvider.getConnection();
			stmt = con.createStatement();
			PreparedStatement query = con.prepareStatement("select * from encheres "
					+ "join utilisateurs on utilisateurs.no_utilisateur = encheres.no_utilisateur "
					+ "join ventes on ventes.no_vente=encheres.no_vente "
					+ "join categories on ventes.no_categorie = categories.no_categorie "
					+ "where (select max(date_enchere) from encheres where no_vente= ?)");
			query.setInt(1, noVente);
			ResultSet rs = query.executeQuery();
			if (rs.next()) {
				Utilisateur util = new Utilisateur(rs.getInt("no_utilisateur"),rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("tel"), rs.getString("rue"), rs.getString("cp"), rs.getString("ville"), rs.getString("mdp"), rs.getInt("credit"));
				enchere = new Enchere(rs.getDate("date_enchere"), util
						, new Vente(rs.getInt("no_vente"), rs.getString("nom_article"), rs.getString("description"), rs.getDate("date_fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"), util, new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"))));
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
		return enchere;
	}

	@Override
	public Enchere selectByPseudo(String pseudo) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enchere selectByTel(String tel) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enchere selectByEmail(String email) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}
}
