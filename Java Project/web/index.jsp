<%-- 
    Author: Syed A.
    TODO: DELETE THIS
--%>
<jsp:include page="includes/header.jsp">
    <jsp:param name="pageTitle" value="Home | Login"/>
</jsp:include>
<main> 
    <h2 align="center">Please Login</h1>
        <form name="LoginForm" action="LoginServlet" method="post">
            <div align="center">ID: <input type="text" name="id" value=""autofocus></div>
            <br>
            <div align="center">PW: <input type="password" name="pw" value=""></div>
            <br>
            <div align="center">
                <input type="submit" name="LoginBtn" value="Login">
                <input type="reset" name="ClearBtn" value="Clear"> 
            </div>
        </form>
</main>
<jsp:include page="includes/footer.jsp"/>
