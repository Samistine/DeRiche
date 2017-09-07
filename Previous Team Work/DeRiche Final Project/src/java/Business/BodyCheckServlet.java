/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Business.User;
/**
 *
 * @author Carl Moon
 * @author Cameron Randolph
 */
@WebServlet(name = "BodyCheckServlet", urlPatterns = {"/BodyCheckServlet"})
public class BodyCheckServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String canvasData;
        String sigData;
        String firstName;
        String lastName;
        String what;
        String where;
        String when;
        String why;
        String firstAid;
        String recommend;
        
        canvasData = request.getParameter("picURL");
        sigData = request.getParameter("sigURL");
        firstName = request.getParameter("firstName");
        lastName = request.getParameter("lastName");
        what = request.getParameter("what");
        where = request.getParameter("where");
        when = request.getParameter("when");
        why = request.getParameter("why");
        firstAid = request.getParameter("firstAid");
        recommend = request.getParameter("recommend");
        
        FormInfo info = new FormInfo(FormInfo.BODY_CHECK);
        info.setValues(new String[] {canvasData, sigData, firstName, lastName, what, where, when, why, firstAid, recommend});

        Calendar cal = new GregorianCalendar();
        String date = cal.YEAR +"-"+ cal.MONTH +"-"+ cal.DAY_OF_MONTH;
                //+" "+ cal.HOUR_OF_DAY+":"+cal.MINUTE+":"+cal.SECOND;
        
        HttpSession session;
        session = request.getSession();
        
        
        //session.setAttribute("User", User);
       
        User user = (User)session.getAttribute("User");
        

        Forms form = new Forms();
        
        form.insertDB(user.getUserId(), "7", "BODY_CHECK", "test", date, "1");

        RequestDispatcher dispatcher = request.getRequestDispatcher("FormRetrieval.jsp");
        dispatcher.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}