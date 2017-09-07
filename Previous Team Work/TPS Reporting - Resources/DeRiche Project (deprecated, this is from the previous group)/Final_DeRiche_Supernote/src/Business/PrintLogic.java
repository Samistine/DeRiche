/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import static Data_Access.Data.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;

/**
 * Creates a print object.
 * @author Steven
 */
public class PrintLogic {
    
    String note;
    String parName;
    String userName;
    String userTitle;
    Timestamp date;
    String location;
    String medNum;
    ArrayList printNotes = new ArrayList();
    JFileChooser jfc = new JFileChooser();
    
    /**
     * Sets the note in the print object.
     * @param note String
     */
    public void setNote(String note){
        this.note = note;
        System.out.println("printlogic setNote note: " + this.note);
    }
    
    /**
     * Sets the Participants name in the print object.
     * @param name String
     */
    public void setParName(String name){
        this.parName = name;
        System.out.println("printLogic setParName name: " + this.parName);
    }
    
    /**
     * Sets the User Name in the print object.
     * @param name String
     */
    public void setUserName(String name){
        this.userName = name;
        System.out.println("printLogic setUserName name: " + this.userName);
    }
    
    /**
     * Sets the Users Title in the print object.
     * @param title String
     */
    public void setUserTitle(String title){
        this.userTitle = title;
        System.out.println("printLogic setUserTitle title: " + this.userTitle);
    }
    
    /**
     * Sets the date the note was created in the print object.
     * @param date Timestamp
     */
    public void setDate(Timestamp date){
        this.date = date;
        System.out.println("printLogic setDate Date: " + this.date);
    }
    
    /**
     * Sets the Medicaid Number in the print object for use in titling save files..
     * @param med String
     */
    public void setMedNum(String med){
        this.medNum = med;
    }
    
    /**
     *
     * @param location
     */
    public void setLocation(String location){
        this.location = location;
    }
    
    /**
     * Allow for retrieval of note from print object.
     * @return note String
     */
    public String getNote(){
        return note;
    }
    
    /**
     * Allow for retrieval of Participants name from print object.
     * @return parName String
     */
    public String getParName(){
        return parName;
    }
    
    /**
     * Allow for retrieval of User Name from print object.
     * @return userName String
     */
    public String getUserName(){
        return userName;
    }
    
    /**
     * Allow for retrieval of title from print object.
     * @return userTitle String
     */
    public String getUserTitle(){
        return userTitle;
    }
    
    /**
     * Allow for retrieval of date from print object.
     * @return date Timestamp
     */
    public Timestamp getDate(){
        return date;
    }
    
    /**
     * Allow for retrieval of Medicaid Number from print object for titling the save file.
     * @return medNum String
     */
    public String getMedNum(){
        return medNum;
    }
    
    /**
     *
     * @return
     */
    public String getLocation(){
        return location;
    }
    
    /**
     * Very basic Constructor
     */
    public PrintLogic(){
    }
    
    /**
     * This adds all the variables to the print object.
     * @param noteId int
     * @param location String
     * @return print object
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public PrintLogic print(int noteId, String location) throws ClassNotFoundException, SQLException{
        System.out.println("PrintLogic noteId constructor.");
        PrintLogic print = new PrintLogic();
        NoteLogic note = new NoteLogic();
        ParticipantLogic participant = new ParticipantLogic();
        UserLogic user =new UserLogic();
        
        note = note.select(noteId);
        participant = participant.select(note.getParticipantID());
        user = user.select(note.getUserID());
        print.setNote(note.getText());
        print.setParName(participant.getParticipantFirstName()+" "+participant.getParticipantLastName());
        print.setMedNum(participant.getMedicaidNumber());
        print.setUserName(user.getFirstName()+" "+user.getLastName());
        print.setUserTitle(user.getUserTitle());
        print.setDate(note.getTimeStarted());
        print.setLocation(location);
        
        return print;
    }
    
    /**
     * Builds an ArrayList of print objects
     * @param location String
     * @param participantID int
     * @param startDate String
     * @param endDate String
     * @return printNotes ArrayList
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList getPrintList(String location, int participantID,String startDate,String endDate) throws ClassNotFoundException, SQLException{
        try{
            Class.forName(driver);
            try(Connection connection = DriverManager.getConnection(databaseTime, databaseUser, databasePass)){
                Statement statement = connection.createStatement();
                System.out.println("You are at the getPrintList method");
                String sql = "Select NoteID from Note where ParticipantID = "+participantID+" and Time_Started Between '"+startDate+"%' AND '"+endDate+"%';";
                System.out.println(sql);
                ResultSet result = statement.executeQuery(sql);
                while (result.next()){
                    PrintLogic print = new PrintLogic();
                    print = print.print(result.getInt("noteID"), location);
                    printNotes.add(print);
                }
            }
            return printNotes;
        }catch(SQLException ext){
            throw ext;
        }
    }
    
    /**
     * this saves a note in the database as a text file in the NetBeans project folder.
     * the file is named: med_Num(the medicaid number)_timestamp(the timestamp of note creation).txt
     * @param print object
     * @param jfc JFileChooser
     * @throws FileNotFoundException
     */
    public void savePrint(PrintLogic print, JFileChooser jfc) throws FileNotFoundException{
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        String string  = dateFormat.format(print.getDate());
        System.out.println(string);
        File dir = new File(jfc.getSelectedFile()+"\\med_Num("+print.getMedNum()+")");
        dir.mkdir();
        String fileName = (jfc.getSelectedFile()+"\\med_Num("+print.getMedNum()+")\\med_Num("+print.getMedNum()+")_timestamp("+string+")");
        //defualt file save;
//        String fileName = ("med_Num("+print.getMedNum()+")_timestamp("+string+")");
        java.io.File file = new java.io.File(fileName);
        
        if(file.exists()){
            System.out.println("fail to save file");
        }
        else{
            try (java.io.PrintWriter output = new java.io.PrintWriter(file)) {
                output.println(print.getLocation());
                output.println(print.getParName());
                output.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
                output.println("("+string+")");
                output.println(print.getNote());
                output.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
                output.println("");
                output.println("");
                output.println(print.getUserName());
                output.println(print.getUserTitle());
                
                output.close();
            }
            file.setReadOnly();
            System.out.println("file saved");
        }
    }
    
