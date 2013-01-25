package com.accoord.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@RooJavaBean
@RooToString
@RooEntity
@Entity(name="role_entity")
public class RoleEntity {

    @NotNull
    private String name;
}
