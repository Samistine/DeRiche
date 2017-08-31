package dataAccess;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The {@link dataAccess.DBConnection} class contains all the methods needed for
 * database access in the DeRiche project.<br>
 * Any class accessing a database through {@link dataAccess.DBConnection} will
 * need to either implement {@link dataAccess.Accessible} or extend
 * {@link dataAccess.Access}. Only classes doing one of these two steps will be
 * able to properly call any methods from this class.<br><br>
 *
 * Date: 2/23/2017
 *
 * @author Cameron Randolph
 * @author Carl Moon
 *
 * @version 1.2
 *
 * @see DBConnection#select(Accessible, Object)
 * @see Access
 * @see Accessible
 */
public final class DBConnection {

    /**
     * The Select identifier for database operations
     */
    public static final char SELECT = 'S';
    /**
     * The Insert identifier for database operations
     */
    public static final char INSERT = 'I';
    /**
     * The Update identifier for database operations
     */
    public static final char UPDATE = 'U';
    /**
     * The Delete identifier for database operations
     */
    public static final char DELETE = 'D';
    /**
     * The database connection
     */
    private static String connection = "jdbc:mysql://localhost:3306/deriche";
    /**
     * @description The database driver
     */
    private static String driver = "com.mysql.jdbc.Driver";

    /**
     * Changes the value of connection from the default.
     *
     * @param connection the {@link java.lang.String} representing the new
     * connection
     */
    public static void setConnection(String connection) {
        DBConnection.connection = connection;
    }

    /**
     * Changes the value of driver from the default.
     *
     * @param driver the {@link java.lang.String} representing the new driver
     */
    public static void setDriver(String driver) {
        DBConnection.driver = driver;
    }

    /**
     * Returns the value stored in the connection variable.
     *
     * @return a {@link java.lang.String} representing the database connection
     */
    public static String getConnection() {
        return connection;
    }

    /**
     * Returns the value stored in the driver variable.
     *
     * @return a {@link java.lang.String} representing the database driver
     */
    public static String getDriver() {
        return driver;
    }

    /**
     * Handles all select statements required by the DeRiche business
     * objects.<br>
     * This method has the ability to select any value from the database
     * described in the establish() method and return it as an
     * {@link java.lang.Object}.
     *
     * @author Carl Moon
     * @author Cameron Randolph
     *
     * @param sql the statement to be passed to the database
     * @param var the variable to be used to set the designated parameter for
     * the {@link java.sql.PreparedStatement}
     *
     * @return an {@link java.lang.Object} array holding all values selected
     * from the database
     *
     * @see select
     *
     * @deprecated as of version 1.2, replaced by select
     *
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the Driver for the database cannot be
     * found
     */
    public static Object[] selectDB(String sql, String var) throws SQLException, ClassNotFoundException {

        int columnCount;
        Object[] data;
        Connection connection;
        PreparedStatement statement;
        ResultSet rs;

        connection = establish();
        connection.prepareStatement(sql);
        statement = connection.prepareStatement(sql);
        statement.setString(1, var);
        rs = statement.executeQuery();
        columnCount = rs.getMetaData().getColumnCount();
        data = new Object[columnCount];

        while (rs.next()) {
            for (int i = 0; i < columnCount; i++) {
                data[i] = rs.getObject(i + 1);
            }
        }

        connection.close();

        return data;
    }

