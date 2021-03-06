// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.accoord.domain;

import com.accoord.domain.AssessmentArea;
import java.lang.Integer;
import java.lang.Long;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;
import org.springframework.transaction.annotation.Transactional;

privileged aspect AssessmentArea_Roo_Entity {
    
    @PersistenceContext
    transient EntityManager AssessmentArea.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long AssessmentArea.id;
    
    @Version
    @Column(name = "version")
    private Integer AssessmentArea.version;
    
    public Long AssessmentArea.getId() {
        return this.id;
    }
    
    public void AssessmentArea.setId(Long id) {
        this.id = id;
    }
    
    public Integer AssessmentArea.getVersion() {
        return this.version;
    }
    
    public void AssessmentArea.setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void AssessmentArea.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void AssessmentArea.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            AssessmentArea attached = this.entityManager.find(this.getClass(), this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void AssessmentArea.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public AssessmentArea AssessmentArea.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        AssessmentArea merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager AssessmentArea.entityManager() {
        EntityManager em = new AssessmentArea().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long AssessmentArea.countAssessmentAreas() {
        return entityManager().createQuery("select count(o) from com.accoord.domain.AssessmentArea o", Long.class).getSingleResult();
    }
    
    public static List<AssessmentArea> AssessmentArea.findAllAssessmentAreas() {
        return entityManager().createQuery("select o from com.accoord.domain.AssessmentArea o", AssessmentArea.class).getResultList();
    }
    
    public static AssessmentArea AssessmentArea.findAssessmentArea(Long id) {
        if (id == null) return null;
        return entityManager().find(AssessmentArea.class, id);
    }
    
    public static List<AssessmentArea> AssessmentArea.findAssessmentAreaEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from com.accoord.domain.AssessmentArea o", AssessmentArea.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
