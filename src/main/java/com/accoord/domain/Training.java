package com.accoord.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;

import com.accoord.shared.Sector;
import com.accoord.shared.TrainingType;

@RooJavaBean
@RooToString
@RooEntity
@Entity(name="training")
public class Training {
	
	@NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date date_performed;
	
	@NotNull
    @Enumerated
    private TrainingType training_type;
	
	@NotNull
    @Enumerated
    private Sector sector;
	
	private String description;
	
	private Double number_attended;  
	
  
    @NotNull
    @ManyToOne
    @JoinColumn(name="owner")
    private UserEntity owner;
    
   
    @ManyToOne
    @JoinColumn(name="area")
    private ServiceArea area;
	
	
}
