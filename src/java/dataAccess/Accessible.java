package dataAccess;

import java.sql.PreparedStatement;

/**
 * @ description The {@link dataAccess.Accessible} interface allows objects to use methods from the {@link dataAccess.DBConnection} class.<br>
 * Any class implementing {@link dataAccess.Accessible} must follow the conventions listed below.<br><br>
 * 
 * <ol>
 *   <li>The simple class name, retrieved from using Class.getSimpleName(), must match the name of the table in the database.</li>
 *   <li>The global attributes of the class must match the column names in the table. &nbsp; &nbsp;<b>NOTE:</b> They do not have to be case sensitive</li>
 * </ol>
 * 
 * <br>
 * In addition to these rules, {@link dataAccess.Accessible} objects must also override the methods setStatementValues(PreparedStatement statement, Object[] values) and getPrimaryKeyIndex() in a very specific way.<br>
 * The exact requirements for how to properly implement the methods can be found in a supplementary file within the doc folder of the dataAccess package named "How to Implement Accessible.txt". However, the basic rules are as follows.<br><br>
 * 
 * <ol>
 *   <li>Set all attributes for the class using the {@link java.lang.Object} array passed as a parameter to the setStatementValues(PreparedStatement statement, Object[] values) method.</li>
 *   <li>Set all designated parameters for the {@link java.sql.PreparedStatement} object passed as a parameter using its setObject()/setString()/setInt()... etc. methods.</li>
 *   <li>Set all designated parameters for the {@link java.sql.PreparedStatement} object passed as a parameter using its setObject()/setString()/setInt()... etc. methods. However, unlike when setStatementValues(PreparedStatement statement, Object[] values) is called using DBConnection.insert(Accessible, Object[]), this part of the method will set the designated parameter for the primary key last.</li>
 *   <li>Return the index for the primary key of the class using the getPrimaryKeyIndex() method. See getPrimaryKeyIndex() for more information.</li>
 * </ol>
 * 
 * <br>
 * Any class following these rules may use the {@link dataAccess.DBConnection} class' static methods for accessing database tables.<br><br>
 * 
 * 
 * @since  3/01/2017
 *
 * @author Cameron Randolph
 *@author Yasmina Rabhi added documentation.
 *
 * @version 1.2
 * 
 * @see Access
 * @see DBConnection
 */
public interface Accessible {

	/**
	 *@ description  Sets all designated parameters for a {@link java.sql.PreparedStatement}.
	 * 
	 * @author Cameron Randolph
	 * 
	 * @param statement the {@link java.sql.PreparedStatement} which will have its designated parameters set
	 * @param values the values with which to set the designated parameters of the {@link java.sql.PreparedStatement}
	 * 
	 * @return a {@link java.sql.PreparedStatement} with all of its designated parameters set
	 */
    public abstract PreparedStatement setStatementValues(PreparedStatement statement, Object[] values);
    
    /**
     * @description Retrieves the primary key index for the class implementing the Accessible interface.
     * The index refers to the order of variable declaration in the class' global variables list.
     * 
     * @author Cameron Randolph
     * 
     * @return an int representing the index of the primary key of a table in a database
     */
    public abstract int getPrimaryKeyIndex();
    
}