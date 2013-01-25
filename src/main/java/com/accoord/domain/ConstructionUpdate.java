package com.accoord.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Entity(name="construction_update")
public class ConstructionUpdate {
	
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date updated;
    
    @NotNull
   	@ManyToOne
   	@JoinColumn(name="owner")
   	private UserEntity owner;
    
    private Float actualunitsbuilt;
    
    private String note;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name="project")
    private ConstructionProject project;
}
