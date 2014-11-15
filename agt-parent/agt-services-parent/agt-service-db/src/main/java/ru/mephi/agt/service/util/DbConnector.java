package ru.mephi.agt.service.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbConnector {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(DbConnector.class);
	private static final String DS_NAME = "java:jboss/datasources/AgtDS";

	public static Connection getConnection() throws SQLException {

		Connection con = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup(DS_NAME);
			con = ds.getConnection();
		} catch (NamingException e) {
			LOGGER.error("Can't lookup DS {}", DS_NAME, e);
		} catch (SQLException e) {
			LOGGER.error("Can't obtain conntection from DS : {}", DS_NAME, e);
			throw e;
		}
		return con;
	}
}
