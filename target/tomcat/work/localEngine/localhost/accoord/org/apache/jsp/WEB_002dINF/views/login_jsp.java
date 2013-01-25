package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.accoord.util.*;
import com.accoord.domain.*;
import com.accoord.web.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write('\n');
AccoordUtil.getDataAccess().loadDefaultValues(); 
      out.write("\n");
      out.write("\n");
      out.write("<head>\n");
      out.write("\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n");
      out.write("<title>Login</title>\n");
      out.write("\n");
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
      out.write("\n");
      out.write("\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");

String action="/accoord/resources/j_spring_security_check";
Organization organization=AccoordUtil.getOrganizationFromUrl(request.getAttribute("javax.servlet.forward.request_uri").toString());
if(organization==null)
{
	action="/accoord/checkorganization";
}

      out.write("    \n");
      out.write("<form method=\"post\"  name=\"f\" action=\"");
      out.print(action );
      out.write("\" onsubmit=\"return validateLoginForm(this)\">\n");
      out.write("\n");
      out.write("<div class=\"login-container\">\n");
      out.write("<div class=\"\" style=\"border: thin;border-color: black;\">\n");
      out.write("<div class=\"login-label\" style=\"float: left;\"><a href=\"");
      out.print(request.getContextPath());
      out.write("/register\">Register New User</a></div>\n");
      out.write("<div class=\"login-label\" style=\"float: right;\"><a href=\"");
      out.print(request.getContextPath());
      out.write("/forgotpassword\">Forgot password</a></div>\n");
      out.write("\t\n");
      out.write("<div align=\"center\" style=\"font: normal;font-size: 21px;padding-top: 50px;padding-left: 100px;\">");
      out.print(organization==null?"Organization Name":"Please Login" );
      out.write("</div>\t\n");
      out.write("\n");
      out.write("\t<div class=\"login-inner\">\n");
      out.write("\t");
if(organization!=null) {
      out.write("\n");
      out.write("   \t  <div class=\"login-label\">Username :</div>\n");
      out.write("      <div class=\"login-textfield\"><input validatefunc=\"validateUsername\" validationfailedfunc=\"validateUsernameFailed\"  type=\"text\" name=\"username\" id=\"username\" /></div>\n");
      out.write("      <div class=\"error\" id=\"error_client_username\" >");
      out.print(I18N.getMsg("Give User Name"));
      out.write("</div>\n");
      out.write("      <div class=\"error\" id=\"error_server_username\" >");
      out.print(I18N.getMsg("Give User Name"));
      out.write("</div>\n");
      out.write("      <div class=\"clear\"></div>\n");
      out.write("      \n");
      out.write("      <input type=\"text\" name=\"j_username\" id=\"j_username\" style=\"display: none\"/>\n");
      out.write("      <input type=\"text\" name=\"organization\" id=\"organization\" style=\"display: none\" value=\"");
      out.print(organization.getName());
      out.write("\"/>\n");
      out.write("      \n");
      out.write("      <div class=\"login-label\">Password :</div>\n");
      out.write("      <div class=\"login-textfield\"><input type=\"password\" name=\"j_password\" id=\"password\" /></div>\n");
      out.write("      <div class=\"error\" id=\"error_client_password\" >");
      out.print(I18N.getMsg("Give Password"));
      out.write("</div>\n");
      out.write("      <div class=\"error\" id=\"error_server_password\" >");
      out.print(I18N.getMsg("Give Password"));
      out.write("</div>\n");
      out.write("      <div class=\"clear\">\n");
      out.write("      ");
}else{ 
      out.write("\n");
      out.write("      \n");
      out.write("      <div class=\"login-label\">Organization :</div>\n");
      out.write("      <div class=\"login-textfield\"><input type=\"text\" name=\"organization\" id=\"orgname\" validatefunc=\"validateUsername\" validationfailedfunc=\"validateUsernameFailed\"/></div>\n");
      out.write("      <div class=\"error\" id=\"error_client_orgname\" >");
      out.print(I18N.getMsg("Enter Organization Name"));
      out.write("</div>\n");
      out.write("      <div class=\"error\" id=\"error_server_orgname\" >");
      out.print(I18N.getMsg("Enter Organization Name"));
      out.write("</div>\n");
      out.write("      <div class=\"clear\">\n");
      out.write("      \n");
      out.write("      ");
} 
      out.write("\n");
      out.write("      <div class=\"login-butn\"><input name=\"submit\"  type=\"submit\" value=\"");
      out.print(organization==null?"Enter":"Login" );
      out.write("\" style=\"background-position:top right;background-image: images/login-button.jpg;\" /></div>\n");
      out.write("      \n");
      out.write("</div>\n");
      out.write("    <div class=\"clear\"></div>\n");
      out.write("    \n");
      out.write("</div>\n");
      out.write("\n");
      out.write("</div>\n");
      out.write("</div>\n");
      out.write("</form>\n");
      out.write("</body>\n");
      out.write("\n");
      out.write("\n");
      out.write("  \n");
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
