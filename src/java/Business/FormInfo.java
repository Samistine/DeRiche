package Business;

/**
 * A class that represents one form, of any type, in the the DeRiche project.<br>This class holds all of the data contained within a form including Base64 images from pictures that can be drawn on, TextFields, and TextAreas.<br><br>
 * The class also contains preset {java.lang.String} arrays that represent all the tags for any form in the DeRiche project.
 *
 * @author Cameron Randolph
 * @version 1.0
 */
public class FormInfo {
    
    public static final int BODY_CHECK = 1;
    public static final int SEIZURE_ACTIVITY_CHECKLIST = 2;
    public static final int SINGLE_ABSENCE = 3;
    public static final int WEIGHTBP_CHART = 4;
    public static final int COMMUNITY_CONNECTION_ACTIVITY_SHEET = 5;
    public static final int BLANK_MONTHLY_DATA_TRACKING_LOG = 6;
    public static final int INTERNAL_INCIDENT_FORM = 7;
    public static final int REPOSITIONING_FORM = 8;
    
    private Entry[] entries;
    
    /**
     * This constructor is used locally in the static decode() method. No implementation of this constructor should/can be used outside of this class.
     */
    private FormInfo() {
    	
    }
    
    /**
     * Creates a new FormInfo object with Entry tags set to the values specified by the type parameter passed.
     * 
     * @param type the type of form as specified by the static final int values in this class
     */
    public FormInfo(int type) {
        initTags(type);
    }
    
    /**
     * Sets the value for one Entry object to the passed parameter "value", as specified by the "tag" parameter.
     * 
     * @param tag the indentifier for the Entry object to which the value is to be changed
     * @param value the value that will be used to assign to the tag, matching the tag parameter passed, in the array of Entry objects
     */
    public void setValue(String tag, String value) {
        for (int i = 0; i < entries.length; i++) { 
            if (entries[i].getTag().equals(tag.toUpperCase())) {
                entries[i].setValue(value);
            }
        }
    }
    
	/**
	 * Sets all entries in the order they are listed in the array of Entry object to the corresponding values passed as the parameter as a {java.lang.String} array.
	 * 
	 * @param values the array of values that will be used to assign values to all tags in the array of Entry objects
	 */
    public boolean setValues(String[] values) {
        if (values.length != entries.length) {
            return false;
        }
        
        for (int i = 0; i < entries.length; i++) {
            entries[i].setValue(values[i]);
        }
        
        return true;
    }
    
    /**
     * Converts all the data in this FormInfo object to a single {java.lang.String} which can be saved to a database.
     * 
     * @return a encoded {java.lang.String} version of all the data held within this FormInfo object
     */
    public String encode() {
    	
        String string = new String();
        String delimiter = new String();
        
        char var;
        
		for (int i = 0; i < 7; i++) {
			var = ((char)((int)(Math.random() * 25) + 65));
			
			if (isIllegalChar(var)) {
				i--;
				
				continue;
			}
			
			
			delimiter += var;
			
			if (i == 6) {
				for (int r = 0; r < entries.length; r++) {
					if (entries[r].getValue().contains(delimiter) || entries[r].getTag().contains(delimiter)) {
						i = 0;
						
						delimiter = new String();
						break;
					}
				}
			}
		}
		
        for (int i = 0; i < entries.length; i++) {
            string += entries[i].getTag() + delimiter + entries[i].getValue() + delimiter;
        }
        
        return string;
    }
    
    /**
     * Converts an encoded {java.lang.String} to a new FormInfo object with all of its tag/value pairs set to the values held within the encoded {java.lang.String}.
     * 
     * @param encodedString the {java.lang.String} that was encoded by the encode() method
     * @return a FormInfo object with all of its values set to the values held within the encoded {java.lang.String}
     */
    public static FormInfo decode(String encodedString) {
    	
    	FormInfo info = new FormInfo();
    	
    	String delimiter = encodedString.substring(encodedString.length() - 7, encodedString.length());
    	
    	String[] array = encodedString.split(delimiter);
    	
    	info.setEntries(new Entry[array.length / 2]);
    	
    	for (int i = 0; i < info.getEntries().length; i++) {
    		info.getEntries()[i] = new Entry(array[i * 2], array[(i * 2) + 1]);
    	}
        
    	return info;
    }
    
    public int getLength() {
    return entries.length;
    }
    
