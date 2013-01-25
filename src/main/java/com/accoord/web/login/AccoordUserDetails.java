package com.accoord.web.login;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

import javax.swing.ImageIcon;



import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

import com.accoord.domain.UserEntity;

public class AccoordUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String organization;
	private String firstname;
	private String lastname;
	private Collection<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
	private BufferedImage profilePic;
	private UserEntity userObject;
	

	
	
	public AccoordUserDetails(String username,String password) 
	{
		this.username=username;
		this.password=password;
	
	}
	
	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
		
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getLastname() {
		return lastname;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getOrganization() {
		return organization;
	}

	public void setProfilePic(BufferedImage profilePic) {

		this.profilePic = profilePic;
		
	}

	public BufferedImage getProfilePic() {
		return profilePic;
		//return null;
	}

	public void setUserObject(UserEntity userObject) {
		this.userObject = userObject;
	}

	public UserEntity getUserObject() {
		return userObject;
	}
	
}
