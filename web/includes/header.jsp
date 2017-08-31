<%-- 
    Author: Syed A.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>${param.pageTitle}</title>
        <meta charset="utf-8">
        <link href="includes/style.css" rel="stylesheet">
    </head>
    <body onload="init()">
        <div id="wrapper" onload="speak()">
            <header>
                <h1>DeRiche Agency, Inc.</h1>
            </header>
            <nav>
                <ul>
                    <li><a href="dbhome.jsp">Home</a></li>
                    <li><a href="bodycheck.jsp">Create Form</a></li>
                    <ul class="nav-dropdown">
                        <li><a href="bodycheck.jsp">Body Check</a></li>
                        <li><a href="internal_incident.jsp">Internal Incident</a></li>
                    </ul>
                    <li><a href="FormRetrieval.jsp">Retrieve Form</a></li>
                    <li><a href="contact.jsp">Contact US</a></li>
                </ul>
            </nav>