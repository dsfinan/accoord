// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.accoord.domain;

import java.lang.String;

privileged aspect OrgProfile_Roo_ToString {
    
    public String OrgProfile.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Version: ").append(getVersion()).append(", ");
        sb.append("About: ").append(getAbout()).append(", ");
        sb.append("Orglogo: ").append(java.util.Arrays.toString(getOrglogo())).append(", ");
        sb.append("Website: ").append(getWebsite()).append(", ");
        sb.append("Messages: ").append(getMessages() == null ? "null" : getMessages().size());
        return sb.toString();
    }
    
}
