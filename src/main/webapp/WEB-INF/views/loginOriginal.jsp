<%@page import="com.accoord.util.*,com.accoord.domain.*"%>
<%AccoordUtil.getDataAccess().loadDefaultValues(); %>

<head>
<%@ page import="com.accoord.web.*" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Login</title>

<%@include file="includes.jsp"%>

</head>

<body>
<%
String action= request.getContextPath()+"/resources/j_spring_security_check";
Organization organization=AccoordUtil.getOrganizationFromUrl(request.getAttribute("javax.servlet.forward.request_uri").toString());
if(organization==null)
{
	action= request.getContextPath()+"/checkorganization";
}
%>    
<form method="post"  name="f" action="<%=action %>" onsubmit="return validateLoginForm(this)">

<div class="login-container">
<div class="" style="border: thin;border-color: black;">
<div class="login-label" style="float: left;"><a href="<%=request.getContextPath()%>/register">Register Your Organization</a></div>
<div class="login-label" style="float: right;"><a href="<%=request.getContextPath()%>/forgotpassword">Forgot password</a></div>
	
<div align="left" style="font: normal;font-size: 21px;padding-top: 50px;"><%=organization==null?"Organization":"Please Login" %></div>	

	<div class="login-inner">
	<%if(organization!=null) {%>
   	  <div class="login-label">Username :</div>
      <div class="login-textfield"><input validatefunc="validateUsername" validationfailedfunc="validateUsernameFailed"  type="text" name="username" id="username" /></div>
 <!--      <div class="error" id="error_client_username" ><%=I18N.getMsg("Give User Name")%></div>
      <div class="error" id="error_server_username" ><%=I18N.getMsg("Give User Name")%></div>  -->
      <div class="clear"></div>
      
      <input type="text" name="j_username" id="j_username" style="display: none"/>
      <input type="text" name="organization" id="organization" style="display: none" value="<%=organization.getName()%>"/>
      
      <div class="login-label">Password :</div>
      <div class="login-textfield"><input type="password" name="j_password" id="password" /></div>
  <!--     <div class="loginerror" id="error_client_password" ><%=I18N.getMsg("Give Password")%></div>
      <div class="loginerror" id="error_server_password" ><%=I18N.getMsg("Give Password")%></div>  -->
      <div class="clear">
      <%}else{ %>
      
      <div class="login-label">Please enter your organization:</div>
      <div class="login-textfield"><input type="text" name="organization" id="orgname" validatefunc="validateUsername" validationfailedfunc="validateUsernameFailed"/></div>
 <!--  <div class="loginerror" id="error_client_orgname" ><%=I18N.getMsg("Enter Organization Name")%></div>
      <div class="loginerror" id="error_server_orgname" ><%=I18N.getMsg("Enter Organization Name")%></div> --> 
      <div class="clear">

      <%} %>
      <div><input name="submit"  type="submit" value="<%=organization==null?"Enter":"Login" %>" style="background-position:top right;background-image: images/login-button.jpg;" /></div>
      

      
      
</div>
    <div class="clear"></div>
    
</div>

</div>
</div>
</form>
</body>


  
