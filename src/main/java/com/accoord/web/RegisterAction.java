package com.accoord.web;

import java.io.*;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.accoord.util.AccoordUtil;
import com.accoord.domain.*;

public class RegisterAction implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		String username=request.getParameter("username");
		String password=request.getParameter("newpassword");
		
		String orgname=request.getParameter("orgname");
		String email=request.getParameter("email");
		if(username==null || orgname==null || email==null || password==null){
			AccoordUtil.addStatusMessage(request, response, "Username/Password/Email is mandatory", true, true);
			response.sendRedirect(request.getContextPath()+"/register");
			return null;
		}
		
		Organization o=AccoordUtil.getDataAccess().getOrganization(orgname);
		if(o!=null)
		{
			AccoordUtil.addStatusMessage(request, response, "Organization name '"+orgname+"' is already registered", true, true);
			response.sendRedirect(request.getContextPath()+"/register");
			return null;
		}
		UserEntity ue=AccoordUtil.getDataAccess().getUser(email);
		if(ue!=null)
		{
			AccoordUtil.addStatusMessage(request, response, "Email id is already registered", true, true);
			response.sendRedirect(request.getContextPath()+"/register");
			return null;
		}
		
		
		String activationkey=AccoordUtil.encrypt(username+password+System.currentTimeMillis()+Math.random());
		AccoordUtil.getDataAccess().register(orgname, username, password, email,activationkey);
		AccoordUtil.addStatusMessage(request, response, "Registration successfull. An email is sent to "+email+" with activation instructions", true, true);
		sendConfirmationMail(username,password,orgname,email,activationkey,request);
		response.sendRedirect(request.getContextPath()+"/home");
		return null;
	}

	public void sendConfirmationMail(String username,String password,String orgname,String email,String activationkey,HttpServletRequest request)throws Exception
	{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/applicationContext.xml");
		Object o=context.getBean("mailSender");
		MailSender mailSender=(MailSender)o;
		Object mailmsg=context.getBean("mailMessage");
		
		SimpleMailMessage msg = (SimpleMailMessage)mailmsg;
	        msg.setTo(email);
	        InputStream maildata=Thread.currentThread().getContextClassLoader().getResourceAsStream("confirmationemail.tpl");
			String mail=convertStreamToString(maildata);
			mail=replace(mail,"USERNAME", username);
			mail=replace(mail,"LINK",request.getScheme()+"://"+request.getServerName()+((request.getLocalPort()==80)?"":(":"+request.getLocalPort()))+request.getContextPath()+"/confirmemail?id="+activationkey);
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
