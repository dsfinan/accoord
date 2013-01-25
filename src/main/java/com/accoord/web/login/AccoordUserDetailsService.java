package com.accoord.web.login;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.*;
import org.springframework.security.core.userdetails.*;

import com.accoord.domain.RoleEntity;
import com.accoord.domain.UserEntity;
import com.accoord.util.AccoordUtil;

public class AccoordUserDetailsService extends HibernateDaoSupport implements UserDetailsService
{
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		
		
		try
		{
			
			String user=username.split("/")[0];
			String orgname=username.split("/")[1];
					
			
			UserEntity ue=AccoordUtil.getDataAccess().getUser(orgname, user);
			if(!ue.getVerified())
			{
				throw new Exception("Email Id not confirmed for user"+username);
			}

			AccoordUserDetails aud=new AccoordUserDetails(user,ue.getPassword());
			aud.setOrganization(orgname);
			
			//set the UserEntity
			aud.setUserObject(ue);
			
			aud.setFirstname(ue.getFirstname());
			aud.setLastname(ue.getLastname());
			
			//get the profile pic byte array and convert to thumbnail and then set to aud
			//check to see if there is a image in the DB
			//TODO could cause nullpoint exception?
			if (ue.getProfilepicture()!= null){
				InputStream in = new ByteArrayInputStream(ue.getProfilepicture());
				BufferedImage profilePic = ImageIO.read(in); 
				aud.setProfilePic(profilePic);
			}

	
			Collection<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
			List<RoleEntity> ls=AccoordUtil.getDataAccess().getRoles(orgname, user);
			for(RoleEntity re:ls)
			{
				authorities.add(new GrantedAuthorityImpl(re.getName()));
			}
			aud.setAuthorities(authorities);
			return aud; 
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException(username+" not found");
		
		}
	}

}
