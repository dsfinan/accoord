// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.accoord.domain;

import com.accoord.domain.UserEntity;
import java.lang.String;
import java.util.Date;

privileged aspect Comment_Roo_JavaBean {
    
    public String Comment.getComment() {
        return this.comment;
    }
    
    public void Comment.setComment(String comment) {
        this.comment = comment;
    }
    
    public Date Comment.getCreated() {
        return this.created;
    }
    
    public void Comment.setCreated(Date created) {
        this.created = created;
    }
    
    public UserEntity Comment.getOwner() {
        return this.owner;
    }
    
    public void Comment.setOwner(UserEntity owner) {
        this.owner = owner;
    }
    
}