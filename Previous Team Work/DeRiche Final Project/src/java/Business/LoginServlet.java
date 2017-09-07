/**
 * @author R. Carl Moon
 *
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business;

import dataAccess.DBConnection.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name="LoginServlet", urlPatterns={"/LoginServlet"})
public class LoginServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
               String id = request.getParameter("id");
           String pw = request.getParameter("pw");
           
            User user = new User();
          System.out.println("LoginServlet loaded Succcessfully!!");
         //Object[] array = dataAccess.DBConnection.select(user, id, 2, true);
         
         User us = new User();
        try
    {
        
        us.selectDB(id);
        
        us.display();
    }
    catch(SQLException e)
    {
        System.out.println(e);
    }
            HttpSession ses1;
            ses1 = request.getSession();
            ses1.setAttribute("User",us);
           /*
           for(int i=0; i< array.length; i++)
           {
               System.out.print("(String)array[0]"+array[i]);
           }
            
            user.setUserName(array[0]+"");
            user.setPassword(array[2]+"");
            //user.setT(array[1]);
            /*
            
            System.out.print("Here is the info for the Array");
            //array.toString();
           
             ses1.setAttribute("user", user);
            
             /*
           //String[] array = database_connection.db_connection(id);
           System.out.println(array[0] +""+ array[1]);*/
           //if (array[1].equals(id) && array[2].equals(pw)) {
          
           boolean isEmpty = us.getPassword() == null || us.getPassword().trim().length() == 0;
            if (isEmpty) 
            {
                System.out.println("String is Empty");
                System.out.println("isEmpty= "+isEmpty);
            
               System.out.println("Log In Failed");
               RequestDispatcher rd;
               rd = request.getRequestDispatcher("error.jsp");
               rd.forward(request,response);
            } else if (us.getUserName().equals(id) && us.getPassword().equals(pw)) {
                System.out.println("Logged In!!!");
                
                RequestDispatcher rd;
                rd = request.getRequestDispatcher("dbhome.jsp");
                rd.forward(request,response);
                
            } else {
                System.out.println("Log In Failed");
                RequestDispatcher rd;
                rd = request.getRequestDispatcher("error.jsp");
                rd.forward(request,response);
            }
           
        }
    } 

        
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
