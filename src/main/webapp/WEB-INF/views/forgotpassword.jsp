<%@page import="com.accoord.util.*,com.accoord.domain.*"%>
<%AccoordUtil.getDataAccess().loadDefaultValues(); %>

<head>
<%@ page import="com.accoord.web.*" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Forgot Password</title>

<%@include file="includes.jsp"%>

</head>

<body>
<%
String action=request.getContextPath()+"/forgotpasswordaction";
%>    
<form method="post"  name="f" action="<%=action %>" onsubmit="return validateLoginForm(this)">

<div class="login-container" style="height: 400px">
<div class="" style="border: thin;border-color: black;">
<!-- <div class="login-label" style="float: left;"><a href="<%=request.getContextPath()%>/login">login page</a></div> -->
	
<div align="left" style="font: normal;font-size: 21px;padding-top: 50px;padding-left: 100px;">Forgot Password</div>	

	<div class="login-inner">
   	  
      <div class="login-label">Please Enter Your Email ID:</div>
      <div class="login-textfield"><input type="text" name="email" id="email" validatefunc="validateEmail" validationfailedfunc="validateUsernameFailed"/></div>
<!--        <div class="error" id="error_client_email" ><%=I18N.getMsg("Enter Valid Email Id Used While Registering")%></div>
      <div class="error" id="error_server_email" ><%=I18N.getMsg("Enter Valid Email Id")%></div> -->
      <div class="clear">
      
      
      <div class="login-butn"><input name="submit"  type="submit" value="Submit" style="background-position:top right;background-image: images/login-button.jpg;" /></div>
      
</div>
    <div class="clear"></div>
    
</div>

</div>
</div>
</form>
</body>


  
