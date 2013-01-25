package com.accoord.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;

@RooJavaBean
@RooToString
@RooEntity
@Entity(name="user_profile")
public class UserProfile {
	
    
    @Lob()
    private byte[] profilepicture;
    
    @Size(min = 3, max = 160)
    private String bio;

    private String skype;
    
    private String twitter;
    
    private String linkedin;
    
	
    @OneToMany(fetch=FetchType.EAGER, mappedBy = "profile")
    private Set<Message> messages = new HashSet<Message>();
    
    
    
}
