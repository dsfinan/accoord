package com.accoord.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.accoord.util.*;
import com.accoord.web.login.AccoordUserDetails;

public class FileUploadController extends SimpleFormController 
{
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
            Object command, BindException errors) throws ServletException, IOException {

        FileUploadBean bean = (FileUploadBean) command;

        byte[] file = bean.getFile();
      
        Authentication a=SecurityContextHolder.getContext().getAuthentication();
        AccoordUserDetails aud=(AccoordUserDetails)(a.getPrincipal());
        
        String destination=request.getParameter("destination");
        //note: 1 profilepic; 2 orglogo; 3 msgphoto; 4 msgfile
        int destino = new Integer (destination);
        
        if (file == null) {
 
        	  AccoordUtil.addStatusMessage(request, response, "no file chosen", true, true);
              
        }

        else
        {
        	try
            {
        		switch (destino){
        		 	case 1:  
        		 	AccoordUtil.getDataAccess().addProfilePic(aud.getUsername(),aud.getOrganization(), file);
        		 	response.sendRedirect(request.getContextPath()+"/userprofiles/"+ aud.getUserObject().getProfile().getId());
        			break;
        		 	case 2:  
            		AccoordUtil.getDataAccess().addOrgLogo(aud.getUsername(),aud.getOrganization(), file);
            		response.sendRedirect(request.getContextPath()+"/home");
            		break;
        		
        		}
            	//AccoordUtil.getDataAccess().addFile(aud.getUsername(),aud.getOrganization(), filetype, file);
        		//AccoordUtil.getDataAccess().addProfilePic(aud.getUsername(),aud.getOrganization(), file);
            }
        	catch (Exception e) {
        		//AccoordUtil.addStatusMessage(request, response, "exception", true, true);
        		e.printStackTrace();
			}
        	AccoordUtil.addStatusMessage(request, response, "File Uploaded successfully", true, true);
        }
        
        return null;
    }

    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
        throws ServletException {
        // to actually be able to convert Multipart instance to byte[]
        // we have to register a custom editor
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
        // now Spring knows how to handle multipart object and convert them
    }
}







