package com.accoord.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.accoord.util.AccoordUtil;

public class ConfirmEmail implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {

		String activationkey=arg0.getParameter("id");
		boolean status=AccoordUtil.getDataAccess().confirmUser(activationkey);
		if(status)
		{
			AccoordUtil.addStatusMessage(arg0, arg1, "Email verified. Please Login", true, true);
			arg1.sendRedirect(arg0.getContextPath()+"/login");
			return null;

		}
		else
		{
			arg1.getOutputStream().print("Link Not Valid");
			return null;
		}
	}

}
