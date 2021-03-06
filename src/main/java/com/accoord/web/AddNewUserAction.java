package com.accoord.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.accoord.util.AccoordUtil;
import com.accoord.web.login.AccoordUserDetails;

public class AddNewUserAction implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		try
		{
			Authentication a=SecurityContextHolder.getContext().getAuthentication();
			AccoordUserDetails aud=(AccoordUserDetails)(a.getPrincipal());
			String username=request.getParameter("username");
			String organization=aud.getOrganization();
			String password=request.getParameter("newpassword");
			String email=request.getParameter("email");
			String orgadmin = aud.getUsername();

			
			if(AccoordUtil.getDataAccess().getUser(email)!=null)
			{
				AccoordUtil.addStatusMessage(request, response, "Email already in use", true, true);
				response.sendRedirect(request.getContextPath()+"/addnewuser");
				return null;

			}
			if(AccoordUtil.getDataAccess().getUser(organization,username)!=null)
			{
				AccoordUtil.addStatusMessage(request, response, "Username already exist", true, true);
				response.sendRedirect(request.getContextPath()+"/addnewuser");
				return null;

			}
			
			AccoordUtil.getDataAccess().addOrgUser(organization, username, password, email);
			AccoordUtil.addStatusMessage(request, response, "User Successfully added", true, true);
			sendConfirmationMail(username, password, organization, orgadmin, email, request);
			response.sendRedirect(request.getContextPath()+"/addnewuser");

			
		}
		catch (Exception e) {
			e.printStackTrace();
			AccoordUtil.addStatusMessage(request, response, "User Not Added", true, true);
			response.sendRedirect(request.getContextPath()+"/addnewuser");

		}
		return null;
	}

	public void sendConfirmationMail(String username,String password,String orgname,String orgadmin,String email, HttpServletRequest request)throws Exception
	{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/applicationContext.xml");
		Object o=context.getBean("mailSender");
		MailSender mailSender=(MailSender)o;
		Object mailmsg=context.getBean("mailMessage");
		
		SimpleMailMessage msg = (SimpleMailMessage)mailmsg;
	        msg.setTo(email);
	        InputStream maildata=Thread.currentThread().getContextClassLoader().getResourceAsStream("adduseremail.tpl");
			String mail=convertStreamToString(maildata);
			mail=replace(mail,"USERNAME", username);
			mail=replace(mail,"ORGADMIN", orgadmin);
			mail=replace(mail,"ORG", orgname);			
			mail=replace(mail,"TEMPPASS", password);				
			msg.setText(mail);
	        
			try{
	            mailSender.send(msg);
	        }
	        catch(MailException ex) {
	            System.err.println(ex.getMessage());            
	        }
		
	        
	}	
	
	/**
	 * replace template value in format %replace% with a given value
	 * @param mail
	 * @param string
	 * @param string2
	 * @return
	 */
	public static String replace(String mail, String string, String string2) 
	{
		int from=0;
		while(mail.indexOf("%",from)!=-1)
		{
			int index=mail.indexOf("%",from);
			int index2=mail.indexOf("%",index+1);
			String substring=mail.substring(index+1,index2);
			if(substring.equals(string))
			{
				mail=mail.substring(0,index)+string2+mail.substring(index2+1,mail.length());
			}
			from=index2+1;
		}
		return mail;
	}
	
	/**
	 * convert given input stream to String
	 * 
	**/
	public static String convertStreamToString(InputStream is) throws IOException {
		if (is != null) {
			Writer writer = new StringWriter();
			char[] buffer = new char[1024];
			try {
				Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				int n;
				while ((n = reader.read(buffer)) != -1) 
				{
					writer.write(buffer, 0, n);
				}
			} finally {
				is.close();
			}
			return writer.toString();
		} else {       
			return "";
		}
	}	
	
	
	
}
