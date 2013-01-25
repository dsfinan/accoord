package com.accoord.web;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.accoord.domain.Organization;
import com.accoord.util.AccoordUtil;

public class CheckOrganization implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		String orgname=request.getParameter("organization");
		Organization o=AccoordUtil.getDataAccess().getOrganization(orgname);
		if(o==null)
		{
			AccoordUtil.addStatusMessage(request, response, "Organization name invalid", false, true);
			response.sendRedirect(request.getContextPath()+"/login");
		}
		else
		{
			response.sendRedirect(request.getContextPath()+"/login/"+o.getName());
			
		}
		return null;
	}

}
