// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.accoord.domain;

import com.accoord.domain.Message;
import com.accoord.domain.UserEntity;
import java.lang.String;
import java.util.Set;

privileged aspect UserProfile_Roo_JavaBean {
    

    public byte[] UserProfile.getProfilepicture() {
        return this.profilepicture;
    }
    
    public void UserProfile.setProfilepicture(byte[] profilepicture) {
        this.profilepicture = profilepicture;
    }
    
    public String UserProfile.getBio() {
        return this.bio;
    }
    
    public void UserProfile.setBio(String bio) {
        this.bio = bio;
    }
    
    public String UserProfile.getSkype() {
        return this.skype;
    }
    
    public void UserProfile.setSkype(String skype) {
        this.skype = skype;
    }
    
    public String UserProfile.getTwitter() {
        return this.twitter;
    }
    
    public void UserProfile.setTwitter(String twitter) {
        this.twitter = twitter;
    }
    
    public String UserProfile.getLinkedin() {
        return this.linkedin;
    }
    
    public void UserProfile.setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }
    
    public Set<Message> UserProfile.getMessages() {
        return this.messages;
    }
    
    public void UserProfile.setMessages(Set<Message> messages) {
        this.messages = messages;
    }
    
}
