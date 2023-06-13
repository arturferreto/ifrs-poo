package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseDAO {
    private static final String IP       = "82.180.153.65";
    private static final String USER     = "u142008557_aula";
    private static final String PASSWORD = "Aula01Aula01!";
    private static final String DATABASE = "u142008557_aula";
    private static final String URL_JDBC = "jdbc:mysql://" + IP + "/" + DATABASE;

    public static String getURL() {
        return URL_JDBC;
    }

    public static String getUser() {
        return USER;
    }

    public static String getPassword() {
        return PASSWORD;
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(getURL(), getUser(), getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
