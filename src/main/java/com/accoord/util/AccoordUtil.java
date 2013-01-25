package com.accoord.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.*;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.codec.Base64;

import com.accoord.domain.Organization;
import com.accoord.web.AccoordDao;

public class AccoordUtil {

	/**
	 * return s organization name for given url in format http://something/login/organizationname
	 * @param url
	 * @return
	 */
	public static Organization getOrganizationFromUrl(String url)throws Exception
	{
		if(url.endsWith("/"))
		{
			url=url.substring(0,url.length()-1);
		}
		String orgname=url.substring(url.lastIndexOf("/")+1);
		if("login".equals(orgname)|| "".equals(orgname))
		{
			return null;
		}
		return getDataAccess().getOrganization(orgname);
	}
	public static AccoordDao getDataAccess()throws Exception
	{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/applicationContext.xml");
		Object o=context.getBean("dao");
		return (AccoordDao)o;
	}
	/**
	 * Encrypt the given string in SHA algorithm. It is one side encrypted. Retrieving original value from encrypted is not possible
	 * @param plaintext
	 * @return
	 * @throws Exception
	 */
	 public static String encrypt(String plaintext) throws Exception
	  {
		 MessageDigest md = MessageDigest.getInstance("SHA-256");

		    byte[] hash = md.digest(plaintext.getBytes());

		    StringBuffer sb = new StringBuffer();
		    for(byte b : hash) {
		    	sb.append(String.format("%02x", b));

		    }

		    return (sb.toString());
  }
	
	 /**
	  * Removes characters like \ ' " = + etc from the given string and replaces it with s
	  * @param textArg
	  * @param s
	  * @return
	  */
	 public static String getEscapedString(Object textArg,char s)
		{
			if(textArg == null){
				return null;
			} 
	                String text = textArg.toString();
			StringBuffer charBuffer = new StringBuffer();
			for(int length = 0; length < text.length(); length++) {
				char ch = text.charAt(length);
				// The check is added for '/' so that data like '</script>' 
				// can be escaped. This is required since the '</script> tag is 
				// executed even if it is a part of a String.'
				if(ch == '\r' || ch == '\n' ){
					charBuffer.append('\\').append('n');
				}
				else if(ch == '\"' || ch == '\'' || ch=='/' || ch=='\\' || ch=='=' || ch=='&'|| ch=='+'){
					charBuffer.append(s);
				}
				else
				{
					charBuffer.append(ch);
				}
			}
			return charBuffer.toString();
		}
	 /**
	  *  string is escaped of special characters and html tags if present are made invalid.
	  * @param textArg
	  * @return
	  */
		public static String getEscapedString(Object textArg)
		{
			if(textArg == null){
				return null;
			} 
	                String text = textArg.toString();
			StringBuffer charBuffer = new StringBuffer();
			for(int length = 0; length < text.length(); length++) {
				char ch = text.charAt(length);
				// The check is added for '/' so that data like '</script>' 
				// can be escaped. This is required since the '</script> tag is 
				// executed even if it is a part of a String.'
				if(ch == '\r' || ch == '\n' ){
					charBuffer.append('\\').append('n');
				}
				else if(ch == '\"' || ch == '\'' || ch=='/' || ch=='\\'){
					charBuffer.append('\\');
					charBuffer.append(ch);
				}
				else
				{
					charBuffer.append(ch);
				}
			}
			return charBuffer.toString();
		}

		public static final String _ST_MSG_STATUS="ST_MSG_STATUS";
		public static final String _ST_MSG_MESSAGE="ST_MSG_MESSAGE";
		public static final String _ST_MSG_TYPE="ST_MSG_TYPE";
		public static final String _REQ_STAT="REQ_STAT";
		
	    
		
		/**
		 * get an instance of given fully qualified class name
		 * @param className
		 * @return
		 */
		public static Object createInstance(String className)
		{
			Class klass = null;
			Object obj = null; 
			try
			{
				Thread currentThread = Thread.currentThread();
				ClassLoader loader = currentThread.getContextClassLoader();
				klass = loader.loadClass(className);
				obj = klass.newInstance();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				throw new RuntimeException("Error In Creating Class" + className);
			}
			return obj;
		}
		/**
		 * decode an already encoded string back to UTF-8
		 * @param url
		 * @return
		 */
		public static String decodeUrl(String url)
		{
			try
			{
				return URLDecoder.decode(url, "UTF-8");
			}
			catch (UnsupportedEncodingException uee)
			{
				throw new IllegalArgumentException(uee);
			}
		}
		/**
		 * get date from string in format dd/mm/yyyy
		 * @param date
		 * @return
		 */
		public static Date getDate(String date) 
		{
			String[] arr=date.split("/");
			int year=Integer.parseInt(arr[2]);
			int day=Integer.parseInt(arr[0]);
			int month=Integer.parseInt(arr[1]);
			Calendar c=Calendar.getInstance();
			c.set(year, month-1, day);
			return c.getTime();
			
		}
		
