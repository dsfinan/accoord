// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.accoord.domain;

import com.accoord.domain.AssessmentArea;
import com.accoord.domain.Comment;
import com.accoord.domain.Event;
import com.accoord.domain.OrgProfile;
import com.accoord.domain.ServiceArea;
import com.accoord.domain.UserEntity;
import com.accoord.domain.UserProfile;
import java.lang.Boolean;
import java.lang.String;
import java.util.Date;
import java.util.Set;

privileged aspect Message_Roo_JavaBean {
    
    public String Message.getMessage() {
        return this.message;
    }
    
    public void Message.setMessage(String message) {
        this.message = message;
    }
    
    public Date Message.getCreated() {
        return this.created;
    }
    
    public void Message.setCreated(Date created) {
        this.created = created;
    }
    
    public UserEntity Message.getOwner() {
        return this.owner;
    }
    
    public void Message.setOwner(UserEntity owner) {
        this.owner = owner;
    }
    
    public Boolean Message.getMakepublic() {
        return this.makepublic;
    }
    
    public void Message.setMakepublic(Boolean makepublic) {
        this.makepublic = makepublic;
    }
    
    public byte[] Message.getPicture() {
        return this.picture;
    }
    
    public void Message.setPicture(byte[] picture) {
        this.picture = picture;
    }
    
    public byte[] Message.getFile() {
        return this.file;
    }
    
    public void Message.setFile(byte[] file) {
        this.file = file;
    }
    
    public AssessmentArea Message.getAssessmentarea() {
        return this.assessmentarea;
    }
    
    public void Message.setAssessmentarea(AssessmentArea assessmentarea) {
        this.assessmentarea = assessmentarea;
    }
    
    public ServiceArea Message.getServicearea() {
        return this.servicearea;
    }
    
    public void Message.setServicearea(ServiceArea servicearea) {
        this.servicearea = servicearea;
    }
    
    public Event Message.getEvent() {
        return this.event;
    }
    
    public void Message.setEvent(Event event) {
        this.event = event;
    }
    
    public UserProfile Message.getProfile() {
        return this.profile;
    }
    
    public void Message.setProfile(UserProfile profile) {
        this.profile = profile;
    }
    
    public OrgProfile Message.getOrgprofile() {
        return this.orgprofile;
    }
    
    public void Message.setOrgprofile(OrgProfile orgprofile) {
        this.orgprofile = orgprofile;
    }
    
    public Set<Comment> Message.getComments() {
        return this.comments;
    }
    
    public void Message.setComments(Set<Comment> comments) {
        this.comments = comments;
    }
    
}
