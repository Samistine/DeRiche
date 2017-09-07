<%-- 
    R. Carl Moon II
    Login.jsp

--%>

<%@page import="java.sql.SQLException"%>
<%@page import="Business.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Deriche</title>
<meta charset="utf-8">
<link href="CSSPage.css" rel="stylesheet">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title align="center">Customer Login</title>
    </head>
    <body>
        <main> 
        <h1 align="center">Please Login</h1>
        <form name="LoginForm" action="LoginServlet" method="post">
            <div align="center">ID:<input type="text" name="id" value=""autofocus></div>
            <br>
            <div align="center">PW:<input type="password" name="pw" value=""></div>
            <br>
            <div align="center">
                <input type="submit" name="LoginBtn" value="Login">
                 <input type="reset" name="ClearBtn" value="Clear"> 
            </div>
        </form>
        </main></body>
</html>
