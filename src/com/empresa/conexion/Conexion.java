package com.empresa.conexion;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class Conexion {

	private static BasicDataSource dataSource = null;

	private static DataSource getDataSource() {
		if (dataSource == null) {
			dataSource = new BasicDataSource();
			dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
			dataSource.setUsername("root");
			dataSource.setPassword("");
			dataSource.setUrl("jdbc:mysql://localhost:3306/crud");
			dataSource.setInitialSize(20);// cuantas conexiones de inicio
			dataSource.setMaxConnLifetimeMillis(15);// cuantas conexiones pueden estar activas
			dataSource.setMaxTotal(20);// maximos de conexiones que pueden haber en total
			dataSource.setMaxWaitMillis(5000);// cuanto tiempo espera para
												// desativar una conexion
		}
		return dataSource;
	}
	
	public static Connection getConnection() throws SQLException{
		return getDataSource().getConnection(); 
		
	}
}
