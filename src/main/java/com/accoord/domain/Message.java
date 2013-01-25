package com.accoord.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;

@RooJavaBean
@RooToString
@RooEntity
@Entity(name="message")
public class Message {
	
    @NotNull
    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date created;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name="owner")
    private UserEntity owner;
	
    private Boolean makepublic;
    
    @Lob()
    private byte[] picture;
    
    @Lob()
    private byte[] file;
    
    @ManyToOne
    @JoinColumn(name="assessmentarea")
    private AssessmentArea assessmentarea;
    
    @ManyToOne
    @JoinColumn(name="servicearea")
    private ServiceArea servicearea;
    
    @ManyToOne
    @JoinColumn(name="event")
    private Event event;
    
    @ManyToOne
    @JoinColumn(name="profile")
    private UserProfile profile;
    
    @ManyToOne
    @JoinColumn(name="orgprofile")
    private OrgProfile orgprofile;
    
    @OneToMany(fetch=FetchType.EAGER, mappedBy = "owner")
    private Set<Comment> comments = new HashSet<Comment>();
}
