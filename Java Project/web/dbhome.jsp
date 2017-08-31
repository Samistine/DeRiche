<!DOCTYPE html>
<%-- 
    Adjuster: Syed A.
--%>
<jsp:include page="includes/header.jsp">
    <jsp:param name="pageTitle" value="Body Check Form"/>
</jsp:include>
<%@page import="Business.*"%>
<%@page import ="javax.servlet.http.HttpSession"%>
<main>
    <%
        HttpSession ses = request.getSession();
        User user = (User) ses.getAttribute("User");
        if (user == null) {
            // Redirect to login page if not logged in. - Syed A.
            response.sendRedirect("index.jsp");
        } else {
            out.println("<h2>Welcome " + user.getFirstName() + user.getLastName() + "</h2>");
        }
    %>
</main>
<jsp:include page="includes/footer.jsp"/>