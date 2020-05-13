package fr.eni.ecole.troc_encheres.dal;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public abstract class ConnectionProvider {

	public static DataSource dataSource;

	static {
		Context context;
		try {
			context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/pool_cnx");
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException("Acc�s impossible � la bdd");
		}
	}

	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

}
