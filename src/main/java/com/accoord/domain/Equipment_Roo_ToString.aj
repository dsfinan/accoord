// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.accoord.domain;

import java.lang.String;

privileged aspect Equipment_Roo_ToString {
    
    public String Equipment.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Version: ").append(getVersion()).append(", ");
        sb.append("Created: ").append(getCreated()).append(", ");
        sb.append("Sector: ").append(getSector()).append(", ");
        sb.append("Description: ").append(getDescription()).append(", ");
        sb.append("Lat: ").append(getLat()).append(", ");
        sb.append("Lng: ").append(getLng()).append(", ");
        sb.append("Owner: ").append(getOwner()).append(", ");
        sb.append("Lastlat: ").append(getLastlat()).append(", ");
        sb.append("Lastlng: ").append(getLastlng()).append(", ");
        sb.append("Lastzoomlevel: ").append(getLastzoomlevel()).append(", ");
        sb.append("Area: ").append(getArea());
        return sb.toString();
    }
    
}
