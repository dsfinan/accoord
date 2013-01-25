<%@page import="org.springframework.security.core.*,org.springframework.security.core.context.*,com.accoord.web.login.*,java.util.*" %>
<%@page import="com.accoord.util.*,com.accoord.domain.*"%>

<% 
Authentication a=SecurityContextHolder.getContext().getAuthentication();
AccoordUserDetails aud=(AccoordUserDetails)(a.getPrincipal());
Collection<GrantedAuthority> authorities=aud.getAuthorities();
%>
<%@include file="includes.jsp"%>

<div>index page of <%=aud.getUsername() %> in organization <%=aud.getOrganization() %>
<p>


<br>Roles are
<br><br>
<%for(GrantedAuthority ga:authorities)
{%>
<%=ga.getAuthority()%><br>
<%} %>


</div>

<h1>Please upload a Org Logo</h1>
<form method="post" action="<%=request.getContextPath() %>/uploadFile" enctype="multipart/form-data">
            <input type="file" name="file"/>
            <input type="hidden" name="destination" value="2"/>
            <input type="submit"/>
</form>
