// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.accoord.domain;

import java.lang.String;

privileged aspect ConstructionProject_Roo_ToString {
    
    public String ConstructionProject.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Version: ").append(getVersion()).append(", ");
        sb.append("Createddate: ").append(getCreateddate()).append(", ");
        sb.append("Owner: ").append(getOwner()).append(", ");
        sb.append("Constructiontype: ").append(getConstructiontype()).append(", ");
        sb.append("Sheltertype: ").append(getSheltertype()).append(", ");
        sb.append("Description: ").append(getDescription()).append(", ");
        sb.append("Estimatedstartdate: ").append(getEstimatedstartdate()).append(", ");
        sb.append("Estimatedenddate: ").append(getEstimatedenddate()).append(", ");
        sb.append("Targetnumberofunits: ").append(getTargetnumberofunits()).append(", ");
        sb.append("Attachment: ").append(java.util.Arrays.toString(getAttachment())).append(", ");
        sb.append("Area: ").append(getArea()).append(", ");
        sb.append("Updates: ").append(getUpdates() == null ? "null" : getUpdates().size());
        return sb.toString();
    }
    
}
