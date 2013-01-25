package com.accoord.web;

import java.io.InputStream;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.accoord.domain.*;
import com.accoord.util.AccoordUtil;

public class ForgotPasswordAction implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		String email=request.getParameter("email");
		UserEntity ue=AccoordUtil.getDataAccess().getUser(email);
		if(email==null || ue==null)
		{
			AccoordUtil.addStatusMessage(request, response, "Email not recognized", true, true);
			response.sendRedirect(request.getContextPath()+"/forgotpassword");
			return null;
		
		}
		sendResetEmail(ue,request);
		AccoordUtil.addStatusMessage(request, response, "An email is sent to "+email+ " with password reset instructions", true, true);
		response.sendRedirect(request.getContextPath()+"/login");
		return null;
	}

	public void sendResetEmail(UserEntity ue,HttpServletRequest request)throws Exception
	{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/applicationContext.xml");
		Object o=context.getBean("mailSender");
		MailSender mailSender=(MailSender)o;
		Object mailmsg=context.getBean("mailMessage");
		
		SimpleMailMessage msg = (SimpleMailMessage)mailmsg;
	        msg.setTo(ue.getEmail());
	        msg.setSubject("Reset your Password");
	        InputStream maildata=Thread.currentThread().getContextClassLoader().getResourceAsStream("passwordreset.tpl");
			String mail=AccoordUtil.convertStreamToString(maildata);
			mail=AccoordUtil.replace(mail,"USERNAME", ue.getUsername());
			mail=AccoordUtil.replace(mail,"ORGANIZATION", ue.getOrganization().getName());
			
			mail=AccoordUtil.replace(mail,"LINK",request.getScheme()+"://"+request.getServerName()+((request.getLocalPort()==80)?"":(":"+request.getLocalPort()))+request.getContextPath()+"/resetpassword?id="+ue.getResetkey());
			msg.setText(mail);
	        try{
	            mailSender.send(msg);
	        }
	        catch(MailException ex) {
	            System.err.println(ex.getMessage());            
	        }
		
	}
	
}
