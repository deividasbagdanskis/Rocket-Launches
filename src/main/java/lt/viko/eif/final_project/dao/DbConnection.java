package lt.viko.eif.final_project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Establishes connection with a database.
 * @author Deividas Bagdanskis
 */
public class DbConnection {
    private Connection connection;
    private final String DB_NAME = "final_project_db";
    private final String USERNAME = "root";
    private final String PASSWORD = "pass";
    private String dbURL = "jdbc:mysql://localhost/" + DB_NAME + "?user=" + USERNAME + "&password=" + PASSWORD +
            "&useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false" +
            "&serverTimezone=UTC";

    /**
     * Creates connection with a database.
     */
    public DbConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbURL);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets connection with a database.
     * @return connection with a database
     */
    public Connection getConnection() {
        return connection;
    }
}
