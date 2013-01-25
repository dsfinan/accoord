package com.accoord.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.accoord.domain.UserEntity;
import com.accoord.util.AccoordUtil;

public class ChangePasswordAction implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		try
		{
			String username=request.getParameter("username");
			String organization=request.getParameter("organization");
			String password=request.getParameter("password");
			String newpassword=request.getParameter("newpassword");

			UserEntity ue= AccoordUtil.getDataAccess().getUser(organization, username);
			if(ue==null || (!(ue.getPassword().equals(AccoordUtil.encrypt(password)))))
			{
				AccoordUtil.addStatusMessage(request, response, "Old password not matching", true, true);
				response.sendRedirect(request.getContextPath()+"/changepassword");
				return null;

			}
			boolean status=AccoordUtil.getDataAccess().updateUserPassword(newpassword, username,organization);
			if(status)
			{
				AccoordUtil.addStatusMessage(request, response, "Password successfully updated", true, true);
				response.sendRedirect(request.getContextPath()+"/changepassword");

			}
			else
			{
				AccoordUtil.addStatusMessage(request, response, "Some error", true, true);
				response.sendRedirect(request.getContextPath()+"/changepassword");

			}
			return null;
		}
		catch (Exception e) {
			e.printStackTrace();
			AccoordUtil.addStatusMessage(request, response, "Some error", true, true);
			response.sendRedirect(request.getContextPath()+"/changepassword");
			return null;
		}
	}

}
