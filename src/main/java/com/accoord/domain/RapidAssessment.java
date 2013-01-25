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

import com.accoord.shared.EventType;
import com.accoord.shared.ShelterConditions;

@RooJavaBean
@RooToString
@RooEntity
@Entity(name="rapid_assessment")
public class RapidAssessment {
	
	
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date created;
	
    @NotNull
    @ManyToOne
    @JoinColumn(name="area")
    private AssessmentArea area;
	
    @NotNull
   	@ManyToOne
   	@JoinColumn(name="owner")
   	private UserEntity owner;
	
    
    //general assessment info, with gen_ prefix
    
    private Double gen_est_per_aff;
    
    private Double gen_est_per_aff_male;
    
    private Double gen_est_per_aff_female;
    
    private Double gen_est_num_dead;
    
    private Double gen_est_num_injured;
    
    private Double gen_est_num_missing;
    
    private String gen_likely_movements;
    
    private String gen_keypeople_contact; 
    
    private Boolean gen_being_registered; 
    
    //shelter assessment info, with she_ prefix    
    
    private Double she_est_num_withshelters;
    
    @Enumerated
    private ShelterConditions she_protection_elem;
    
    @Enumerated
    private ShelterConditions she_privacy;
    
    @Enumerated
    private ShelterConditions she_security;
    
    @Enumerated
    private ShelterConditions she_fire;
    
    private Double she_est_per_beds;
    
    private Double she_est_per_cooking;
    
    private Double she_est_per_plastic;
    
    
  //Watsan assessment info, with wat_ prefix    
    
    private Double wat_used_perday_l_drinking;
    
    private Double wat_used_perday_l_cooking;
    
    private Double wat_used_perday_l_bathe;
    
    private Double wat_est_min_collect;
    
    private Boolean wat_pipe_net_present;
    
    private Boolean wat_surface_present;
    
    private Boolean wat_vendors_present;
    
    private Double wat_est_def_funclat;
    
    private Double wat_est_avgper_funclat;
    
    private Double wat_est_def_open;
    
    private Double wat_est_def_managed;
    
    private Double wat_est_def_publat;
    
    private Double wat_est_def_famlat;
    
    private Boolean wat_def_latforwomen; 
    
    private Double wat_hp_num_wcontainers; 
    
    private Boolean wat_hp_handwashstation; 
    

  //TODO health 
    
  //TODO food  
}