    /**
     * This class prints out text documents from the main program.
     * @param print PrintLogic
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void printPrint(PrintLogic print) throws FileNotFoundException, IOException{
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        String string  = dateFormat.format(print.getDate());
        System.out.println(string);
        
        String fileName = ("med_Num("+print.getMedNum()+")_timestamp("+string+")");
        java.io.File file = new java.io.File(fileName);
        boolean bool;
        
        String path = file.getPath();
        System.out.println(path);
        file.delete();
        if(file.exists()){
            System.out.println("fail to save file");
            
        }else{
            try (java.io.PrintWriter output = new java.io.PrintWriter(file)) {
                output.println(print.getLocation());
                output.println(print.getParName());
                output.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
                output.println("("+string+")");
                output.println(print.getNote());
                output.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
                output.println("");
                output.println("");
                output.println(print.getUserName());
                output.println(print.getUserTitle());
                
                output.close();
            }
            file.setReadOnly();
            System.out.println("file saved");
        
            JEditorPane text = new JEditorPane("file:" + fileName);
            try {
                PrintService service = PrintServiceLookup.lookupDefaultPrintService();
                text.print(null, null, false, service, null, false);
            } catch (PrinterException ex) {
                Logger.getLogger(PrintLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
              
        file.createNewFile();
            System.out.println("Garrrrr");
            
        bool = file.delete();
            System.out.println(bool);  
        
    }
    
    /**
     * this works the ArrayList Of print object through the save print method.
     * @param printList ArrayList
     * @throws FileNotFoundException
     */
    public void savePrintList(ArrayList printList) throws FileNotFoundException{
        PrintLogic print = new PrintLogic();
        jfc = print.directoryPicker();
        for(int i = 0; i < printNotes.size();i++){
//            PrintLogic print = new PrintLogic();
            print = ((PrintLogic)(printList.get(i)));
            print.savePrint(print, jfc);
        }
    }
    
    /**
     * printPrintList runs an Array of PrintLogic objects through printPrint()
     * @param printList ArrayList
     * @throws IOException
     */
    public void printPrintList(ArrayList printList) throws IOException{
        PrintLogic print = new PrintLogic();
        for(int i = 0; i< printList.size();i++){
            print = ((PrintLogic)(printList.get(i)));
            print.printPrint(print);
            
        }
    }
       
    /**
     * Allows one to choose the save location of a Document.
     * @return jfc JFileChooser
     */
    public JFileChooser directoryPicker(){
        String directory = null;
        
        jfc.setCurrentDirectory(new java.io.File("."));
        jfc.setDialogTitle(directory);
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        jfc.setAcceptAllFileFilterUsed(false);
        
        if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
      System.out.println("getCurrentDirectory(): " 
         +  jfc.getCurrentDirectory());
      System.out.println("getSelectedFile() : " 
         +  jfc.getSelectedFile());
      }
    else {
      System.out.println("No Selection ");
      }
     
        
        System.out.println(directory);
        return jfc;
    }
    /**
     * a basic display for print objects.
     */
    public void display(){
        System.out.println("new Note");
        System.out.println(note);
        System.out.println(parName);
        System.out.println(userName);
        System.out.println(userTitle);
        System.out.println(date);
        System.out.println(location);
    }
    
    /**
     * a display for an ArrayList of print objects.
     * @param list: ArrayList
     */
    public void displayList(ArrayList list){
        for (int i = 0; i < printNotes.size(); i++){
            PrintLogic print = new PrintLogic();
            print = ((PrintLogic)(list.get(i)));
            print.display();
        }
    }
    
    /**
     * Just a simple Test to make sure it works before adding it to the main program.
     * @param args
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws java.io.FileNotFoundException
     */
    public static void main(String [] args) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException{
        //test single run file
        String location = "testLocation";
        PrintLogic print = new PrintLogic();
        print = print.print(6, location);
        print.display();
        print.savePrint(print, print.directoryPicker());
        print.printPrint(print);
        //test Array running side
        System.out.println("Before printList");
        ArrayList finalList = new ArrayList();
        finalList = print.getPrintList(location, 3, "2016-10-1", "2016-12-1");
        System.out.println("After printList");
        print.displayList(finalList);
        print.savePrintList(finalList);
        print.printPrintList(finalList);
    }
    
}
