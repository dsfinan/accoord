// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.accoord.domain;

import com.accoord.domain.ConstructionUpdate;
import com.accoord.domain.ServiceArea;
import com.accoord.domain.UserEntity;
import com.accoord.shared.ConstructionType;
import com.accoord.shared.ShelterType;
import java.lang.Float;
import java.lang.String;
import java.util.Date;
import java.util.Set;

privileged aspect ConstructionProject_Roo_JavaBean {
    
    public Date ConstructionProject.getCreateddate() {
        return this.createddate;
    }
    
    public void ConstructionProject.setCreateddate(Date createddate) {
        this.createddate = createddate;
    }
    
    public UserEntity ConstructionProject.getOwner() {
        return this.owner;
    }
    
    public void ConstructionProject.setOwner(UserEntity owner) {
        this.owner = owner;
    }
    
    public ConstructionType ConstructionProject.getConstructiontype() {
        return this.constructiontype;
    }
    
    public void ConstructionProject.setConstructiontype(ConstructionType constructiontype) {
        this.constructiontype = constructiontype;
    }
    
    public ShelterType ConstructionProject.getSheltertype() {
        return this.sheltertype;
    }
    
    public void ConstructionProject.setSheltertype(ShelterType sheltertype) {
        this.sheltertype = sheltertype;
    }
    
    public String ConstructionProject.getDescription() {
        return this.description;
    }
    
    public void ConstructionProject.setDescription(String description) {
        this.description = description;
    }
    
    public Date ConstructionProject.getEstimatedstartdate() {
        return this.estimatedstartdate;
    }
    
    public void ConstructionProject.setEstimatedstartdate(Date estimatedstartdate) {
        this.estimatedstartdate = estimatedstartdate;
    }
    
    public Date ConstructionProject.getEstimatedenddate() {
        return this.estimatedenddate;
    }
    
    public void ConstructionProject.setEstimatedenddate(Date estimatedenddate) {
        this.estimatedenddate = estimatedenddate;
    }
    
    public Float ConstructionProject.getTargetnumberofunits() {
        return this.targetnumberofunits;
    }
    
    public void ConstructionProject.setTargetnumberofunits(Float targetnumberofunits) {
        this.targetnumberofunits = targetnumberofunits;
    }
    
    public byte[] ConstructionProject.getAttachment() {
        return this.attachment;
    }
    
    public void ConstructionProject.setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }
    
    public ServiceArea ConstructionProject.getArea() {
        return this.area;
    }
    
    public void ConstructionProject.setArea(ServiceArea area) {
        this.area = area;
    }
    
    public Set<ConstructionUpdate> ConstructionProject.getUpdates() {
        return this.updates;
    }
    
    public void ConstructionProject.setUpdates(Set<ConstructionUpdate> updates) {
        this.updates = updates;
    }
    
}