		/**
		 * convert date to string in format DD MM YYYY hh mm ss
		 * @param date
		 * @return
		 */
		public static String dateToString(Date date) 
		{
			if(date==null)
			{
			}
			String temp="";
			Calendar c=Calendar.getInstance();
			c.setTime(date);
			temp=(appendZero(c.get(Calendar.DAY_OF_MONTH))+"/"+appendZero(c.get(Calendar.MONTH)+1))
			+"/"+c.get(Calendar.YEAR)+"    "
			+appendZero(c.get(Calendar.HOUR_OF_DAY))+"h "+appendZero(c.get(Calendar.MINUTE))
			+"m "+appendZero(c.get(Calendar.SECOND))+"s";
			return temp;
			
		}
		public static String appendZero(int i)
		{
			if(i<10)
			{
				return "0"+i;
			}
			return ""+i;
		}
		
		/**
		 * add parameter to a given url
		 * @param URL
		 * @param name
		 * @param value
		 * @return
		 */
		public static String addParameter(String URL, String name, String value)
		{
			int qpos = URL.indexOf('?');
			int hpos = URL.indexOf('#');
			char sep = qpos == -1 ? '?' : '&';
			String seg = sep + encodeUrl(name) + '=' + encodeUrl(value);
			return hpos == -1 ? URL + seg : URL.substring(0, hpos) + seg
					+ URL.substring(hpos);
		}

		/** 
		 * encode a given url so that it can be used for query string in urls
		 * @param url
		 * @return
		 */
		public static String encodeUrl(String url)
		{
			try
			{
				return URLEncoder.encode(url, "UTF-8");
			}
			catch (UnsupportedEncodingException uee)
			{
				throw new IllegalArgumentException(uee);
			}
		}


		public static String getParameter(HttpServletRequest request, String param) throws Exception
		{
			String p=request.getParameter(param);
			if(p==null || "".equals(p))
			{
				throw new Exception("required param "+p+" missing");
			}
			if("null".equals(p))
			{
				return null;
			}
			return p;

		}
		/**
		 * Add status message to a response
		 * It works through cookies. Two cookies are set one with data corresponding to status message
		 * other with info like if it is read, should it be sticky like that. On page load client side code parses this cookies and show status message
		 * 
		 *   
		 * @param request
		 * @param response
		 * @param message
		 * @param sticky
		 * @param top
		 */
		public static void addStatusMessage(HttpServletRequest request,HttpServletResponse response,String message,Boolean sticky,Boolean top)
		{
			Cookie c=new Cookie(_ST_MSG_MESSAGE, encodeUrl(message));
			Cookie c2=new Cookie(_ST_MSG_TYPE, encodeUrl(top?"top":"input"));
			Cookie c3=new Cookie(_ST_MSG_STATUS, encodeUrl("notreadA"+(sticky?"true":"false")));
			c.setPath(request.getContextPath());
			c2.setPath(request.getContextPath());
			c3.setPath(request.getContextPath());
			response.addCookie(c);
			response.addCookie(c2);
			response.addCookie(c3);
			
		}
		/**
		 *  get a query string removed url . For eg: http://something.jsp?error=true is the input then output will be http://something.jsp
		 * @return
		 */
		public static String getQueryStringRemovedUrl(String url)
		{
			if(url.indexOf("?")==-1)
			{
				return url;
			}
			String returnurl=url.substring(0,url.indexOf("?"));
			return returnurl;
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

		
		
		/**
		 * convert given file into an array of bytes
		 * 
		**/
	
		public static byte[] getBytesFromFile(File file) throws IOException {
		    InputStream is = new FileInputStream(file);

		    // Get the size of the file
		    long length = file.length();

		    // You cannot create an array using a long type.
		    // It needs to be an int type.
		    // Before converting to an int type, check
		    // to ensure that file is not larger than Integer.MAX_VALUE.
		    if (length > Integer.MAX_VALUE) {
		        // File is too large
		    	
		    }

		    // Create the byte array to hold the data
		    byte[] bytes = new byte[(int)length];

		    // Read in the bytes
		    int offset = 0;
		    int numRead = 0;
		    while (offset < bytes.length
		           && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
		        offset += numRead;
		    }

		    // Ensure all the bytes have been read in
		    if (offset < bytes.length) {
		        throw new IOException("Could not completely read file "+file.getName());
		    }

		    // Close the input stream and return bytes
		    is.close();
		    return bytes;
		}
		
		
		

}
