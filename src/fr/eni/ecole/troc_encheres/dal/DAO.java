package fr.eni.ecole.troc_encheres.dal;

import fr.eni.ecole.troc_encheres.dal.exceptions.DALException;
import java.util.List;

public interface DAO<T> {
	
	void insert(T obj) throws DALException;
    void update(T obj) throws DALException;
    T selectById(int idObj) throws DALException;
    List<T> selectAll() throws DALException;
    void delete(int idObj) throws DALException;
	
}
