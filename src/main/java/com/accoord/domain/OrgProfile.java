package com.accoord.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;

@RooJavaBean
@RooToString
@RooEntity
@Entity(name="org_profile")
public class OrgProfile {
	
	

	private String about;
	
    @Lob()
    private byte[] orglogo;
	
    private String website;
    
    @OneToMany(fetch=FetchType.EAGER, mappedBy = "orgprofile")
    private Set<Message> messages = new HashSet<Message>();
    

    
    //TODO add collection of users
    
}
