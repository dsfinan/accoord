package com.accoord.web;

import java.io.File;
import java.util.*;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.security.util.EncryptionUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.RequestContext;

import com.accoord.domain.*;
import com.accoord.util.AccoordUtil;

public class AccoordDaoImpl extends HibernateDaoSupport  implements AccoordDao {

	@Override
	public Organization getOrganization(String orgname) throws Exception {
		List result=getHibernateTemplate().find("from com.accoord.domain.Organization s Where s.name=?",new Object[]{orgname});
		if(result.size()>0)
		{
			return (Organization)result.get(0);
		}
		return null;
	}
	
	@Override
	@Transactional
	public void loadDefaultValues()throws Exception
	{
		
		List result=getHibernateTemplate().find("from com.accoord.domain.Organization s ",new Object[]{});
		if(result.size()>0)
		{
			return;
		}
		ResourceBundle rb = ResourceBundle.getBundle("DefaultUser",Locale.ENGLISH);
		Organization organization=new Organization();
		organization.setName(rb.getString("orgname"));
		
		//create a org profile
		OrgProfile op = new OrgProfile();
		op.setAbout("Tells us about your Organization"); //TODO Internationalization i18n messages
		op.setVersion(1);
				//create a user profile
		UserProfile up = new UserProfile();
		up.setBio("Tell us about yourself");  //TODO Internationalization i18n messages
		up.setVersion(1);
		
		UserEntity user=new UserEntity();
		user.setEmail(rb.getString("email"));
		user.setOrganization(organization);
		user.setPassword(AccoordUtil.encrypt(rb.getString("password")));
		user.setResetkey(AccoordUtil.encrypt(rb.getString("password")+System.currentTimeMillis()));
		user.setUsername(rb.getString("username"));
		user.setVerified(true);
		user.setActivationkey(AccoordUtil.encrypt(rb.getString("password")+System.currentTimeMillis()));
		organization.setAdmin(user);
		
		//set the profiles
		organization.setOrgprofile(op);
		user.setProfile(up);
		
		RoleEntity role=new RoleEntity();
		role.setName("SUPER_ADMIN");
		
		RoleEntity role2=new RoleEntity();
		role2.setName("ORG_ADMIN");
		
		RoleEntity role3=new RoleEntity();
		role3.setName("USER");
		
		UserRole ur=new UserRole();
		ur.setUserentity(user);
		ur.setRoleentity(role);
	
		UserRole ur3=new UserRole();
		ur3.setUserentity(user);
		ur3.setRoleentity(role2);
	
		
		getHibernateTemplate().save(user);
		getHibernateTemplate().save(organization);
		
		getHibernateTemplate().save(role);
		getHibernateTemplate().save(role2);
		getHibernateTemplate().save(role3);
		
		getHibernateTemplate().save(ur);
		
		getHibernateTemplate().save(ur3);
	
	}

	@Override
	@Transactional
	public UserEntity getUser(String orgname,String username) throws Exception
	{
		List result=getHibernateTemplate().find("from com.accoord.domain.UserEntity s Where s.username=? AND s.organization.name=?",new Object[]{username,orgname});
		if(result.size()>0)
		{
			return (UserEntity)result.get(0);
		}
		return null;
	
	}

	@Override
	@Transactional
	public boolean updateUserPassword(String password,String resetkey)throws Exception
	{
		List result=getHibernateTemplate().find("from com.accoord.domain.UserEntity s Where s.resetkey=?",new Object[]{resetkey});
		if(result.size()<=0 || "".equals(password))
		{
			return false;
		}
		UserEntity ue=(UserEntity)result.get(0);
		ue.setPassword(AccoordUtil.encrypt(password));
		ue.setResetkey(AccoordUtil.encrypt(password)+System.currentTimeMillis()+Math.random());
		getHibernateTemplate().persist(ue);
		return true;
	}
	
