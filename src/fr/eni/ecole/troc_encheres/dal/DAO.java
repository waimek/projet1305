package fr.eni.ecole.troc_encheres.dal;
import fr.eni.ecole.troc_encheres.bo.Utilisateur;
/*
 * @author Edouard
 */
import fr.eni.ecole.troc_encheres.dal.exceptions.DALException;
import java.util.List;

public interface DAO<T> {
	
	void insert(T obj) throws DALException;
    void update(T obj) throws DALException;
    T selectById(int idObj) throws DALException;
    List<T> selectAll() throws DALException;
    void delete(int idObj) throws DALException;
	int selectIdByUser(String pseudo) throws DALException;
	int selectIdByTel(String tel) throws DALException;
	int selectIdByEmail(String email)throws DALException;
}
