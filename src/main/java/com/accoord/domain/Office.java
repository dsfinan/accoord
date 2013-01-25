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

import com.accoord.shared.OfficeType;

@RooJavaBean
@RooToString
@RooEntity
@Entity(name="office")
public class Office {
	
	@NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date created;
	
	private Boolean open;

    @NotNull
    @Enumerated
    private OfficeType type;

    private String address;
    
    private String city;
    
    private String country;
 
    private String description;
    
    @NotNull
    private Float lat;
    @NotNull
    private Float lng;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name="owner")
    private UserEntity owner;
    
    
    @NotNull
    @ManyToOne
    @JoinColumn(name="organization")
    private Organization organization;

	
	
}
