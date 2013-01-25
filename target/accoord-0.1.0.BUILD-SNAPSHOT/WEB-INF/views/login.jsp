<%@page import="com.accoord.util.*,com.accoord.domain.*"%>
<%AccoordUtil.getDataAccess().loadDefaultValues(); %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset=utf-8 />
<%@ page import="com.accoord.web.*" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Login</title>
</head>

<%@include file="includes.jsp"%>
<body>
<section id="wrap">
<%
String action="/accoord/resources/j_spring_security_check";
Organization organization=AccoordUtil.getOrganizationFromUrl(request.getAttribute("javax.servlet.forward.request_uri").toString());
if(organization==null)
{
	action="/accoord/checkorganization";
}
%>    
 <article>
<form method="post"  name="f" action="<%=action %>" onsubmit="return validateLoginForm(this)">

<div class="login-container">
<div class="" style="border: thin;border-color: black;">
<div class="login-label" style="float: left;"><a href="#">Register New User</a></div>
<div class="login-label" style="float: right;"><a href="#">Forgot password</a></div>
	
<div align="center" style="font: normal;font-size: 21px;padding-top: 50px;padding-left: 200px;"><%=organization==null?"Please Enter Organization Name":"Please Login" %></div>	

	<div class="login-inner">
	<%if(organization!=null) {%>
   	  <div class="login-label">Username :</div>
      <div class="login-textfield"><input validatefunc="validateUsername" validationfailedfunc="validateUsernameFailed"  type="text" name="username" id="username" /></div>
      <div class="error" id="error_client_username" ><%=I18N.getMsg("Give User Name")%></div>
      <div class="error" id="error_server_username" ><%=I18N.getMsg("Give User Name")%></div>
      <div class="clear"></div>
      
      <input type="text" name="j_username" id="j_username" style="display: none"/>
      <input type="text" name="organization" id="organization" style="display: none" value="<%=organization.getName()%>"/>
      
      <div class="login-label">Password :</div>
      <div class="login-textfield"><input type="password" name="j_password" id="password" /></div>
      <div class="error" id="error_client_password" ><%=I18N.getMsg("Give Password")%></div>
      <div class="error" id="error_server_password" ><%=I18N.getMsg("Give Password")%></div>
      <div class="clear">
      <%}else{ %>
      
      <div class="login-label">Organization :</div>
      <div class="login-textfield"><input type="text" name="organization" id="organization" validatefunc="validateUsername" validationfailedfunc="validateUsernameFailed"/></div>
      <div class="error" id="error_client_organization" ><%=I18N.getMsg("Enter Organization Name")%></div>
      <div class="error" id="error_server_organization" ><%=I18N.getMsg("Enter Organization Name")%></div>
      <div class="clear">
      
      <%} %>
      <div class="login-butn"><input name="submit"  type="submit" value="<%=organization==null?"Enter":"Login" %>" style="background-position:top right;background-image: images/login-button.jpg;height: 25px;" /></div>
      
</div>
    <div class="clear"></div>
    
</div>

</div>
</form>
</article>
</section>
</body>
</html>


  
