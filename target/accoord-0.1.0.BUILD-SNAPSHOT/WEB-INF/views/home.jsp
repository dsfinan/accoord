<%@page import="org.springframework.security.core.*,org.springframework.security.core.context.*,com.accoord.web.login.*,java.util.*" %>
<% 
Authentication a=SecurityContextHolder.getContext().getAuthentication();
AccoordUserDetails aud=(AccoordUserDetails)(a.getPrincipal());
Collection<GrantedAuthority> authorities=aud.getAuthorities();
%>

<div>index page of <%=aud.getUsername() %> in organization <%=aud.getOrganization() %>

<br>Roles are <br>
<%if(request.isUserInRole("ORG_ADMIN")){out.println("org ani");} %>
<br><br>
<%for(GrantedAuthority ga:authorities)
{%>
<%=ga.getAuthority()%><br>
<%} %>
</div>
