package fr.eni.ecole.troc_encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.eni.ecole.troc_encheres.bo.Categorie;
import fr.eni.ecole.troc_encheres.bo.Retrait;
import fr.eni.ecole.troc_encheres.bo.Utilisateur;
import fr.eni.ecole.troc_encheres.bo.Vente;
import fr.eni.ecole.troc_encheres.dal.ConnectionProvider;
import fr.eni.ecole.troc_encheres.dal.DAO;
import fr.eni.ecole.troc_encheres.dal.exceptions.DALException;

/**
 * @author Edouard
 */
public class VenteDAOJdbcImpl implements DAO<Vente> {

	@Override
	public void insert(Vente vente) throws DALException {
		Connection con = null;
		PreparedStatement stmt = null;
		int noVente = 0;
		try {
			con = ConnectionProvider.getConnection();
			stmt = con.prepareStatement(
					"insert into ventes (nom_article, description, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) values (?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, vente.getNomArticle());
			stmt.setString(2, vente.getDescription());
			stmt.setDate(3, new Date(vente.getDateFinEncheres().getTime()));
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
			query.setDate(3, new Date(vente.getDateFinEncheres().getTime()));
			query.setInt(4, vente.getMiseAPrix());
			query.setInt(5, vente.getPrixVente());
			query.setInt(6, vente.getUtil().getNumero());
			query.setInt(7, vente.getCategorie().getNumero());
			query.setInt(8, vente.getNumero());
			
			int nbRows = query.executeUpdate();
			if (nbRows == 0 || nbRows == -1) {
				throw new DALException("Erreur de mise � jour");
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
            PreparedStatement query = con.prepareStatement("select * from ventes left join utilisateurs on utilisateurs.no_utilisateur= ventes.no_utilisateur left join categories on categories.no_categorie = ventes.no_categorie left join retraits on retraits.no_vente = ventes.no_vente where ventes.no_vente = ?");
            
            query.setInt(1, idVente);
            ResultSet rs = query.executeQuery();
            if (rs.next()) {
                vente = new Vente(rs.getInt("no_vente"), rs.getString("nom_article"), rs.getString("description"), rs.getDate("date_fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"), 
                		new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("tel"), rs.getString("rue"), rs.getString("cp"), rs.getString("ville"), rs.getString("mdp"), rs.getInt("credit")), 
                		new Categorie(rs.getInt("no_categorie"), rs.getString("libelle")));
                vente.setRetrait(new Retrait(vente, rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville")));
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
            ResultSet rs = stmt.executeQuery("select * from ventes join utilisateurs on utilisateurs.no_utilisateur= ventes.no_utilisateur join categories on categories.no_categorie = vente.no_categorie join retraits on retraits.no_vente = ventes.no_vente");
            Vente vente = null;
            while (rs.next()) {
                vente = new Vente(rs.getInt("no_vente"), rs.getString("nom_article"), rs.getString("description"), rs.getDate("date_fin_encheres"), rs.getInt("miseAPrix"), rs.getInt("prix_vente"), 
                		new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit")), 
                		new Categorie(rs.getInt("no_vente"), rs.getString("libelle")));
                vente.setRetrait(new Retrait(vente, rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville")));
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
            		+ "join categories on categories.no_categorie = ventes.no_categorie "
            		+ "join retraits on retraits.no_vente = ventes.no_vente "
            		+ "where no_utilisateur = ?");
            stmt.setInt(1, idUtil);
            ResultSet rs = stmt.executeQuery();
            Vente vente = null;
            while (rs.next()) {
                vente = new Vente(rs.getInt("no_vente"), rs.getString("nom_article"), rs.getString("description"), rs.getDate("date_fin_encheres"), rs.getInt("miseAPrix"), rs.getInt("prix_vente"), 
                		new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit")), 
                		new Categorie(rs.getInt("no_vente"), rs.getString("libelle")));
                
                vente.setRetrait(new Retrait(vente, rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville")));
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
	
	/**
	 * @author Matthieu
	 * @param nomArticle
	 * @param monNoUtilisateur
	 * @param noCategorie
	 * @param mesVentes
	 * @param mesEncheresEnCours
	 * @param mesAcquisitions
	 * @param autresEncheres
	 * @return
	 * @throws DALException
	 */
	public List<Vente> selectByPlusieursChamps(String nomArticle, int monNoUtilisateur, int noCategorie, 
			boolean mesVentes, boolean mesEncheresEnCours, boolean mesAcquisitions, 
			boolean autresEncheres ) throws DALException {
		
		List<Vente> ventes = new ArrayList<>();
        Connection con = null;
        try {
            con = ConnectionProvider.getConnection();
            String sql = "";
            // Connaitre les parametres à utiliser dans la requete
            HashMap<Integer, Boolean> elemParamReq = new HashMap<Integer, Boolean>();
            
            // requete pour la vente sur une categories
            if (mesVentes) {
            	elemParamReq.put(elemParamReq.size(), true);
            	elemParamReq.put(elemParamReq.size(), true);
            	elemParamReq.put(elemParamReq.size(), true);
            	
            	sql += "(SELECT u.*, v.*, c.* FROM ventes v "
            			+ "JOIN utilisateurs u ON u.no_utilisateur= v.no_utilisateur "
            			+ "JOIN categories c ON c.no_categorie = v.no_categorie "
            			+ "WHERE v.nom_article LIKE ? "
            			+ "AND v.no_utilisateur = ? ";
            	if (noCategorie != 0) {
            		sql += "AND v.no_categorie = ? ";
            	}else {
            		elemParamReq.replace(elemParamReq.size()-1, false);
				}
            	
            	sql +=")";
			}
            
            // requete pour mes encheres en cours
            if (mesEncheresEnCours) {
            	elemParamReq.put(elemParamReq.size(), true);
            	elemParamReq.put(elemParamReq.size(), true);
            	elemParamReq.put(elemParamReq.size(), true);
            	
            	if (mesVentes) {
            		sql +=" UNION ";
				}
            	
            	sql += "(SELECT u.*, v.*, c.* FROM ventes v "
            			+ "JOIN utilisateurs u ON u.no_utilisateur= v.no_utilisateur "
            			+ "JOIN categories c ON c.no_categorie = v.no_categorie "
            			+ "JOIN encheres e ON e.no_vente = v.no_vente "
            			+ "WHERE v.date_fin_encheres > NOW() "
            			+ "AND v.nom_article LIKE ? "
            			+ "AND e.date_enchere IN "
            				+ "(SELECT max(date_enchere) FROM encheres WHERE no_utilisateur = ? GROUP BY no_vente) ";
            	
				if (noCategorie != 0) {
					sql += "AND v.no_categorie = ? ";
				}else {
            		elemParamReq.replace(elemParamReq.size()-1, false);
				}
    	            	
            	sql +=")";
			}
          
            // requete pour mes acquisitions
            if (mesAcquisitions) {
            	elemParamReq.put(elemParamReq.size(), true);
            	elemParamReq.put(elemParamReq.size(), true);
            	elemParamReq.put(elemParamReq.size(), true);
            	
            	if (mesVentes || mesEncheresEnCours) {
            		sql +=" UNION ";
				}
            	
				sql += "(SELECT u.*, v.*, c.*  FROM encheres e "
						+ "JOIN ventes v ON v.no_vente = e.no_vente "
						+ "JOIN utilisateurs u on u.no_utilisateur= v.no_utilisateur "
						+ "JOIN categories c on c.no_categorie = v.no_categorie "
						+ "WHERE e.date_enchere IN "
							+ "(SELECT max(date_enchere) FROM encheres group by no_vente) "
						+ "AND v.date_fin_encheres < NOW() "
						+ "AND v.nom_article LIKE ? "
						+ "AND e.no_utilisateur = ? ";

				if (noCategorie != 0) {
  					sql += "AND v.no_categorie = ? ";
  				}else {
            		elemParamReq.replace(elemParamReq.size()-1, false);
				}
		      	            	
              	sql +=")";
			}
            
            // requete autres encheres 
            if (autresEncheres) {
            	elemParamReq.put(elemParamReq.size(), true);
            	elemParamReq.put(elemParamReq.size(), true);
            	elemParamReq.put(elemParamReq.size(), true);
            	
            	if (mesVentes || mesEncheresEnCours || mesAcquisitions) {
            		sql +=" UNION ";
				}
				sql += "(SELECT u.*, v.*, c.* FROM ventes v "
						+ "JOIN utilisateurs u ON u.no_utilisateur = v.no_utilisateur "
						+ "JOIN categories c ON c.no_categorie = v.no_categorie "
						+ "WHERE v.nom_article like ? "
						+ "AND v.no_utilisateur != ? ";
				
				if (noCategorie != 0) {
  					sql += "AND v.no_categorie = ? ";
  				}else {
            		elemParamReq.replace(elemParamReq.size()-1, false);
				}
		      	            	
              	sql +="ORDER BY ABS(DATEDIFF(v.date_fin_encheres, CURRENT_DATE)))";		
		        		
			}
          
            System.out.println("requete : " + sql);
            
            if (sql.isEmpty()) {
			}else {
				PreparedStatement stmt = con.prepareStatement(sql);
				
				// connaitre les parametres à prendre en compte dans la requete
				int count = 0;
				for (int i = 0; i < elemParamReq.size(); i++) {
					if ((i == 0) || (i == 3) || (i == 6) || (i == 9)) {
						if (elemParamReq.get(i)) {
							count++;
							stmt.setString(count, "%"+nomArticle+"%");
						}
					}
					if ((i == 1) || (i == 4) || (i == 7) || (i == 10)) {
						if (elemParamReq.get(i)) {
							count++;
							stmt.setInt(count, monNoUtilisateur);
						}
					}
					if ((i == 2) || (i == 5) || (i == 8) || (i == 11)) {
						if (elemParamReq.get(i)) {
							count++;
							stmt.setInt(count, noCategorie);
						}
					}
				}
				
				ResultSet rs = stmt.executeQuery();
				Vente vente = null;
				
				while (rs.next()) {
					vente = new Vente(rs.getInt("no_vente"), rs.getString("nom_article"), rs.getString("description"), rs.getDate("date_fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"), 
							new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("tel"), rs.getString("rue"), rs.getString("cp"), rs.getString("ville"), rs.getString("mdp"), rs.getInt("credit")), 
							new Categorie(rs.getInt("no_categorie"), rs.getString("libelle")));
					ventes.add(vente);
				}
				
				stmt.close();
				rs.close();
			}
        
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


	
	public List<Vente> selectVentesTerminees() throws DALException {
		List<Vente> ventes = new ArrayList<>();
        Connection con = null;
        try {
            con = ConnectionProvider.getConnection();
            
            PreparedStatement stmt = con.prepareStatement("select * from ventes "
            		+ "join utilisateurs on utilisateurs.no_utilisateur= ventes.no_utilisateur "
            		+ "join categories on categories.no_categorie = ventes.no_categorie "
            		+ "join retraits on retraits.no_vente = ventes.no_vente "
            		+ "where date(date_fin_encheres) = date(now())");
            ResultSet rs = stmt.executeQuery();
            Vente vente = null;
            while (rs.next()) {
                vente = new Vente(rs.getInt("no_vente"), rs.getString("nom_article"), rs.getString("description"), rs.getDate("date_fin_encheres"), rs.getInt("miseAPrix"), rs.getInt("prix_vente"), 
                		new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit")), 
                		new Categorie(rs.getInt("no_vente"), rs.getString("libelle")));
                
                vente.setRetrait(new Retrait(vente, rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville")));
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
