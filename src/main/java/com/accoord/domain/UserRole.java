package com.accoord.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import com.accoord.domain.UserEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.accoord.domain.RoleEntity;

@RooJavaBean
@RooToString
@RooEntity
@Entity(name="user_role")
public class UserRole {

    @ManyToOne
    @JoinColumn(name="userentity")
    private UserEntity userentity;

    @ManyToOne
    @JoinColumn(name="roleentity")
    private RoleEntity roleentity;

    
}
