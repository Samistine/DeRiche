package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class FormRetrieval_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "includes/header.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("pageTitle", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("Retrieve a Form", request.getCharacterEncoding()), out, false);
      out.write("\n");
      out.write("<main>\n");
      out.write("    <h2>Form Retrieval</h2>\n");
      out.write("\n");
      out.write("    <div style=\"width: 1200px; height: 800px; border-spacing: 0; overflow: auto;\">\n");
      out.write("        <!--\n");
      out.write("            For the JSP, add a loop for the table rows, so that every Form object from the database that is registered for the current User can be loaded into the table.\n");
      out.write("            Only Forms that are not marked as being submitted already can be retrieved. Use the revision column to decide this.\n");
      out.write("        --> \n");
      out.write("        <form name=\"retrieveForm\" id=\"retrieveForm\" action=\"RetrieveServlet\" method=\"post\">\n");
      out.write("            <table id=\"myTable\" cellspacing=\"0\" cellpadding=\"1\" border=\"1\" >\n");
      out.write("                <tr>\n");
      out.write("                    <th>Date</th>\n");
      out.write("                    <th>Form ID</th>\n");
      out.write("                    <th>Form Type</th>\n");
      out.write("                </tr>\n");
      out.write("                ");

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
                
      out.write("\n");
      out.write("            </table>\n");
      out.write("    </div>\n");
      out.write("    <label for=\"id\">Form ID:</label>\n");
      out.write("    <input type=\"text\" name=\"fomrId\" id=\"fomrId\" required=\"required\">\n");
      out.write("    <input type=\"submit\" name=\"submitBtn\" id=\"submitBtn\" value=\"Retrieve Form\">\n");
      out.write("</main>");
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
