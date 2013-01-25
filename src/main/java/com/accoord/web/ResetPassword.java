package com.accoord.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.accoord.domain.UserEntity;
import com.accoord.util.AccoordUtil;

public class ResetPassword implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String key=request.getParameter("id");
		UserEntity ue=AccoordUtil.getDataAccess().getUserWithResetKey(key);
		if(ue==null)
		{
			AccoordUtil.addStatusMessage(request, response, "Link not valid", true, true);
			response.sendRedirect(request.getContextPath()+"/login");
		}
		response.sendRedirect(request.getContextPath()+"/resetpass?id="+key);
		return null;
	}

}
