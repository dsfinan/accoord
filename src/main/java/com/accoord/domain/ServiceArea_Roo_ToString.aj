// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.accoord.domain;

import java.lang.String;

privileged aspect ServiceArea_Roo_ToString {
    
    public String ServiceArea.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Version: ").append(getVersion()).append(", ");
        sb.append("Name: ").append(getName()).append(", ");
        sb.append("Description: ").append(getDescription()).append(", ");
        sb.append("Created: ").append(getCreated()).append(", ");
        sb.append("Polygon: ").append(getPolygon()).append(", ");
        sb.append("Lastlat: ").append(getLastlat()).append(", ");
        sb.append("Lastlng: ").append(getLastlng()).append(", ");
        sb.append("Lastzoomlevel: ").append(getLastzoomlevel()).append(", ");
        sb.append("Owner: ").append(getOwner()).append(", ");
        sb.append("Event: ").append(getEvent()).append(", ");
        sb.append("Equipment: ").append(getEquipment() == null ? "null" : getEquipment().size()).append(", ");
        sb.append("Constructionprojects: ").append(getConstructionprojects() == null ? "null" : getConstructionprojects().size()).append(", ");
        sb.append("Shelterprojects: ").append(getShelterprojects() == null ? "null" : getShelterprojects().size()).append(", ");
        sb.append("Trainings: ").append(getTrainings() == null ? "null" : getTrainings().size()).append(", ");
        sb.append("Distributions: ").append(getDistributions() == null ? "null" : getDistributions().size()).append(", ");
        sb.append("Messages: ").append(getMessages() == null ? "null" : getMessages().size());
        return sb.toString();
    }
    
}