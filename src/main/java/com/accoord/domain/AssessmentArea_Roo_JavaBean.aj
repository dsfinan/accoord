// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.accoord.domain;

import com.accoord.domain.Event;
import com.accoord.domain.Message;
import com.accoord.domain.RapidAssessment;
import com.accoord.domain.UserEntity;
import java.lang.Double;
import java.lang.String;
import java.util.Date;
import java.util.Set;

privileged aspect AssessmentArea_Roo_JavaBean {
    
    public String AssessmentArea.getName() {
        return this.name;
    }
    
    public void AssessmentArea.setName(String name) {
        this.name = name;
    }
    
    public String AssessmentArea.getDescription() {
        return this.description;
    }
    
    public void AssessmentArea.setDescription(String description) {
        this.description = description;
    }
    
    public Date AssessmentArea.getCreated() {
        return this.created;
    }
    
    public void AssessmentArea.setCreated(Date created) {
        this.created = created;
    }
    
    public String AssessmentArea.getPolygon() {
        return this.polygon;
    }
    
    public void AssessmentArea.setPolygon(String polygon) {
        this.polygon = polygon;
    }
    
    public Double AssessmentArea.getLastlat() {
        return this.lastlat;
    }
    
    public void AssessmentArea.setLastlat(Double lastlat) {
        this.lastlat = lastlat;
    }
    
    public Double AssessmentArea.getLastlng() {
        return this.lastlng;
    }
    
    public void AssessmentArea.setLastlng(Double lastlng) {
        this.lastlng = lastlng;
    }
    
    public Double AssessmentArea.getLastzoomlevel() {
        return this.lastzoomlevel;
    }
    
    public void AssessmentArea.setLastzoomlevel(Double lastzoomlevel) {
        this.lastzoomlevel = lastzoomlevel;
    }
    
    public UserEntity AssessmentArea.getOwner() {
        return this.owner;
    }
    
    public void AssessmentArea.setOwner(UserEntity owner) {
        this.owner = owner;
    }
    
    public Event AssessmentArea.getEvent() {
        return this.event;
    }
    
    public void AssessmentArea.setEvent(Event event) {
        this.event = event;
    }
    
    public Set<RapidAssessment> AssessmentArea.getRapidassessments() {
        return this.rapidassessments;
    }
    
    public void AssessmentArea.setRapidassessments(Set<RapidAssessment> rapidassessments) {
        this.rapidassessments = rapidassessments;
    }
    
    public Set<Message> AssessmentArea.getMessages() {
        return this.messages;
    }
    
    public void AssessmentArea.setMessages(Set<Message> messages) {
        this.messages = messages;
    }
    
}
