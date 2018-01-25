package be.brickrevolution.data.util;

import be.brickrevolution.util.BreakoutException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {

    private static final String URL = "jdbc:mysql://149.202.46.120/breakoutrevolution?useSSL=false";
    private static final String UID = "usrbreakout";
    private static final String PWD = "TIbreakout2017";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new BreakoutException("Unable to load database driver.", ex);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, UID, PWD);
    }
}
