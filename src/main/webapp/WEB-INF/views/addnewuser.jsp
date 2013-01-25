<%@page import="com.accoord.util.*,com.accoord.domain.*"%>
<%@page import="org.springframework.security.core.*,org.springframework.security.core.context.*,com.accoord.web.login.*,java.util.*" %>

<%AccoordUtil.getDataAccess().loadDefaultValues(); %>
<head>
<%@ page import="com.accoord.web.*,com.accoord.domain.*,com.accoord.util.*" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Add User</title>

<%@include file="includes.jsp"%>

</head>

<body>
<%
String action=request.getContextPath()+"/addnewuseraction";
if(request.isUserInRole("ORG_ADMIN"))
{
%>    
<form method="post"  name="f" action="<%=action %>" onsubmit="return validateLoginForm(this)">

<div class="login-container" style="height: 400px">
<div class="" style="border: thin;border-color: black;">
	
<div align="left" style="font: normal;font-size: 21px;padding-top: 50px;">Add User</div>	

	<div class="login-inner">


 	  <div class="login-label">Username :</div>
      <div class="login-textfield"><input validatefunc="validateUsername" validationfailedfunc="validateUsernameFailed"  type="text" name="username" id="username" /></div>
<!--        <div class="error" id="error_client_username" ><%=I18N.getMsg("Enter User Name")%></div>
      <div class="error" id="error_server_username" ><%=I18N.getMsg("Enter User Name")%></div>  -->
      <div class="clear"></div>
     
   	  
      <div class="login-label">Password:</div>
      <div class="login-textfield"><input type="password" name="newpassword" id="newpassword" validatefunc="validateUsername" validationfailedfunc="validateUsernameFailed"/></div>
<!--       <div class="error" id="error_client_newpassword" ><%=I18N.getMsg("Enter a password")%></div>
      <div class="error" id="error_server_newpassword" ><%=I18N.getMsg("Enter a password")%></div>  -->
      <div class="clear">

     

      <div class="login-label">Confirm Password:</div>
      <div class="login-textfield"><input type="password" name="confirmpassword" id="confirmpassword" validatefunc="validateConfirmNewPassword" validationfailedfunc="validateUsernameFailed"/></div>
<!--       <div class="error" id="error_client_confirmpassword" ><%=I18N.getMsg("Passwords not matching")%></div>
      <div class="error" id="error_server_confirmpassword" ><%=I18N.getMsg("Enter a password")%></div>  -->
      <div class="clear">
     
     <div class="login-label">Email :</div>
      <div class="login-textfield"><input type="text" name="email" id="email" validatefunc="validateEmail" validationfailedfunc="validateUsernameFailed"/></div>
<!--       <div class="error" id="error_client_email" ><%=I18N.getMsg("Enter Valid Admin Email Id")%></div>
      <div class="error" id="error_server_email" ><%=I18N.getMsg("Enter Admin Email Id")%></div>  -->
      <div class="clear">
      
       
      
      <div class="login-butn"><input name="submit"  type="submit" value="Submit" style="background-position:top right;background-image: images/login-button.jpg;" /></div>
      
</div>
    <div class="clear"></div>
    
</div>

</div>
</div>
</form>
<%}else
	{%>
	
	Not Authorized
	<%} %>
</body>


  
