<%@page import="com.accoord.util.*,com.accoord.domain.*"%>
<%AccoordUtil.getDataAccess().loadDefaultValues(); %>

<head>
<%@ page import="com.accoord.web.*" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Register</title>

<%@include file="includes.jsp"%>

</head>

<body>
<%
String action=request.getContextPath()+"/registeraction";
%>    

 

<form method="post"  name="f" action="<%=action %>" onsubmit="return validateLoginForm(this)">

<div class="login-container" style="height: 400px">
<div class="" style="border: thin;border-color: black;">
<!-- <div class="login-label" style="float: left;"><a href="<%=request.getContextPath()%>/login">login page</a></div> -->
	
<div align="left" style="font: normal;font-size: 21px;padding-top: 50px;">Please Register</div>	

	<div class="login-inner">
   	  <div class="login-label">Organization Name :</div>
      <div class="login-textfield"><input validatefunc="validateUsername" validationfailedfunc="validateUsernameFailed"  type="text" name="orgname" id="orgname" /></div>
<!--       <div class="error" id="error_client_orgname" ><%=I18N.getMsg("Give Organization Name")%></div>
      <div class="error" id="error_server_orgname" ><%=I18N.getMsg("Give Organization Name")%></div>  -->
      <div class="clear"></div>
      
      
      
   	  <div class="login-label">Admin Username :</div>
      <div class="login-textfield"><input validatefunc="validateUsername" validationfailedfunc="validateUsernameFailed"  type="text" name="username" id="username" /></div>
<!--       <div class="error" id="error_client_username" ><%=I18N.getMsg("Enter User Name")%></div>
      <div class="error" id="error_server_username" ><%=I18N.getMsg("Enter User Name")%></div> -->
      <div class="clear"></div>
      
      
      <div class="login-label">Admin Password :</div>
      <div class="login-textfield"><input type="password" name="newpassword" id="newpassword" /></div>
<!--       <div class="error" id="error_client_newpassword" ><%=I18N.getMsg("Enter Password")%></div>
      <div class="error" id="error_server_newpassword" ><%=I18N.getMsg("Enter Password")%></div>  -->
      <div class="clear">
      
      <div class="login-label">Confirm Admin Password:</div>
      <div class="login-textfield"><input type="password" name="confirmpassword" id="confirmpassword" validatefunc="validateConfirmNewPassword" validationfailedfunc="validateUsernameFailed"/></div>
<!--       <div class="error" id="error_client_confirmpassword" ><%=I18N.getMsg("Passwords not matching")%></div>
      <div class="error" id="error_server_confirmpassword" ><%=I18N.getMsg("Enter a password")%></div>  -->
      <div class="clear">
      
      
      <div class="login-label">Admin Email :</div>
      <div class="login-textfield"><input type="text" name="email" id="email" validatefunc="validateEmail" validationfailedfunc="validateUsernameFailed"/></div>
<!--       <div class="error" id="error_client_email" ><%=I18N.getMsg("Enter Valid Admin Email Id")%></div>
      <div class="error" id="error_server_email" ><%=I18N.getMsg("Enter Admin Email Id")%></div>  -->
      <div class="clear">
      
      
      <div class="login-butn"><input name="submit"  type="submit" value="Register" style="background-position:top right;background-image: images/login-button.jpg;" /></div>
      
</div>
    <div class="clear"></div>
    
</div>

</div>
</div>
</form>


</body>


  
