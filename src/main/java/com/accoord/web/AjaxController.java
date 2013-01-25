package com.accoord.web;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.accoord.domain.AssessmentArea;
import com.accoord.domain.Event;
import com.accoord.domain.Message;
import com.accoord.domain.OrgProfile;
import com.accoord.domain.Organization;
import com.accoord.domain.RoleEntity;
import com.accoord.domain.ServiceArea;
import com.accoord.domain.UserEntity;
import com.accoord.domain.UserProfile;
import com.accoord.domain.UserRole;

import com.accoord.util.AccoordUtil;
import com.accoord.web.login.AccoordUserDetails;

import flexjson.JSONSerializer;



@RequestMapping("/ajax")
@Controller
public class AjaxController {
	
	@RequestMapping(value="/CreateOrgProfile", method=RequestMethod.GET)
	public String home() {

		
		return "/";
	}
	

	
	
	@RequestMapping(value="/setOrgLogoTest", method=RequestMethod.GET)
	public String atest() throws IOException {
		
		
		
		try {
			UrlResource resource = new UrlResource ("/resources/images/icons/orgIcons/orgLogoBlank.jpg");
			File orgpic = resource.getFile();
			String name = orgpic.getName();
			return name;
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		}
		return "/";
		
		
		//OrgProfile op = OrgProfile.findOrgProfile((long) 1);
		
		//byte[] logoArray = null;
		//try {
			//logoArray = AccoordUtil.getBytesFromFile(orgpic);
			//op.setOrglogo(logoArray);
			//op.persist();
			//Organization org = Organization.findOrganization((long)1);
			//org.setOrgprofile(op);
			//org.persist();
		//} catch (IOException e) {
			
			//e.printStackTrace();
		//}
		
	}
	

	
	//get User with a user ID
	 @RequestMapping(value="/returnUser", method=RequestMethod.GET)  
	  public @ResponseBody String getUser(HttpServletRequest request, HttpServletResponse response, @RequestParam Long id) throws Exception {  

		 UserEntity jsonUE = UserEntity.findUserEntity(id);
		 	//check if event is null
		 	if (jsonUE.equals(null)){
		 		AccoordUtil.addStatusMessage(request, response, "Opps...something went wrong!", false, true);;
		 		return "No User Found";
		 	}		 
		 return new JSONSerializer().include("serviceareas", "assessmentareas", "rapidassessments", "securityincidents", "profile").serialize(jsonUE);
		

	  } 

		//get Users
	 @RequestMapping(value="/returnUsers", method=RequestMethod.GET)  
	  public @ResponseBody String getUsers() {  

		 List<UserEntity> jsonUsers = UserEntity.findAllUserEntitys();
		 //TODO include updates, orgs, userEntity, etc		 	
		 return new JSONSerializer().include("serviceareas", "assessmentareas", "securityincidents", "events").serialize(jsonUsers);
		

	  } 
	 
		//get Users
	 @RequestMapping(value="/returnUserRoles", method=RequestMethod.GET)  
	  public @ResponseBody String getUserRoles() {  

		 List<UserRole> jsonUserRoles = UserRole.findAllUserRoles();
		 //TODO include updates, orgs, userEntity, etc		 	
		 return new JSONSerializer().serialize(jsonUserRoles);
		

	  } 
	 
	 
		//get Roles
	 @RequestMapping(value="/returnRoles", method=RequestMethod.GET)  
	  public @ResponseBody String getRoles() {  

		 List<RoleEntity> jsonRoles = RoleEntity.findAllRoleEntitys();
		 //TODO include updates, orgs, userEntity, etc		 	
		 return new JSONSerializer().serialize(jsonRoles);
		

	  } 
	 
