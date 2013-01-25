package com.accoord.web;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import com.accoord.web.login.AccoordUserDetails;


public class ImageController extends HttpServlet {
    private static final long serialVersionUID = 1L;

  public ImageController() {
     }

  

    protected void getProfilePic(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
         
		Authentication a=SecurityContextHolder.getContext().getAuthentication();
		AccoordUserDetails aud=(AccoordUserDetails)(a.getPrincipal()); 


	    //BufferedImage image = aud.getProfilePic();
        BufferedImage image = new BufferedImage(0, 0, 0);
		OutputStream out = response.getOutputStream();
         
          response.setContentType("image/jpeg");
          ImageIO.write(image,"jpeg",out);
               
          out.flush();
          out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    }
}

