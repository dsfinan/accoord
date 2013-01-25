<%@page import="com.accoord.util.*,com.accoord.domain.*"%>
<%AccoordUtil.getDataAccess().loadDefaultValues(); %>

<head>
<%@ page import="com.accoord.web.*,com.accoord.domain.*,com.accoord.util.*" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Change Password</title>
<%@page import="org.springframework.security.core.*,org.springframework.security.core.context.*,com.accoord.web.login.*,java.util.*" %>

<%@include file="includes.jsp"%>

</head>

<body>
<%
Authentication a=SecurityContextHolder.getContext().getAuthentication();
AccoordUserDetails aud=(AccoordUserDetails)(a.getPrincipal());
String action=request.getContextPath()+"/changepasswordaction";

%>    
<form method="post"  name="f" action="<%=action %>" onsubmit="return validateLoginForm(this)">

<div class="login-container" style="height: 400px">
<div class="" style="border: thin;border-color: black;">
	
<div align="left" style="font: normal;font-size: 21px;padding-top: 50px;">Change Password</div>	

	<div class="login-inner">
      <div class="login-label">Old Password:</div>
      <div class="login-textfield"><input type="password" name="password" id="password" validatefunc="validateUsername" validationfailedfunc="validateUsernameFailed"/></div>
<!--       <div class="error" id="error_client_password" ><%=I18N.getMsg("Enter old password")%></div>
      <div class="error" id="error_server_password" ><%=I18N.getMsg("Enter old password")%></div>  -->
      <div class="clear">

   	  
      <div class="login-label">New Password:</div>
      <div class="login-textfield"><input type="password" name="newpassword" id="newpassword" validatefunc="validateUsername" validationfailedfunc="validateUsernameFailed"/></div>
<!--       <div class="error" id="error_client_newpassword" ><%=I18N.getMsg("Enter a password")%></div>
      <div class="error" id="error_server_newpassword" ><%=I18N.getMsg("Enter a password")%></div>   -->
      <div class="clear">

 	  <input style="display:none" value="<%=aud.getUsername()%>" name="username"></input>
      <input style="display:none" value="<%=aud.getOrganization()%>" name="organization"></input>
     

      <div class="login-label">Confirm New Password:</div>
      <div class="login-textfield"><input type="password" name="confirmpassword" id="confirmpassword" validatefunc="validateConfirmNewPassword" validationfailedfunc="validateUsernameFailed"/></div>
<!--       <div class="error" id="error_client_confirmpassword" ><%=I18N.getMsg("Passwords not matching")%></div>
      <div class="error" id="error_server_confirmpassword" ><%=I18N.getMsg("Enter a password")%></div>   -->
      <div class="clear">
      
      
      <div class="login-butn"><input name="submit"  type="submit" value="Submit" style="background-position:top right;background-image: images/login-button.jpg;" /></div>
      
</div>
    <div class="clear"></div>
    
</div>

</div>
</div>
</form>
</body>


  
