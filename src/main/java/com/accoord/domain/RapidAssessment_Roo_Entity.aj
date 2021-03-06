// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.accoord.domain;

import com.accoord.domain.RapidAssessment;
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

privileged aspect RapidAssessment_Roo_Entity {
    
    @PersistenceContext
    transient EntityManager RapidAssessment.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long RapidAssessment.id;
    
    @Version
    @Column(name = "version")
    private Integer RapidAssessment.version;
    
    public Long RapidAssessment.getId() {
        return this.id;
    }
    
    public void RapidAssessment.setId(Long id) {
        this.id = id;
    }
    
    public Integer RapidAssessment.getVersion() {
        return this.version;
    }
    
    public void RapidAssessment.setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void RapidAssessment.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void RapidAssessment.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            RapidAssessment attached = this.entityManager.find(this.getClass(), this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void RapidAssessment.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public RapidAssessment RapidAssessment.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        RapidAssessment merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager RapidAssessment.entityManager() {
        EntityManager em = new RapidAssessment().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long RapidAssessment.countRapidAssessments() {
        return entityManager().createQuery("select count(o) from com.accoord.domain.RapidAssessment o", Long.class).getSingleResult();
    }
    
    public static List<RapidAssessment> RapidAssessment.findAllRapidAssessments() {
        return entityManager().createQuery("select o from com.accoord.domain.RapidAssessment o", RapidAssessment.class).getResultList();
    }
    
    public static RapidAssessment RapidAssessment.findRapidAssessment(Long id) {
        if (id == null) return null;
        return entityManager().find(RapidAssessment.class, id);
    }
    
    public static List<RapidAssessment> RapidAssessment.findRapidAssessmentEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from com.accoord.domain.RapidAssessment o", RapidAssessment.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