    /**
     * Handles all select statements required by the DeRiche business
     * objects.<br>
     * This method has the ability to select rows from the database described in
     * the establish() method.
     *
     * @author Carl Moon
     * @author Cameron Randolph
     *
     * @param object the {@link dataAccess.Accessible} object representation of
     * a table in a database for which a select command is to be made
     * @param value the primary key of the table represented by the
     * {@link dataAccess.Accessible} object. Used to set the designated
     * parameter of the {@link java.sql.PreparedStatement}
     * @param column the index of the column in the database to be selected from
     * @param negate changes the "=" operator to "<>" in for the "WHERE" clause
     * of the SQL statement
     * @param set determines whether or not the values of
     * {@link dataAccess.Accessible} object passed will be set to the values
     * retrieved from the database
     *
     * @return an {@link java.lang.Object} array holding all values selected
     * from the database
     */
    public static Object[][] select(Accessible object, Object value, int column, boolean negate, boolean set) {

        int columnCount;

        ArrayList<Object[]> rows = new ArrayList<Object[]>();

        Object[] data = null;
        Object[][] finalData = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            String sql = generateSQL(object, column, SELECT);

            if (negate) {
                sql = sql.replaceFirst("=", "<>");
            }

            connection = establish();
            statement = connection.prepareStatement(sql);
            statement.setObject(1, value);
            resultSet = statement.executeQuery();

            columnCount = resultSet.getMetaData().getColumnCount();
            data = new Object[columnCount];

            while (resultSet.next()) {
                for (int i = 0; i < columnCount; i++) {
                    data[i] = resultSet.getObject(i + 1);
                }

                rows.add(data);

                data = new Object[columnCount];
            }

            finalData = new Object[rows.size()][columnCount];

            for (int i = 0; i < rows.size(); i++) {
                finalData[i] = rows.get(i);
            }

            if (set && rows.size() == 1) {
                object.setStatementValues(null, finalData[0]);
            }

            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return finalData;
    }

    /**
     * Handles all insert statements required by the DeRiche business
     * objects.<br>
     * This method has the ability to insert rows into the database described in
     * the establish() method.
     *
     * @author Cameron Randolph
     *
     * @param object the {@link dataAccess.Accessible} object representation of
     * a table in a database for which an insert command is to be made
     * @param values the values to be added to the
     * {@link java.sql.PreparedStatement} as designated parameters
     * @param determines whether or not the values of
     * {@link dataAccess.Accessible} object passed will be set to the values
     * retrieved from the database
     *
     * @return an int representing whether or not the database was successfully
     * updated
     *
     * @throws StatementValuesNotImplementedException if the
     * {@link dataAccess.Accessible} object has not properly implemented the
     * setStatementValues(PreparedStatement, Object[]) method
     */
    public static int insert(Accessible object, Object[] values) throws StatementValuesNotImplementedException {

        int executed = 0;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = establish();
            statement = connection.prepareStatement(generateSQL(object, -1, INSERT));
            statement = object.setStatementValues(statement, values);
            if (statement == null) {
                throw new StatementValuesNotImplementedException();
            }

            executed = statement.executeUpdate();

            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return executed;
    }

    /**
     * Handles all update statements required by the DeRiche business
     * objects.<br>
     * This method has the ability to update rows in the database described in
     * the establish() method.
     *
     * @param object the {@link dataAccess.Accessible} object representation of
     * a table in a database for which a delete command is to be made
     * @param value the primary key of the table represented by the
     * {@link dataAccess.Accessible} object. Used to set the designated
     * parameter of the {@link java.sql.PreparedStatement}
     * @param column the index of the column in the database to be selected from
     *
     * @return an int representing whether or not the database was successfully
     * updated
     *
     * @throws StatementValuesNotImplementedException if the
     * {@link dataAccess.Accessible} object has not properly implemented the
     * setStatementValues(PreparedStatement, Object[]) method
     */
    public static int update(Accessible object, Object value, int column) throws StatementValuesNotImplementedException {

        int executed = 0;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = establish();
            statement = connection.prepareStatement(generateSQL(object, column, UPDATE));
            statement = object.setStatementValues(statement, null);
            statement.setObject(object.getClass().getDeclaredFields().length, value);
            if (statement == null) {
                throw new StatementValuesNotImplementedException();
            }
            executed = statement.executeUpdate();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return executed;
    }

