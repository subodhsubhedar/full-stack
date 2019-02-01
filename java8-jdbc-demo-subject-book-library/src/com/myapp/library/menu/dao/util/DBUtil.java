package com.myapp.library.menu.dao.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import com.myapp.library.exception.LibraryDaoException;

/**
 * 
 * @author Admin
 *
 */
public class DBUtil {

	public static final String DB_ORCL = "ORACLE";

	public static final String DB_MYSQL = "MY_SQL";

	public static final String propFileName = "library_app.properties";

	private static Properties prop = new Properties();

	public static Connection getConnection(String dbName) throws LibraryDaoException {

		Connection conn = null;

		try {
			FileInputStream inputStream = new FileInputStream(propFileName);

			prop.load(inputStream);

			switch (dbName) {

			case (DB_ORCL): {
				// Details about Oracle if present.
			}
			case (DB_MYSQL): {
				conn = DriverManager.getConnection(prop.getProperty("mysqldb_url"),
						prop.getProperty("mysqldb_user"), prop.getProperty("mysqldb_password"));
			}
			}

		} catch (Exception e) {
			throw new LibraryDaoException("Excpetion occured while connecting to DB :", e);
		}

		return conn;

	}
}
