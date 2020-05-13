package fr.eni.ecole.troc_encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.troc_encheres.bo.Categorie;
import fr.eni.ecole.troc_encheres.bo.Utilisateur;
import fr.eni.ecole.troc_encheres.bo.Vente;
import fr.eni.ecole.troc_encheres.dal.ConnectionProvider;
import fr.eni.ecole.troc_encheres.dal.DAO;
import fr.eni.ecole.troc_encheres.dal.exceptions.DALException;

public class VenteDAOJdbcImpl implements DAO<Vente> {

	@Override
	public void insert(Vente vente) throws DALException {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ConnectionProvider.getConnection();
			stmt = con.prepareStatement(
					"insert into ventes (nom_article, description, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) values (?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, vente.getNomArticle());
			stmt.setString(2, vente.getDescription());
			stmt.setDate(3, (Date) vente.getDateFinEncheres());
			stmt.setInt(4, vente.getMiseAPrix());
			stmt.setInt(5, vente.getPrixVente());
			stmt.setInt(6, vente.getUtil().getNumero());
			stmt.setInt(7, vente.getCategorie().getNumero());
			int rows = stmt.executeUpdate();

			if (rows != 1) {
				throw new DALException("Erreur insert");
			} else {
				ResultSet rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					vente.setNumero(rs.getInt(1));
				} else {
					throw new DALException("Erreur GeneratedKeys");
				}
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new DALException("Erreur closeResult");
				}
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
	public void update(Vente vente) throws DALException {
		Connection con = null;

		try {
			con = ConnectionProvider.getConnection();
			PreparedStatement query = con.prepareStatement(
					"UPDATE ventes SET nom_article=?, description=?, date_fin_encheres=?, prix_initial=?, prix_vente=?, no_utilisateur=?, no_categorie=? WHERE no_vente=?");
			query.setString(1, vente.getNomArticle());
			query.setString(2, vente.getDescription());
			query.setDate(3, (Date) vente.getDateFinEncheres());
			query.setInt(4, vente.getMiseAPrix());
			query.setInt(5, vente.getPrixVente());
			query.setInt(6, vente.getUtil().getNumero());
			query.setInt(7, vente.getCategorie().getNumero());
			query.setInt(8, vente.getNumero());

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
	public Vente selectById(int idVente) throws DALException {
		Vente vente = null;
        Connection con = null;
        Statement stmt = null;
        try {
            con = ConnectionProvider.getConnection();
            stmt = con.createStatement();
            PreparedStatement query = con.prepareStatement("select * from ventes join utilisateurs on utilisateurs.no_utilisateur= ventes.no_utilisateur join categories on categories.no_categorie = vente.no_categorie where no_vente = ?");
            query.setInt(1, idVente);
            ResultSet rs = query.executeQuery();
            if (rs.next()) {
                vente = new Vente(rs.getInt("no_vente"), rs.getString("nom_article"), rs.getString("description"), rs.getDate("date_fin_encheres"), rs.getInt("miseAPrix"), rs.getInt("prix_vente"), 
                		new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit")), 
                		new Categorie(rs.getInt("no_vente"), rs.getString("libelle")));
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
        return vente;
	}

	@Override
	public List<Vente> selectAll() throws DALException {
		List<Vente> ventes = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        try {
            con = ConnectionProvider.getConnection();
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from ventes join utilisateurs on utilisateurs.no_utilisateur= ventes.no_utilisateur join categories on categories.no_categorie = vente.no_categorie");
            Vente vente = null;
            while (rs.next()) {
                vente = new Vente(rs.getInt("no_vente"), rs.getString("nom_article"), rs.getString("description"), rs.getDate("date_fin_encheres"), rs.getInt("miseAPrix"), rs.getInt("prix_vente"), 
                		new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit")), 
                		new Categorie(rs.getInt("no_vente"), rs.getString("libelle")));
                ventes.add(vente);
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
        return ventes;
	}

	@Override
	public void delete(int idVente) throws DALException {
		Connection con = null;
		Statement stmt = null;

		try {
			con = ConnectionProvider.getConnection();
			stmt = con.createStatement();
			PreparedStatement query = con.prepareStatement("delete from ventes where no_vente = ?");
			query.setInt(1, idVente);
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
	
	public List<Vente> selectByUtil(int idUtil) throws DALException {
		List<Vente> ventes = new ArrayList<>();
        Connection con = null;
        try {
            con = ConnectionProvider.getConnection();
            
            PreparedStatement stmt = con.prepareStatement("select * from ventes "
            		+ "join utilisateurs on utilisateurs.no_utilisateur= ventes.no_utilisateur "
            		+ "join categories on categories.no_categorie = vente.no_categorie "
            		+ "where no_utilisateur = ?");
            stmt.setInt(1, idUtil);
            ResultSet rs = stmt.executeQuery();
            Vente vente = null;
            while (rs.next()) {
                vente = new Vente(rs.getInt("no_vente"), rs.getString("nom_article"), rs.getString("description"), rs.getDate("date_fin_encheres"), rs.getInt("miseAPrix"), rs.getInt("prix_vente"), 
                		new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit")), 
                		new Categorie(rs.getInt("no_vente"), rs.getString("libelle")));
                ventes.add(vente);
            }
            stmt.close();
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                
                con.close();

            } catch (Exception e) {
                throw new DALException("Erreur fermeture");
            }
        }
        return ventes;
	}
}
