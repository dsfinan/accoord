package com.accoord.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;

import com.accoord.shared.EventType;


@RooJavaBean
@RooToString
@RooEntity
@Entity(name = "event")
public class Event {

    @NotNull
    private String name;

    private String description;

    private String country;

    @NotNull
    @Enumerated
    private EventType eventtype;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date created;

    @NotNull
    private Double lat;

    @NotNull
    private Double lng;

    @NotNull
    private Double zoomlevel;

    private Boolean open;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date dateclosed;


    
    @NotNull
    @ManyToOne
    @JoinColumn(name="owner")
    private UserEntity owner;
    
    
    @OneToMany(fetch=FetchType.EAGER, mappedBy = "event")
    @OrderBy("created asc") 
    private Set<AssessmentArea> assessmentareas = new HashSet<AssessmentArea>();
    
    @OneToMany(fetch=FetchType.EAGER, mappedBy = "event")
    @OrderBy("created asc") 
    private Set<ServiceArea> serviceareas = new HashSet<ServiceArea>();
    
    @OneToMany(fetch=FetchType.EAGER, mappedBy = "event")
    @OrderBy("incidentdate asc") 
    private Set<SecurityIncident> securityincidents = new HashSet<SecurityIncident>();

    @OneToMany(fetch=FetchType.EAGER, mappedBy = "event")
    @OrderBy("created asc") 
    private Set<Message> messages = new HashSet<Message>();
    
}
