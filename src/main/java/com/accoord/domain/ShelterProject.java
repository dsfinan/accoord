package com.accoord.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
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

import com.accoord.shared.ShelterType;

@RooJavaBean
@RooToString
@RooEntity
@Entity(name="shelter_project")
public class ShelterProject {
	
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date createddate;
	
	//TODO createdby UserEntity
    
	//TODO createdorg Organization
	
    @NotNull
    @Enumerated
    private ShelterType sheltertype;
    
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date estimatedstartdate;
    
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date estimatedenddate;
    
    @NotNull
    private Float targetnumberofunits;
    
    @Lob()
    private byte[] attachment;
        
    @NotNull
    @ManyToOne
    @JoinColumn(name="area")
    private ServiceArea area;
    
    @OneToMany(fetch=FetchType.EAGER, mappedBy = "project")
    private Set<ShelterUpdate> updates = new HashSet<ShelterUpdate>();
    
    
    
}
