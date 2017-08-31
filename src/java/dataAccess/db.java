/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Carl
 */
public final class db 
{
    public static Connection dbc()
        {
            Connection dbc = null;
            
                try
                {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/deriche", "root", "password");
                    return connection;
                }
                catch (Exception ex) 
                {
                    ex.printStackTrace();
                    return null;
                }
        }// end private static Connection getDBConn()
}// end public final class db 