		//get Roles
	 @RequestMapping(value="/returnOrgs", method=RequestMethod.GET)  
	  public @ResponseBody String getOrgs() {  

		 List<Organization> jsonOrgs = Organization.findAllOrganizations();
		 //TODO include updates, orgs, userEntity, etc		 	
		 return new JSONSerializer().serialize(jsonOrgs);
		

	  } 



	 
	 
	 
	//get events
	 @RequestMapping(value="/returnEvents", method=RequestMethod.GET)  
	  public @ResponseBody String getEvents() {  

		 List<Event> jsonEvents = Event.findAllEvents();
		 //TODO include updates, orgs, userEntity, etc		 	
		 return new JSONSerializer().include("serviceareas", "assessmentareas", "securityincidents", "owner").serialize(jsonEvents);

	  } 
	 

	 
		//get a specific event
	 @RequestMapping(value="/returnEvent", method=RequestMethod.GET)  
	  public @ResponseBody String getEvent(HttpSession session, @RequestParam Long id) throws Exception {  

		 //List<Event> jsonEvents = Event.findAllEvents();
		 //TODO include updates, orgs, userEntity, etc	
		 Event jsonEvent = Event.findEvent(id);
		 		 
		 return new JSONSerializer().include("serviceareas", "assessmentareas", "messages", "securityincidents", "owner").serialize(jsonEvent);
		

	  } 
	 
	 //ajax/returnAssesssmentArea ...get a specific assessment area
	 @RequestMapping(value="/returnAssesssmentArea", method=RequestMethod.GET)  
	  public @ResponseBody String getAssessmentArea(HttpSession session, @RequestParam Long id) throws Exception {  

		//TODO include updates, orgs, userEntity, etc	
		 AssessmentArea jsonAssessment = AssessmentArea.findAssessmentArea(id);
		 		 
		 return new JSONSerializer().include("messages", "rapidassessments", "owner").serialize(jsonAssessment);
		

	  } 

	 //ajax/returnServiceArea ...get a specific service area
	 @RequestMapping(value="/returnServiceArea", method=RequestMethod.GET)  
	  public @ResponseBody String getServiceArea(HttpSession session, @RequestParam Long id) throws Exception {  

		ServiceArea jsonAssessment = ServiceArea.findServiceArea(id);

		 		 
		 return new JSONSerializer().include("messages", "constructionprojects", "distributions", "equipment", "trainings", "owner").serialize(jsonAssessment);
		

	  } 
	 
	 //set map state to session variables
	 @RequestMapping(value="/setMapState", method=RequestMethod.GET)  

	 public @ResponseBody String setMapState(HttpSession session, @RequestParam String zoom, @RequestParam String lat, @RequestParam String lng) {  

		 

		 session.setAttribute("lastZoom", zoom);
		 session.setAttribute("lastLat", lat);
		 session.setAttribute("lastLng", lng);
	
		 return "success";

	  } 
	 
	 //messaging methods below, one per owning entity
	 
	//post eventmsg
	 @RequestMapping(value="/addEventMsg", method=RequestMethod.GET) 
	 public @ResponseBody void addMsg(HttpServletRequest request, HttpServletResponse response, @RequestParam("authorId") Long authorId, 
			 @RequestParam("eventId")Long eventId, 
			 //@RequestParam("public")String isPublic, 
			 @RequestParam("msg")String msg) {
			
		 	
		 
		 	//check if public is true and create Boolean otherwise, assume false
		  	//boolean publicMsg = false;
			//if (isPublic=="true"){
		 		//publicMsg = true; 
		 	//}
		 
			//get the date 
			//TODO change to client side so we dont post server date and time
			Date now = new Date();
		 
			//get the objects
			Event event =  Event.findEvent(eventId);
		 
		 	//check if event is null
		 	if (event.equals(null)){
		 		AccoordUtil.addStatusMessage(request, response, "Opps...something went wrong!", false, true);;
		 		return;
		 	}
		 
		 	Authentication a=SecurityContextHolder.getContext().getAuthentication();
		 	AccoordUserDetails aud=(AccoordUserDetails)(a.getPrincipal());
		 	UserEntity owner = aud.getUserObject();
		 
		 	//create new message
		 	Message m = new Message();
		 	m.setEvent(event);
		 	m.setOwner(owner);
		 	m.setCreated(now);
		 	m.setMessage(msg);
		 	//m.setMakepublic(publicMsg);
		 	m.persist();
	
		 	AccoordUtil.addStatusMessage(request, response, "Update Posted", false, true);
		 	return;
			
	
	 }

