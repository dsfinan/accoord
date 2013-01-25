// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.accoord.domain;

import com.accoord.domain.AssessmentArea;
import com.accoord.domain.EventDataOnDemand;
import com.accoord.domain.UserEntityDataOnDemand;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

privileged aspect AssessmentAreaDataOnDemand_Roo_DataOnDemand {
    
    declare @type: AssessmentAreaDataOnDemand: @Component;
    
    private Random AssessmentAreaDataOnDemand.rnd = new java.security.SecureRandom();
    
    private List<AssessmentArea> AssessmentAreaDataOnDemand.data;
    
    @Autowired
    private EventDataOnDemand AssessmentAreaDataOnDemand.eventDataOnDemand;
    
    @Autowired
    private UserEntityDataOnDemand AssessmentAreaDataOnDemand.userEntityDataOnDemand;
    
    public AssessmentArea AssessmentAreaDataOnDemand.getNewTransientAssessmentArea(int index) {
        com.accoord.domain.AssessmentArea obj = new com.accoord.domain.AssessmentArea();
        obj.setCreated(new java.util.Date());
        obj.setDescription("description_" + index);
        obj.setEvent(eventDataOnDemand.getRandomEvent());
        obj.setLastlat(new Integer(index).doubleValue());
        obj.setLastlng(new Integer(index).doubleValue());
        obj.setLastzoomlevel(new Integer(index).doubleValue());
        obj.setName("name_" + index);
        obj.setOwner(userEntityDataOnDemand.getRandomUserEntity());
        java.lang.String polygon = "polygon_" + index;
        if (polygon.length() > 100000) {
            polygon  = polygon.substring(0, 100000);
        }
        obj.setPolygon(polygon);
        return obj;
    }
    
    public AssessmentArea AssessmentAreaDataOnDemand.getSpecificAssessmentArea(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        AssessmentArea obj = data.get(index);
        return AssessmentArea.findAssessmentArea(obj.getId());
    }
    
    public AssessmentArea AssessmentAreaDataOnDemand.getRandomAssessmentArea() {
        init();
        AssessmentArea obj = data.get(rnd.nextInt(data.size()));
        return AssessmentArea.findAssessmentArea(obj.getId());
    }
    
    public boolean AssessmentAreaDataOnDemand.modifyAssessmentArea(AssessmentArea obj) {
        return false;
    }
    
    public void AssessmentAreaDataOnDemand.init() {
        data = com.accoord.domain.AssessmentArea.findAssessmentAreaEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'AssessmentArea' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new java.util.ArrayList<com.accoord.domain.AssessmentArea>();
        for (int i = 0; i < 10; i++) {
            com.accoord.domain.AssessmentArea obj = getNewTransientAssessmentArea(i);
            obj.persist();
            obj.flush();
            data.add(obj);
        }
    }
    
}