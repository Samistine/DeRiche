<%-- 
    Adjuster: Syed A.
--%>
<jsp:include page="includes/header.jsp">
    <jsp:param name="pageTitle" value="Retrieve a Form"/>
</jsp:include>
<%@page import="Business.FormInfo"%>
<%@page import="dataAccess.Accessible"%>
<%@page import="Business.Forms"%>
<%@page import="dataAccess.DBConnection"%>
<%@page import="Business.User"%>
<%@page trimDirectiveWhitespaces="true" %>
<main>
    <h2>Form Retrieval</h2>
    <div style="margin-bottom: 50px; width: 800px;">
        <!--
            For the JSP, add a loop for the table rows, so that every Form object from the database that is registered for the current User can be loaded into the table.
            Only Forms that are not marked as being submitted already can be retrieved. Use the revision column to decide this.
        --> 
        <form name="retrieveForm" id="retrieveForm" action="RetrieveServlet" method="post">
            <table id="myTable" cellspacing="0" cellpadding="1" border="1" >
                <tr>
                    <th>Date</th>
                    <th>Form ID</th>
                    <th>Form Type</th>
                </tr>
                <%
                    try {
                        HttpSession s1 = request.getSession();
                        User user = (User) s1.getAttribute("User");
                        Object[][] forms = DBConnection.select(new Forms(), "-18", 6, true, false);
                        if (user.getClearance().equals("1")) {
                            for (int i = 0; i < forms.length; i++) {
                                String temp1 = forms[i][1] + "";
                                String temp6 = forms[i][6] + "";
                                if (temp1.equals(user.getUserId()) && temp6.equals("1")) {
                                    out.println("<tr>");
                                    out.println("<td>" + forms[i][5] + "</td>");
                                    out.println("<td>" + forms[i][0] + "</td>");
                                    out.println("<td>" + forms[i][3] + "</td>");
                                    out.println("</tr>");
                                }
                            }
                        } else if (user.getClearance().equals("2")) {
                            for (int i = 0; i < forms.length; i++) {
                                String temp6 = forms[i][6] + "";
                                if (temp6.equals("2")) {
                                    out.println("<tr>");
                                    out.println("<td>" + forms[i][5] + "</td>");
                                    out.println("<td>" + forms[i][0] + "</td>");
                                    out.println("<td>" + forms[i][3] + "</td>");
                                    out.println("</tr>");
                                }
                            }
                        } else {
                            for (int i = 0; i < forms.length; i++) {
                                if ((forms[i][0] + "").equals("295")) {
                                    FormInfo info = FormInfo.decode(String.valueOf(forms[i][4]));
                                    System.out.println(info.getLength());
                                    //System.out.println(info.toString());
                                }
                                out.println("<tr>");
                                out.println("<td>" + forms[i][5] + "</td>");
                                out.println("<td>" + forms[i][0] + "</td>");
                                out.println("<td>" + forms[i][3] + "</td>");
                                out.println("</tr>");
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                %>
            </table>
    </div>
    <label for="id">Form ID:</label>
    <input type="text" name="fomrId" id="fomrId" required="required">
    <input type="submit" name="submitBtn" id="submitBtn" value="Retrieve Form">
</main>