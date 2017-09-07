package dataAccess;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;

/**
 * @description a class used for allowing business objects to access a typical relational database.<br>
 * @description Classes are only allowed to extend the {@link dataAccess.Access} class. Any class inheriting from it must follow the conventions listed below.<br><br>
 * 
 * <ol>
 *   <li>The simple class name, retrieved from using Class.getSimpleName(), must match the name of the table in the database.</li>
 *   <li>The global attributes of the class must match the column names in the table. &nbsp; &nbsp;<b>NOTE:</b> They do not have to be case sensitive</li>
 * </ol>
 * 
 * <br>
 * Any class following these rules may use the {@link dataAccess.DBConnection} class' static methods for accessing database tables.
 * 
 * @author Cameron Randolph
 * @author Yasnmina Rabhi added documentation
 * @see Accessible
 * @see DBConnection
 */
public abstract class Access implements Accessible {

	private boolean[] privacy;
	
	/**
	 *@description  Sets all designated parameters for a {@link java.sql.PreparedStatement}.
	 * 
	 * @author Cameron Randolph
	 * 
	 * @param statement the {@link java.sql.PreparedStatement} which will have its designated parameters set
	 * @param values the values with which to set the designated parameters of the {@link java.sql.PreparedStatement}
	 * 
	 * @return a {@link java.sql.PreparedStatement} with all of its designated parameters set
	 */
	public PreparedStatement setStatementValues(PreparedStatement statement, Object[] values) {
		
		Field[] fields;
		fields = this.getClass().getDeclaredFields();
		
		try {
			
			openFields(fields);
			
            if (values != null) {
    			for (int i = 0; i < values.length; i++) {
    				if (statement != null) {
    					statement.setObject((i + 1), values[i]);
    				}
    				
    				if (values != null) {
        				fields[i].set(this, values[i] + "");
    				}
    			}
    			
            } else {
    			for (int i = 1; i < fields.length; i++) {
    				statement.setObject(i, fields[i].get(this));
    			}
            }
            
            closeFields(fields);
            
		}
		
		catch (Exception ex) {
			ex.printStackTrace();
			
			return null;
		}
		
		return statement;
	}
	
	/**
	 * @ description Opens any fields in the child class that are declared as private.
	 * 
	 * @param fields The array of {@link java.reflect.Field} objects representing the child class' attributes
	 * @throws FieldsNotAccessedException if the fields passed were already in an open state
	 */
	protected void openFields(Field[] fields) throws FieldsNotAccessedException {
		
		if (privacy != null) {
			throw new FieldsNotAccessedException();
		}
		
		privacy = new boolean[fields.length];
		
		for (int i = 0; i < fields.length; i++) {
			if (!fields[i].isAccessible()) {
				fields[i].setAccessible(true);
				privacy[i] = true;
				
			} else {
				privacy[i] = false;
				
			}
		}
	}
	
	/**
	 * @ description Closes any fields in the child class that were opened by openFields().
	 * 
	 * @param fields The array of {@link java.reflect.Field} objects representing the child class' attributes
	 * @throws FieldsNotAccessedException if the fields passed were already in a closed state
	 */
	protected void closeFields(Field[] fields) throws FieldsNotAccessedException {
		
		if (privacy == null) {
			throw new FieldsNotAccessedException();
		}
		
		for (int i = 0; i < fields.length; i++) {
			if (privacy[i]) {
				fields[i].setAccessible(false);
			}
		}
		
		privacy = null;
	}
	
    /**
     * @description Retrieves the primary key index for the class implementing the Accessible interface.<br>
     * @description The index refers to the order of variable declaration in the class' global variables list.
     * 
     * @author Cameron Randolph
     * 
     * @return an int representing the index of the primary key of a table in a database
     */
	public int getPrimaryKeyIndex() {
                return 0;
        }
	
    /**
     * @description Thrown to indicate that this class' fields have not been properly opened or closed when either openFields() or closeFields() is called.
     * @throws Exception .
     * @author Cameron Randolph.
     * @since 3-22-17.
     */
    public static class FieldsNotAccessedException extends Exception {
    	
    	private String message;
    	
    	public FieldsNotAccessedException() {
    		message = "The fields have either not been opened or closed";
    	}
    	
    	public String toString() {
    		return message;
    	}
    }
}