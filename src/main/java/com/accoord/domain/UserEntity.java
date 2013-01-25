package com.accoord.domain;
 
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.accoord.domain.Organization;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@RooJavaBean
@RooToString
@RooEntity
@Entity(name="user_entity")
public class UserEntity {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String email;


    private String firstname;
    

    private String lastname;
    

    private Boolean verified;

    @ManyToOne
    @JoinColumn(name="organization")
    private Organization organization;

    @NotNull
    private String resetkey;
    
    @NotNull
    private String activationkey;

    @Lob()
    private byte[] profilepicture;
    
    @Size(min = 3, max = 160)
    private String bio;

    private String skype;
    
    private String gtalk;
    
    private String twitter;
    
    private String linkedin;
    
 
    
    
    
    //RELATIONSHIPS
    
    @ManyToOne
    @JoinColumn(name="profile")
    private UserProfile profile;
     
    @OneToMany(fetch=FetchType.EAGER, mappedBy = "owner")
    private Set<Event> events = new HashSet<Event>();
    
    @OneToMany(fetch=FetchType.EAGER, mappedBy = "owner")
    private Set<AssessmentArea> assessmentareas = new HashSet<AssessmentArea>();
    
    @OneToMany(fetch=FetchType.EAGER, mappedBy = "owner")
    private Set<ServiceArea> serviceareas = new HashSet<ServiceArea>();
    
    @OneToMany(fetch=FetchType.EAGER, mappedBy = "owner")
    private Set<SecurityIncident> securityincidents = new HashSet<SecurityIncident>();
    
    @OneToMany(fetch=FetchType.EAGER, mappedBy = "owner")
    private Set<Message> messages = new HashSet<Message>();
    
    @OneToMany(fetch=FetchType.EAGER, mappedBy = "owner")
    private Set<Comment> comments = new HashSet<Comment>();
    
    @OneToMany(fetch=FetchType.EAGER, mappedBy = "owner")
    private Set<RapidAssessment> rapidassessments = new HashSet<RapidAssessment>();
    
    @OneToMany(fetch=FetchType.EAGER, mappedBy = "owner")
    private Set<ConstructionProject> constructionprojects = new HashSet<ConstructionProject>();
    
    @OneToMany(fetch=FetchType.EAGER, mappedBy = "owner")
    private Set<ConstructionUpdate> constructionupdates = new HashSet<ConstructionUpdate>();
    
    @OneToMany(fetch=FetchType.EAGER, mappedBy = "owner")
    private Set<Distribution> distributions = new HashSet<Distribution>();
    
    @OneToMany(fetch=FetchType.EAGER, mappedBy = "owner")
    private Set<Training> trainings = new HashSet<Training>();
    
    @OneToMany(fetch=FetchType.EAGER, mappedBy = "owner")
    private Set<Warehouse> warehouses = new HashSet<Warehouse>();
    
    @OneToMany(fetch=FetchType.EAGER, mappedBy = "owner")
    private Set<Office> offices = new HashSet<Office>();
    
    @OneToMany(fetch=FetchType.EAGER, mappedBy = "owner")
    private Set<Equipment> equipment = new HashSet<Equipment>();
  

    
  
    //followers and following
    private HashMap<Long, String> followers = new HashMap<Long, String>();
    
    private HashMap<Long, String> following = new HashMap<Long, String>();
    
    

    
    

}