	@Override
	@Transactional
	public boolean updateUserPassword(String password,String username,String organization)throws Exception
	{
		List result=getHibernateTemplate().find("from com.accoord.domain.UserEntity s Where s.username=? And s.organization.name=?",new Object[]{username,organization});
		if(result.size()<=0 || "".equals(password))
		{
			return false;
		}
		UserEntity ue=(UserEntity)result.get(0);
		ue.setPassword(AccoordUtil.encrypt(password));
		ue.setResetkey(AccoordUtil.encrypt(password)+System.currentTimeMillis()+Math.random());
		getHibernateTemplate().persist(ue);
		return true;
	}
	
	@Override
	@Transactional
	public UserEntity getUserWithResetKey(String resetkey) throws Exception
	{
		List result=getHibernateTemplate().find("from com.accoord.domain.UserEntity s Where s.resetkey=?",new Object[]{resetkey});
		if(result.size()>0)
		{
			return (UserEntity)result.get(0);
		}
		return null;
	
	}
	
	

	@Override
	@Transactional
	public UserEntity getUser(String email) throws Exception
	{
		List result=getHibernateTemplate().find("from com.accoord.domain.UserEntity s Where s.email=?",new Object[]{email});
		if(result.size()>0)
		{
			return (UserEntity)result.get(0);
		}
		return null;
	
	}
	@Override
	@Transactional
	public List<RoleEntity> getRoles(String orgname,String username) throws Exception
	{
		List result=getHibernateTemplate().find("from com.accoord.domain.UserRole s Where s.userentity.username=? AND s.userentity.organization.name=?",new Object[]{username,orgname});
		List<RoleEntity> ls=new ArrayList<RoleEntity>();
		for(Object o:result)
		{
			RoleEntity re=((UserRole)o).getRoleentity();
			ls.add(re);
		}
		
		return ls;
	}
	@Override
	@Transactional
	public void register(String orgname,String username,String password, String email,String activationkey)throws Exception
	{
		
		Organization o=new Organization();
		o.setName(orgname);
				
		//create a org profile
		OrgProfile op = new OrgProfile();
		op.setAbout("Tells us about your Organization");
		
				//create a user profile
		UserProfile up = new UserProfile();
		up.setBio("Tell us about yourself");
		
		UserEntity user=new UserEntity();
		user.setUsername(username);
		user.setPassword(AccoordUtil.encrypt(password));
		user.setEmail(email);
		user.setVerified(false);
		user.setProfile(up);
		user.setOrganization(o);
		user.setActivationkey(activationkey);
		user.setResetkey(AccoordUtil.encrypt(System.currentTimeMillis()+password));
		o.setAdmin(user);
		o.setOrgprofile(op);
		List result=getHibernateTemplate().find("from com.accoord.domain.RoleEntity s Where s.name=?",new Object[]{"ORG_ADMIN"});
		RoleEntity role=(RoleEntity)result.get(0);
		UserRole ur=new UserRole();
		ur.setUserentity(user);
		ur.setRoleentity(role);
		
		getHibernateTemplate().save(user);
		getHibernateTemplate().save(o);
		getHibernateTemplate().save(ur);
		getHibernateTemplate().save(op);
		getHibernateTemplate().save(up);
		
	}
	
