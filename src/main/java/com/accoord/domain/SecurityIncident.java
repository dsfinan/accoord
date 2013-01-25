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

import com.accoord.shared.SecurityIncidentType;

@RooJavaBean
@RooToString
@RooEntity
@Entity(name="security_incident")
public class SecurityIncident {
	
	@NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date incidentdate;

    private String subject;

    @NotNull
    @Enumerated
    private SecurityIncidentType type;

    private String description;

    private Float lat;

    private Float lng;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name="owner")
    private UserEntity owner;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name="event")
    private Event event;

	
	
	
	
}
