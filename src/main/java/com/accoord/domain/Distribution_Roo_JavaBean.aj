// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.accoord.domain;

import com.accoord.domain.ServiceArea;
import com.accoord.domain.UserEntity;
import com.accoord.shared.DistributionType;
import com.accoord.shared.Sector;
import java.lang.Double;
import java.lang.String;
import java.util.Date;

privileged aspect Distribution_Roo_JavaBean {
    
    public Date Distribution.getDateperformed() {
        return this.dateperformed;
    }
    
    public void Distribution.setDateperformed(Date dateperformed) {
        this.dateperformed = dateperformed;
    }
    
    public DistributionType Distribution.getDistributiontype() {
        return this.distributiontype;
    }
    
    public void Distribution.setDistributiontype(DistributionType distributiontype) {
        this.distributiontype = distributiontype;
    }
    
    public Sector Distribution.getSector() {
        return this.sector;
    }
    
    public void Distribution.setSector(Sector sector) {
        this.sector = sector;
    }
    
    public String Distribution.getDescription() {
        return this.description;
    }
    
    public void Distribution.setDescription(String description) {
        this.description = description;
    }
    
    public Double Distribution.getNumber_units() {
        return this.number_units;
    }
    
    public void Distribution.setNumber_units(Double number_units) {
        this.number_units = number_units;
    }
    
    public String Distribution.getUnits() {
        return this.units;
    }
    
    public void Distribution.setUnits(String units) {
        this.units = units;
    }
    
    public UserEntity Distribution.getOwner() {
        return this.owner;
    }
    
    public void Distribution.setOwner(UserEntity owner) {
        this.owner = owner;
    }
    
    public ServiceArea Distribution.getArea() {
        return this.area;
    }
    
    public void Distribution.setArea(ServiceArea area) {
        this.area = area;
    }
    
}
