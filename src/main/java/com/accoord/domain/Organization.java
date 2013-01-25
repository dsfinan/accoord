package com.accoord.domain;

import java.util.HashSet;
import java.util.Set;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import com.accoord.domain.UserEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@RooJavaBean
@RooToString
@RooEntity
@Entity(name="organization")
public class Organization {

    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name="admin")
    private UserEntity admin;
    
    @OneToMany(fetch=FetchType.EAGER, mappedBy = "organization")
    private Set<Office> offices = new HashSet<Office>();
    
    
    @ManyToOne
    @JoinColumn(name="profile")
    private OrgProfile profile;
    


}
