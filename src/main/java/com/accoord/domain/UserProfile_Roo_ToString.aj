// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.accoord.domain;

import java.lang.String;

privileged aspect UserProfile_Roo_ToString {
    
    public String UserProfile.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Version: ").append(getVersion()).append(", ");
        sb.append("Profilepicture: ").append(java.util.Arrays.toString(getProfilepicture())).append(", ");
        sb.append("Bio: ").append(getBio()).append(", ");
        sb.append("Skype: ").append(getSkype()).append(", ");
        sb.append("Twitter: ").append(getTwitter()).append(", ");
        sb.append("Linkedin: ").append(getLinkedin()).append(", ");
        sb.append("Messages: ").append(getMessages() == null ? "null" : getMessages().size());
        return sb.toString();
    }
    
}
