// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.accoord.domain;

import java.lang.String;

privileged aspect ShelterUpdate_Roo_ToString {
    
    public String ShelterUpdate.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Version: ").append(getVersion()).append(", ");
        sb.append("Updated: ").append(getUpdated()).append(", ");
        sb.append("Actualunitsbuilt: ").append(getActualunitsbuilt()).append(", ");
        sb.append("Project: ").append(getProject());
        return sb.toString();
    }
    
}