	@Override
	@Transactional
	public Boolean confirmUser(String activationkey)throws Exception
	{
		try
		{
			List result=getHibernateTemplate().find("from com.accoord.domain.UserEntity s Where s.activationkey=?",new Object[]{activationkey});
			UserEntity u=(UserEntity)result.get(0);
			u.setVerified(true);
			u.setActivationkey(AccoordUtil.encrypt(activationkey+System.currentTimeMillis()+Math.random()));
			getHibernateTemplate().save(u);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	@Override
	@Transactional
	public void addOrgUser(String organization,String username,String password,String email)throws Exception
	{
		List result=getHibernateTemplate().find("from com.accoord.domain.RoleEntity s Where s.name=?",new Object[]{"USER"});
		RoleEntity role=(RoleEntity)result.get(0);
		
		result=getHibernateTemplate().find("from com.accoord.domain.Organization s Where s.name=?",new Object[]{organization});
		Organization org=(Organization)result.get(0);
		
		//assign a profile blank picture
		File profilepic = new File("/resources/images/icons/userIcons/blankProfile.jpg");
		byte[] ppArray = AccoordUtil.getBytesFromFile(profilepic);
		//create a user profile
		UserProfile up = new UserProfile();
		up.setProfilepicture(ppArray);
		
		
		UserEntity ue=new UserEntity();
		ue.setUsername(username);
		ue.setPassword(AccoordUtil.encrypt(password));
		ue.setEmail(email);
		ue.setProfile(up);
		ue.setOrganization(org);
		ue.setResetkey(AccoordUtil.encrypt(password+System.currentTimeMillis()+Math.random()));
		ue.setVerified(true);
		ue.setActivationkey(AccoordUtil.encrypt(password+System.currentTimeMillis()+Math.random()));
		
		UserRole ur=new UserRole();
		ur.setUserentity(ue);
		ur.setRoleentity(role);
		
		getHibernateTemplate().persist(ue);
		getHibernateTemplate().persist(ur);
		getHibernateTemplate().persist(up);
		
	}

	@Override
	@Transactional
	public void addFile(String user, String org, String filetype, byte[] file)throws Exception
	{
		List result=getHibernateTemplate().find("from com.accoord.domain.UserEntity s Where s.username=? And s.organization.name=?",new Object[]{user,org});
		UserEntity userentity=(UserEntity)result.get(0);
		
		
		//Profiles should already exist from register and addorguser methods
		

    		if (filetype == "profilepic"){
    			
    			
				UserProfile up = userentity.getProfile();
				up.setProfilepicture(file);
				//userentity.setProfile(up);
				//DO I NEED TO SAVE USERENTITY?  Probably just the profile since relationship alread exisits
				//getHibernateTemplate().save(userentity);
				getHibernateTemplate().save(up);					
				
			
    		}else if (filetype =="orglogo"){
					
					Organization organization = userentity.getOrganization();
					OrgProfile op = organization.getOrgprofile();
					op.setOrglogo(file);
					//organization.setOrgprofile(op);
					//getHibernateTemplate().save(organization);
					getHibernateTemplate().save(op);					
				
		
    		}



			//getHibernateTemplate().persist(up);
		//userentity.setProfilepicture(file);
		//getHibernateTemplate().persist(userentity);
		
	}

	@Override
	@Transactional
	public void addProfilePic(String user, String org, byte[] file)throws Exception {

		
		List result=getHibernateTemplate().find("from com.accoord.domain.UserEntity s Where s.username=? And s.organization.name=?",new Object[]{user,org});
		UserEntity userentity=(UserEntity)result.get(0);
		UserProfile up = userentity.getProfile();
		up.setProfilepicture(file);
		userentity.setProfile(up);
		//DO I NEED TO SAVE USERENTITY?  Probably just the profile since relationship alread exisits
		
		
		getHibernateTemplate().save(up);
		getHibernateTemplate().save(userentity);
		
		
	}

	@Override
	public void addOrgLogo(String user, String org, byte[] file) throws Exception {

		List result=getHibernateTemplate().find("from com.accoord.domain.UserEntity s Where s.username=? And s.organization.name=?",new Object[]{user,org});
		UserEntity userentity=(UserEntity)result.get(0);
		Organization organization = userentity.getOrganization();
		if (organization.getOrgprofile().equals(null)){
			OrgProfile op = new OrgProfile();
			op.setOrglogo(file);
			getHibernateTemplate().save(op);	
			organization.setOrgprofile(op);
		}else{
			OrgProfile op = organization.getOrgprofile();
			op.setOrglogo(file);
			getHibernateTemplate().save(op);
			organization.setOrgprofile(op);
		}
			
		getHibernateTemplate().save(organization);
		
		
		
		
	}	




	
	
}