    /**
     * Assigns descriptive tags to all entries for this FormInfo object.
     * 
     * @param type the type of form as specified by the static final int values in this class
     */
    private void initTags(int type) {
        String[] tags = null;
        
        if (type == BODY_CHECK) {
    	    tags = new String[] {"CANVASURI", "SIGURI", "FIRSTNAME", "LASTNAME", "WHAT", "WHERE", "WHEN", "WHY", "FIRSTAID", "RECOMMEND"};
            
        } else if (type == SEIZURE_ACTIVITY_CHECKLIST) {
            //TO DO tags = new String[] { Within this String[] you should have all of the fields needed for making the type of form that's identified bt the "type" parameter. };
            
        } else if (type == SINGLE_ABSENCE) {
            //TO DO tags = new String[] { Within this String[] you should have all of the fields needed for making the type of form that's identified bt the "type" parameter. };
            
        } else if (type == WEIGHTBP_CHART) {
            //TO DO tags = new String[] { Within this String[] you should have all of the fields needed for making the type of form that's identified bt the "type" parameter. };
            
        } else if (type == COMMUNITY_CONNECTION_ACTIVITY_SHEET) {
            //TO DO tags = new String[] { Within this String[] you should have all of the fields needed for making the type of form that's identified bt the "type" parameter. };
            
        } else if (type == BLANK_MONTHLY_DATA_TRACKING_LOG) {
            //TO DO tags = new String[] { Within this String[] you should have all of the fields needed for making the type of form that's identified bt the "type" parameter. };
            
        } else if (type == INTERNAL_INCIDENT_FORM) {
            //TO DO tags = new String[] { Within this String[] you should have all of the fields needed for making the type of form that's identified bt the "type" parameter. };
            
        } else if (type == REPOSITIONING_FORM) {
            //TO DO tags = new String[] { Within this String[] you should have all of the fields needed for making the type of form that's identified bt the "type" parameter. };
            
        }
        
        entries = new Entry[tags.length];
        
        for (int i = 0; i < entries.length; i++) {   
            entries[i] = new Entry(tags[i], "");
        }
        
        /*for (int i = 0; i < entries.length; i++) {   
            System.out.println(entries[i].getTag());
        }*/
    }
    
    /**
     * Tells the encode() method whether or not the random characters generated for the delimiter are suitable for use with the split() method in {java.lang.String}.
     * 
     * @param var the character to be checked against all known illegal characters
     * @return a value telling whether or not the passed character is illegal to use
     */
	private static boolean isIllegalChar(char var) {
		
		//For some reason the split method deosn't work right with these values. Sometimes it outright throws an Exception for pattern errors!
		if (var == '$' || var == '(' || var == ')' || var == '*' || var == '+' || var == '.' || var == '?' || var == '[' || var == '\\' || var == '^' || var == '{' || var == '|') {
			return true;
			
		} else {
			return false;
			
		}
	}
	
	/**
	 * Sets all entries in the order they are listed in the array of Entry object to the corresponding values passed as the parameter as an array.
	 * 
	 * @param entries
	 */
	private void setEntries(Entry[] entries) {
		this.entries = entries;
	}
	
	/**
	 * Retrieves the array of Entry objects stored in this FormInfo object.
	 * 
	 * @return the array of Entry objects stored in this FormInfo object
	 */
	private Entry[] getEntries() {
		return entries;
	}
	
	@Override
    public String toString() {
        
        String string = new String();
        
        for (int i = 0; i < entries.length; i++) {
            string += entries[i].toString() + "\n";
        }
        
        return "Class = FormInfo: Data =\n" + string;
    }
    
    /**
     * A class that represents one tag/value pair for any form in the the DeRiche project.
     * 
     * @author Cameron Randolph
     * @version 1.0
     */
    private static class Entry {
    
        private String tag;
        private String value;
        
        /**
         * Creates a new instance of an Entry object.
         * 
         * @param tag
         * @param value
         */
        public Entry(String tag, String value) {
            this.tag = tag;
            this.value = value;
        }
        
        public void setTag(String tag) {
            this.tag = tag;
        }
        
        public void setValue(String value) {
            this.value = value;
        }
        
        public String getTag() {
            return tag;
        }
        
        public String getValue() {
            return value;
        }
        
        @Override
        public String toString() {
            return new String("Class = Entry | Tag = " + tag + " | Value = " + value);
        }
    }
    
    public static void main(String[] args) {
        
        FormInfo info = new FormInfo(FormInfo.BODY_CHECK);
        
        info.setValues(new String[] {"canvasData/", "sigData", "Pizza", "Hambo", "Fall", "Kitchen", "9:00am", "They Slipped", "N/A", "N/A"});
        
        System.out.println(info.encode());
        
        info = FormInfo.decode((info.encode()));
        
        System.out.println(info);
    }
}