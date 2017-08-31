<!DOCTYPE html>
<%@page import="Business.*"%>
<%@page import ="javax.servlet.http.HttpSession"%>
<html lang="en">
<head>
    <% 
    HttpSession ses;
        ses = request.getSession();
        
        User user = (User)ses.getAttribute("User");
%>
<title>Deriche</title>
<meta charset="utf-8">
<link href="CSSPage.css" rel="stylesheet">
<!--[if lt IE 9]>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js">
</script>
<![endif]-->
</head>
<body>
 <div id="wrapper">
  <header>
      <h1>DeRiche Agency,Inc.</h1>
	
	
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
	<li><a href="contact.html">Contact US</a></li>
  </ul>
  </nav>   
  <main>
  <h2>Welcome <%out.println(user.getFirstName());  %><%= user.getLastName() %></h2>
 <!-- <img src="image.jpg" alt="Sedon" width="450" height="200">  -->
 




  </p>
  
  
  
  
  
  
  
  
  
  <footer>
  
  Copyright &copy; 2017 2017 DeRiche Agency ,Inc.
    <a href="mailto:email@gmail.com">email@gmail.com</a>

  </footer>
  
  
</div>

</body>
</html>