	//post assessmentmsg
	 @RequestMapping(value="/addAssessMsg", method=RequestMethod.GET) 
	 public @ResponseBody void addAssessMsg(HttpServletRequest request, HttpServletResponse response, @RequestParam("authorId") Long authorId, 
			 @RequestParam("assessmentId")Long assessmentId, 
			 //@RequestParam("public")String isPublic, 
			 @RequestParam("msg")String msg) {
			
		 	
		 
		 	//check if public is true and create Boolean otherwise, assume false
		  	//boolean publicMsg = false;
			//if (isPublic=="true"){
		 		//publicMsg = true; 
		 	//}
		 
			//get the date 
			//TODO change to client side so we dont post server date and time
			Date now = new Date();
		 
			//get the objects
			AssessmentArea aa =  AssessmentArea.findAssessmentArea(assessmentId);
		 
		 	//check if event is null
		 	if (aa.equals(null)){
		 		AccoordUtil.addStatusMessage(request, response, "Opps...something went wrong!", false, true);;
		 		return;
		 	}
		 
		 	Authentication a=SecurityContextHolder.getContext().getAuthentication();
		 	AccoordUserDetails aud=(AccoordUserDetails)(a.getPrincipal());
		 	UserEntity owner = aud.getUserObject();
		 
		 	//create new message
		 	Message m = new Message();
		 	m.setAssessmentarea(aa);
		 	m.setOwner(owner);
		 	m.setCreated(now);
		 	m.setMessage(msg);
		 	//m.setMakepublic(publicMsg);
		 	m.persist();
	
		 	AccoordUtil.addStatusMessage(request, response, "Update Posted", false, true);
		 	return;
			
	
	 }

		//post servicemsg
	 @RequestMapping(value="/addServiceMsg", method=RequestMethod.GET) 
	 public @ResponseBody void addServiceMsg(HttpServletRequest request, HttpServletResponse response, @RequestParam("authorId") Long authorId, 
			 @RequestParam("serviceAreaId")Long serviceAreaId, 
			 //@RequestParam("public")String isPublic, 
			 @RequestParam("msg")String msg) {
			
		 	
		 
		 	//check if public is true and create Boolean otherwise, assume false
		  	//boolean publicMsg = false;
			//if (isPublic=="true"){
		 		//publicMsg = true; 
		 	//}
		 
			//get the date 
			//TODO change to client side so we dont post server date and time
			Date now = new Date();
		 
			//get the objects
			ServiceArea sa = ServiceArea.findServiceArea(serviceAreaId);
				 
		 	//check if event is null
		 	if (sa.equals(null)){
		 		AccoordUtil.addStatusMessage(request, response, "Opps...something went wrong!", false, true);;
		 		return;
		 	}
		 
		 	Authentication a=SecurityContextHolder.getContext().getAuthentication();
		 	AccoordUserDetails aud=(AccoordUserDetails)(a.getPrincipal());
		 	UserEntity owner = aud.getUserObject();
		 
		 	//create new message
		 	Message m = new Message();
		 	m.setServicearea(sa);
		 	m.setOwner(owner);
		 	m.setCreated(now);
		 	m.setMessage(msg);
		 	//m.setMakepublic(publicMsg);
		 	m.persist();
	
		 	AccoordUtil.addStatusMessage(request, response, "Update Posted", false, true);
		 	return;
			
	
	 }
	
