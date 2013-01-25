package com.accoord.web;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.accoord.domain.*;


public interface AccoordDao{

	
	public void loadDefaultValues()throws Exception;
	public Organization getOrganization(String orgname) throws Exception;
	public UserEntity getUser(String orgname,String username) throws Exception;
	public List<RoleEntity> getRoles(String orgname,String username) throws Exception;
	public void register(String orgname,String username,String password, String email,String activationkey)throws Exception;
	public UserEntity getUser(String emailid) throws Exception;
	public Boolean confirmUser(String activationkey)throws Exception;
	public UserEntity getUserWithResetKey(String resetkey) throws Exception;
	public boolean updateUserPassword(String password,String resetkey)throws Exception;
	public boolean updateUserPassword(String password,String username,String organization)throws Exception;
	public void addOrgUser(String organization,String username,String password,String email)throws Exception;
	public void addFile(String user, String org, String filetype, byte[] file)throws Exception;
	public void addProfilePic(String user, String org, byte[] file)throws Exception;
	public void addOrgLogo(String user, String org, byte[] file)throws Exception;
}
