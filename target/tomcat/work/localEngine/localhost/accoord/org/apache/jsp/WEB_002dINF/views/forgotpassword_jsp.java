package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.accoord.util.*;
import com.accoord.domain.*;
import com.accoord.web.*;

public final class forgotpassword_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(1);
    _jspx_dependants.add("/WEB-INF/views/includes.jsp");
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
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

      out.write('\r');
      out.write('\n');
AccoordUtil.getDataAccess().loadDefaultValues(); 
      out.write("\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("<title>Forgot Password</title>\r\n");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"/accoord/styles/jquery-ui-1.8.8.custom.css\"></link>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"/accoord/styles/jquery.jgrowl.css\"></link>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"/accoord/styles/styles.css\"></link>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/accoord/js/jquery-1.4.2.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/accoord/js/jquery-ui-1.8.8.custom.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/accoord/js/jquery.blockUI.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/accoord/js/jquery.form.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/accoord/js/jquery.MetaData.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/accoord/js/jquery.jgrowl_minimized.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/accoord/js/script.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/accoord/js/i18n/grid.locale-en.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/accoord/js/jquery.jqGrid.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");

String action=request.getContextPath()+"/forgotpasswordaction";

      out.write("    \r\n");
      out.write("<form method=\"post\"  name=\"f\" action=\"");
      out.print(action );
      out.write("\" onsubmit=\"return validateLoginForm(this)\">\r\n");
      out.write("\r\n");
      out.write("<div class=\"login-container\" style=\"height: 400px\">\r\n");
      out.write("<div class=\"\" style=\"border: thin;border-color: black;\">\r\n");
      out.write("<div class=\"login-label\" style=\"float: left;\"><a href=\"");
      out.print(request.getContextPath());
      out.write("/login\">login page</a></div>\r\n");
      out.write("\t\r\n");
      out.write("<div align=\"center\" style=\"font: normal;font-size: 21px;padding-top: 50px;padding-left: 100px;\">Forgot Password</div>\t\r\n");
      out.write("\r\n");
      out.write("\t<div class=\"login-inner\">\r\n");
      out.write("   \t  \r\n");
      out.write("      <div class=\"login-label\">Please Enter Your Email ID:</div>\r\n");
      out.write("      <div class=\"login-textfield\"><input type=\"text\" name=\"email\" id=\"email\" validatefunc=\"validateEmail\" validationfailedfunc=\"validateUsernameFailed\"/></div>\r\n");
      out.write("      <div class=\"error\" id=\"error_client_email\" >");
      out.print(I18N.getMsg("Enter Valid Email Id Used While Registering"));
      out.write("</div>\r\n");
      out.write("      <div class=\"error\" id=\"error_server_email\" >");
      out.print(I18N.getMsg("Enter Valid Email Id"));
      out.write("</div>\r\n");
      out.write("      <div class=\"clear\">\r\n");
      out.write("      \r\n");
      out.write("      \r\n");
      out.write("      <div class=\"login-butn\"><input name=\"submit\"  type=\"submit\" value=\"Submit\" style=\"background-position:top right;background-image: images/login-button.jpg;\" /></div>\r\n");
      out.write("      \r\n");
      out.write("</div>\r\n");
      out.write("    <div class=\"clear\"></div>\r\n");
      out.write("    \r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("</div>\r\n");
      out.write("</form>\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("  \r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
