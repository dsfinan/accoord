// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.accoord.domain;

import com.accoord.domain.Message;
import com.accoord.domain.Organization;
import java.lang.String;
import java.util.Set;

privileged aspect OrgProfile_Roo_JavaBean {
    

    
    public String OrgProfile.getAbout() {
        return this.about;
    }
    
    public void OrgProfile.setAbout(String about) {
        this.about = about;
    }
    
    public byte[] OrgProfile.getOrglogo() {
        return this.orglogo;
    }
    
    public void OrgProfile.setOrglogo(byte[] orglogo) {
        this.orglogo = orglogo;
    }
    
    public String OrgProfile.getWebsite() {
        return this.website;
    }
    
    public void OrgProfile.setWebsite(String website) {
        this.website = website;
    }
    
    public Set<Message> OrgProfile.getMessages() {
        return this.messages;
    }
    
    public void OrgProfile.setMessages(Set<Message> messages) {
        this.messages = messages;
    }
    
}
