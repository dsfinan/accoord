package com.accoord.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.accoord.domain.Organization;
import com.accoord.domain.UserEntity;
import com.accoord.util.AccoordUtil;

public class ResetPasswordAction implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		String key=request.getParameter("key");
		String password=request.getParameter("newpassword");
		if(!AccoordUtil.getDataAccess().updateUserPassword(password, key))
		{
			AccoordUtil.addStatusMessage(request, response, "Some Error", true, true);
			response.sendRedirect(request.getContextPath()+"/login");
			return null;
	
		}
		AccoordUtil.addStatusMessage(request, response, "Password is successfully reset. Please Login.", true, true);
		response.sendRedirect(request.getContextPath()+"/login");
		
		return null;
	}

}
