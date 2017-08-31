package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.SQLException;
import Business.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("<head>\n");
      out.write("<title>Deriche</title>\n");
      out.write("<meta charset=\"utf-8\">\n");
      out.write("<link href=\"CSSPage.css\" rel=\"stylesheet\">\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title align=\"center\">Customer Login</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <main> \n");
      out.write("        <h1 align=\"center\">Please Login</h1>\n");
      out.write("        <form name=\"LoginForm\" action=\"LoginServlet\" method=\"post\">\n");
      out.write("            <div align=\"center\">ID:<input type=\"text\" name=\"id\" value=\"\"autofocus></div>\n");
      out.write("            <br>\n");
      out.write("            <div align=\"center\">PW:<input type=\"password\" name=\"pw\" value=\"\"></div>\n");
      out.write("            <br>\n");
      out.write("            <div align=\"center\">\n");
      out.write("                <input type=\"submit\" name=\"LoginBtn\" value=\"Login\">\n");
      out.write("                 <input type=\"reset\" name=\"ClearBtn\" value=\"Clear\"> \n");
      out.write("            </div>\n");
      out.write("        </form>\n");
      out.write("        </main></body>\n");
      out.write("</html>\n");
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
