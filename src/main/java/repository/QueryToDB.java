package repository;

import java.sql.*;

public final class QueryToDB {
    private static final String URL = "jdbc:mysql://localhost:3306/mysql?serverTimezone=UTC";
    private static String USERNAME = "root1";
    private static String PASSWORD = "root1";

    protected static Connection connection;
    protected static Statement statement;

    protected static void connectionBegin() throws SQLException {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        statement = connection.createStatement();
    }
}
