package Database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionTest {
    public static String url = "jdbc:mysql://localhost:3306/cdproject";
    public static String username = "root";

    @Test
    void connectionTest() {
        boolean isItGood = false;
        try {
            Connection connection = DriverManager.getConnection(url, username, "");
            isItGood = true;
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        assertTrue(isItGood);
    }
}