		//post profilemsg
	 @RequestMapping(value="/addProfileMsg", method=RequestMethod.GET) 
	 public @ResponseBody void addProfileMsg(HttpServletRequest request, HttpServletResponse response, @RequestParam("authorId") Long authorId, 
			 @RequestParam("profileId")Long profileId, 
			 //@RequestParam("public")String isPublic, 
			 @RequestParam("msg")String msg) {
			
		 	
		 
		 	//check if public is true and create Boolean otherwise, assume false
		  	//boolean publicMsg = false;
			//if (isPublic=="true"){
		 		//publicMsg = true; 
		 	//}
		 
			//get the date 
			//TODO change to client side so we dont post server date and time
			Date now = new Date();
		 
			//get the objects
			UserProfile up = UserProfile.findUserProfile(profileId);

				 
		 	//check if profile is null
		 	if (up.equals(null)){
		 		AccoordUtil.addStatusMessage(request, response, "Opps...something went wrong!", false, true);;
		 		return;
		 	}
		 
		 	Authentication a=SecurityContextHolder.getContext().getAuthentication();
		 	AccoordUserDetails aud=(AccoordUserDetails)(a.getPrincipal());
		 	UserEntity owner = aud.getUserObject();
		 
		 	//create new message
		 	Message m = new Message();
		 	m.setProfile(up);
		 	m.setOwner(owner);
		 	m.setCreated(now);
		 	m.setMessage(msg);
		 	//m.setMakepublic(publicMsg);
		 	m.persist();
	
		 	AccoordUtil.addStatusMessage(request, response, "Update Posted", false, true);
		 	return;
			
	
	 }
		 
		//post orgmsg
	 @RequestMapping(value="/addOrgMsg", method=RequestMethod.GET) 
	 public @ResponseBody void addOrgMsg(HttpServletRequest request, HttpServletResponse response, @RequestParam("authorId") Long authorId, 
			 @RequestParam("orgProfileId")Long orgProfileId, 
			 //@RequestParam("public")String isPublic, 
			 @RequestParam("msg")String msg) {
			
		 	
		 
		 	//check if public is true and create Boolean otherwise, assume false
		  	//boolean publicMsg = false;
			//if (isPublic=="true"){
		 		//publicMsg = true; 
		 	//}
		 
			//get the date 
			//TODO change to client side so we dont post server date and time
			Date now = new Date();
		 
			//get the objects
			OrgProfile op = OrgProfile.findOrgProfile(orgProfileId);

				 
		 	//check if profile is null
		 	if (op.equals(null)){
		 		AccoordUtil.addStatusMessage(request, response, "Opps...something went wrong!", false, true);;
		 		return;
		 	}
		 
		 	Authentication a=SecurityContextHolder.getContext().getAuthentication();
		 	AccoordUserDetails aud=(AccoordUserDetails)(a.getPrincipal());
		 	UserEntity owner = aud.getUserObject();
		 
		 	//create new message
		 	Message m = new Message();
		 	m.setOrgprofile(op);
		 	m.setOwner(owner);
		 	m.setCreated(now);
		 	m.setMessage(msg);
		 	//m.setMakepublic(publicMsg);
		 	m.persist();
	
		 	AccoordUtil.addStatusMessage(request, response, "Update Posted", false, true);
		 	return;
			
	
	 }	 
	 
	 
		//Image serving
		