    /**
     * Handles all delete statements required by the DeRiche business
     * objects.<br>
     * This method has the ability to delete rows in the database described in
     * the establish() method.
     *
     * @param object the {@link dataAccess.Accessible} object representation of
     * a table in a database for which an update command is to be made
     * @param value the primary key of the table represented by the
     * {@link dataAccess.Accessible} object. Used to set the designated
     * parameter of the {@link java.sql.PreparedStatement}
     * @param column the index of the column in the database to be selected from
     *
     * @return an int representing whether or not the database was successfully
     * updated
     */
    public static int delete(Accessible object, Object value, int column) {

        int executed = 0;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = establish();
            statement = connection.prepareStatement(generateSQL(object, column, DELETE));
            statement.setObject(1, value);
            executed = statement.executeUpdate();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return executed;
    }

    /**
     * Generates SQL commands for the {@link dataAccess.Accessible} object
     * passed as a parameter.
     *
     * @param object the {@link dataAccess.Accessible} object representation of
     * a table in a database for which an SQL statement is to be made
     * @param var the char representation of the type of action to be taken when
     * invoking this method
     *
     * @return a {@link java.lang.String} representation of the SQL command
     */
    public static String generateSQL(Accessible object, int column, char var) {

        if (var == 'S') {

            Field[] fields;
            String sql;

            fields = object.getClass().getDeclaredFields();
            sql = "Select * From " + object.getClass().getSimpleName() + " Where " + fields[column].getName() + " = ?";

            return sql + ";";

        } else if (var == 'I') {

            Field[] fields;
            String sql;
            String names;
            String marks;

            fields = object.getClass().getDeclaredFields();
            sql = "Insert into " + object.getClass().getSimpleName() + "(";
            names = "";
            marks = "";

            for (int i = 0; i < fields.length; i++) {
                if (i < fields.length - 1) {
                    names += fields[i].getName() + ", ";
                    marks += "?, ";
                } else {
                    names += fields[i].getName() + ") values(";
                    marks += "?)";
                }
            }

            return sql + names + marks + ";";

        } else if (var == 'U') {

            Field[] fields;
            String sql;

            fields = object.getClass().getDeclaredFields();
            sql = "Update " + object.getClass().getSimpleName() + " set ";

            for (int i = 1; i < fields.length; i++) {
                if (i < fields.length - 1) {
                    sql += fields[i].getName() + " = ?, ";
                } else {
                    sql += fields[i].getName() + " = ? Where " + fields[column].getName() + " = ?";
                }
            }

            return sql + ";";

        } else if (var == 'D') {

            Field[] fields;
            String sql;

            fields = object.getClass().getDeclaredFields();
            sql = "Delete From " + object.getClass().getSimpleName() + " Where " + fields[column].getName() + " = ?";

            return sql + ";";

        }

        return "Error: Incompatible Command";
    }

    /**
     * Retrieves the class name from the object passed to it.<br>
     * This method only gives the name of the class. Any packages listed in the
     * full class name are removed from the return value.
     *
     * @return a {@link java.lang.String} representing the name of the class
     *
     * @deprecated as of version 1.2
     */
    private static String getClassName(Object object) {
        String name;
        int index;

        name = "";
        index = object.getClass().getName().lastIndexOf('.') + 1;

        for (int i = index; i < (object.getClass().getName().length()); i++) {
            name += object.getClass().getName().charAt(i);
        }

        return name;
    }

    /**
     * Establishes a connection to the DeRiche database for every method in the
     * {@link dataAccess.DBConnection} class.
     *
     * @return a {@link java.sql.Connection} already linked to the DeRiche
     * database
     *
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the Driver for the database cannot be
     * found
     */
    public static Connection establish() throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(DBConnection.connection, "root", "password");
        return connection;
    }

    /**
     * Thrown to indicate that the overridden setStatementValues method from
     * {@link dataAccess.Accessible} is not properly implemented.
     *
     * @author Cameron Randolph
     */
    public static class StatementValuesNotImplementedException extends Exception {

        private String message;

        public StatementValuesNotImplementedException() {
            message = "Method setStatementValues from Acessible not properly implemented";
        }

        public String toString() {
            return message;
        }
    }
}
