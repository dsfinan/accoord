package com.accoord.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
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
@Entity(name="service_area")
public class ServiceArea { 
	 	@NotNull
	    private String name;

	    private String description;

	    @Temporal(TemporalType.TIMESTAMP)
	    @DateTimeFormat(style = "S-")
	    private Date created;

	    @NotNull
	    @Column(length=100000)
	    private String polygon;

	    private Double lastlat;
	    
	    private Double lastlng;

	    private Double lastzoomlevel;
	    
	    @NotNull
	    @ManyToOne
	    @JoinColumn(name="owner")
	    private UserEntity owner;
	    	    
	    @NotNull
	    @ManyToOne
	    @JoinColumn(name="event")
	    private Event event;
	    
	    @OneToMany(fetch=FetchType.EAGER, mappedBy = "area")
	    private Set<Equipment> equipment = new HashSet<Equipment>();
	    
	    @OneToMany(fetch=FetchType.EAGER, mappedBy = "area")
	    private Set<ConstructionProject> constructionprojects = new HashSet<ConstructionProject>();
	    
	    @OneToMany(fetch=FetchType.EAGER, mappedBy = "area")
	    private Set<ShelterProject> shelterprojects = new HashSet<ShelterProject>();
	    
	    @OneToMany(fetch=FetchType.EAGER, mappedBy = "area")
	    private Set<Training> trainings = new HashSet<Training>();
	    
	    @OneToMany(fetch=FetchType.EAGER, mappedBy = "area")
	    private Set<Distribution> distributions = new HashSet<Distribution>();
	
	    @OneToMany(fetch=FetchType.EAGER, mappedBy = "servicearea")
	    private Set<Message> messages = new HashSet<Message>();
}