		//get profilepic
		 @RequestMapping(value="/getProfilePic", method=RequestMethod.GET) 
		 public void getProfilePic(HttpServletRequest request, HttpServletResponse response, @RequestParam Long id) throws Exception {
		
			//Get the UserEntity from the id param
			 UserEntity ue = UserEntity.findUserEntity(id);
			 UserProfile up = ue.getProfile();
			 
			 	if (up == null){
			 		AccoordUtil.addStatusMessage(request, response, "Opps...something went wrong!", false, true);;
			 		return;
			 	}
			 	if (up.getProfilepicture() == null){
			 		//write in a blank profile pic from resource
			 		BufferedImage img = null;
			 		try {
			 		    img = ImageIO.read(new File("/resources/images/icons/userIcons/blankProfile.jpg"));
			 		} catch (IOException e) {
			 		}
				 	

				 	BufferedImage profilePic = new BufferedImage(50, 50, img.getType());
				    Graphics2D g = profilePic.createGraphics();
				    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
				    g.drawImage(img, 0, 0, 50, 50, 0, 0, img.getWidth(), img.getHeight(), null);
				    g.dispose();
				 	
				    OutputStream out = response.getOutputStream();
				 	response.setContentType("image/jpg");
	                ImageIO.write(img,"jpg",out);
	               
	                out.flush();
	                out.close();
			 	}
			 
			 	InputStream in = new ByteArrayInputStream(up.getProfilepicture());
				BufferedImage original = ImageIO.read(in); 

				BufferedImage profilePic = new BufferedImage(50, 50, original.getType());
			    Graphics2D g = profilePic.createGraphics();
			    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			    g.drawImage(original, 0, 0, 50, 50, 0, 0, original.getWidth(), original.getHeight(), null);
			    g.dispose();

			 	OutputStream out = response.getOutputStream();
         
			 	response.setContentType("image/jpg");
                ImageIO.write(profilePic,"jpg",out);
               
          out.flush();
          out.close();	 
	 
		 }

		 
			//get  large profilepic for profile page
		 @RequestMapping(value="/getBigProfilePic", method=RequestMethod.GET) 
		 public void getLargeProfilePic(HttpServletRequest request, HttpServletResponse response, @RequestParam Long id) throws Exception {
		
			//Get the UserEntity from the id param
			 UserEntity ue = UserEntity.findUserEntity(id);
			 UserProfile up = ue.getProfile();
			 
			 	if (up.equals(null)){
			 		AccoordUtil.addStatusMessage(request, response, "Opps...something went wrong!", false, true);;
			 		return;
			 	}
			 	if (up.getProfilepicture().equals(null)){
			 		
			 		//write in a blank profile pic from resource
			 		BufferedImage img = null;
			 		try {
			 		    img = ImageIO.read(new File("/resources/images/icons/userIcons/blankProfile.jpg"));
			 		} catch (IOException e) {
			 		}
				 	OutputStream out = response.getOutputStream();
			        //Dimension are not changed, therefore it will render at 200 x 200
				 	response.setContentType("image/jpg");
	                ImageIO.write(img,"jpg",out);
	               
	                out.flush();
	                out.close();	
	                return;
			 	}
			 
			 	InputStream in = new ByteArrayInputStream(up.getProfilepicture());
				BufferedImage original = ImageIO.read(in); 

				BufferedImage profilePic = new BufferedImage(200, 200, original.getType());
			    Graphics2D g = profilePic.createGraphics();
			    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			    g.drawImage(original, 0, 0, 200, 200, 0, 0, original.getWidth(), original.getHeight(), null);
			    g.dispose();

			 	OutputStream out = response.getOutputStream();
         
			 	response.setContentType("image/jpg");
                ImageIO.write(profilePic,"jpg",out);
               
          out.flush();
          out.close();	 
	 
		 }

			//get  Org logo for profile page
		 @RequestMapping(value="/getOrgLogo", method=RequestMethod.GET) 
		 public void getOrgLogo(HttpServletRequest request, HttpServletResponse response, @RequestParam Long id) throws Exception {
		
			//Get the UserEntity from the id param
			 Organization org = Organization.findOrganization(id);
			 OrgProfile profile = org.getOrgprofile();
			 
			 	if (profile.equals(null)){
			 		AccoordUtil.addStatusMessage(request, response, "Opps...cant find the Organizations Profile!", false, true);;
			 		return;
			 	}
			 	if (profile.getOrglogo().equals(null)){
			 		//TODO write out a blanck org image
			 		return;
			 	}
			 
			 	InputStream in = new ByteArrayInputStream(profile.getOrglogo());
				BufferedImage orgLogo = ImageIO.read(in); 

				BufferedImage profilePic = new BufferedImage(100, 200, orgLogo.getType());
			    Graphics2D g = orgLogo.createGraphics();
			    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			    //PROBABLY ERROR HERE
			    g.drawImage(orgLogo, 0, 0, 0, 0, 0, 0, orgLogo.getWidth(), orgLogo.getHeight(), null);
			    g.dispose();

			 	OutputStream out = response.getOutputStream();
         
			 	response.setContentType("image/jpg");
                ImageIO.write(profilePic,"jpg",out);
               
          out.flush();
          out.close();	 
	 
		 }		
		 
	
		 
		 
}
