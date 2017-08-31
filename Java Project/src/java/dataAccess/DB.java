package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;

public final class DB {

    public static Connection dbc() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/deriche", "root", "password");
            return connection;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
