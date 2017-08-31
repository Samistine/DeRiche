package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class bodycheck_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "includes/header.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("pageTitle", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("Body Check Form", request.getCharacterEncoding()), out, false);
      out.write("\n");
      out.write("<main>\n");
      out.write("    <!-- <img src=\"image.jpg\" alt=\"Sedon\" width=\"450\" height=\"200\">  -->\n");
      out.write("    <div id=\"canvasDiv\" style=\"position: relative; width: 673px; height: 614px;\">\n");
      out.write("        <canvas id=\"layer2\" width=\"673\" height=\"514\" style=\"position: absolute; top: 18px; left: 0px; border:2px solid;\"></canvas>\n");
      out.write("        <canvas id=\"layer1\" width=\"673\" height=\"514\" style=\"position: absolute; top: 18px; left: 0px; border:2px solid;\"></canvas>\n");
      out.write("        <div style=\"position: absolute; top: 540px; left: 0px; width: 250px; height: 50px; border:2px solid;\">\n");
      out.write("            <div style=\"position:absolute;top:4px;left:10px;\">Color</div>\n");
      out.write("            <div style=\"position:absolute;top:24px;left:10px;width:15px;height:15px;background:black;\" id=\"black\" onclick=\"color(this)\"></div>\n");
      out.write("            <div style=\"position:absolute;top:24px;left:25px;width:15px;height:15px;background:blue;\" id=\"blue\" onclick=\"color(this)\"></div>\n");
      out.write("            <div style=\"position:absolute;top:4px;left:90px;\">Eraser</div>\n");
      out.write("            <div style=\"position:absolute;top:24px;left:90px;width:15px;height:15px;background:white;border:1px solid;\" id=\"white\" onclick=\"color(this)\"></div>\n");
      out.write("            <input type=\"button\" value=\"Clear Picture\" style=\"position:absolute; top:12px; left:150px; background:white; border:2px solid;\" onclick=\"Pclear()\">\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("    <form id=\"bodyForm\" name=\"bodyForm\" action=\"BodyCheckServlet\" method=\"post\" style=\"padding-top: 40px;\">\n");
      out.write("        Patient First Name:<br>\n");
      out.write("        <input type=\"text\" name=\"firstName\"><br>\n");
      out.write("        Patient Last Name:<br>\n");
      out.write("        <input type=\"text\" name=\"lastName\"><br>\n");
      out.write("        Explain what happened:<br>\n");
      out.write("        <textarea name=\"what\" rows=\"4\" cols=\"50\"></textarea><br>\n");
      out.write("        Where did it happen:<br>\n");
      out.write("        <input type=\"text\" name=\"where\"><br>\n");
      out.write("        When did it happen:<br>\n");
      out.write("        <input type=\"text\" name=\"when\"><br>\n");
      out.write("        Why did it happen:<br>\n");
      out.write("        <textarea name=\"why\" rows=\"4\" cols=\"50\"></textarea><br>\n");
      out.write("        What did you do? Describe First Aid administered and follow up:<br>\n");
      out.write("        <textarea name=\"firstAid\" rows=\"4\" cols=\"50\"></textarea><br>\n");
      out.write("        Recommendations for follow up: (Admin Only)<br>\n");
      out.write("        <textarea  name =\"recommend\" rows=\"4\" cols=\"50\"></textarea><br>\n");
      out.write("        <div id=\"sigDiv\" style=\"position: relative; width: 710px; height: 300px;\">\n");
      out.write("            <canvas id=\"sigLayer2\" width=\"710\" height=\"149\" style=\"position:absolute; top:20px; left:0px; border:2px solid;\"></canvas>\n");
      out.write("            <canvas id=\"sigLayer1\" width=\"710\" height=\"149\" style=\"position:absolute; top:20px; left:0px; border:2px solid;\"></canvas>\n");
      out.write("            <input type=\"button\" value=\"Clear Signature\" style=\"position:absolute; top:180px; left:0px; background:white; border:2px solid;\" onclick=\"Sclear()\">\n");
      out.write("        </div>\n");
      out.write("        <br><br>\n");
      out.write("        <input type=\"submit\" onClick=\"save()\" value=\"Submit\"/>  <input type=\"reset\" value=\"Clear\"/>\n");
      out.write("        <input type=\"hidden\" id=\"picURL\" value=\"\">\n");
      out.write("        <input type=\"hidden\" name=\"sigURL\" id=\"sigURL\" value=\"\"><br>\n");
      out.write("    </form><br>\n");
      out.write("    <button onclick=\"myFunction()\">Help</button>\n");
      out.write("    <script>\n");
      out.write("        function myFunction() {\n");
      out.write("            alert(\n");
      out.write("                    \"1.Mark on the chart below any abnormities observed\\n2.Enter Participant name in the field provided (First name and last name) \\n3.In the Explain what happened section, please type out how Participant received any abnormalities\\n4.In the Where / When section, please type out Where & When Participant received any abnormalities\\n5.In the What did you do? Describe First Aid administered and follow up section, please type out exactly what First Aid was administered and what steps were taken to follow up\\n6.In the Recommendations for follow up section, only if you are an Admin, please type in any recommendations you have for the participant\\n7.Enter your name in the field provided\\n8.Sign the signature box with the mouse\\n11.Select date from the Date Picker\\n\");\n");
      out.write("        }\n");
      out.write("    </script>\n");
      out.write("</main>\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "includes/footer.jsp", out, false);
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
