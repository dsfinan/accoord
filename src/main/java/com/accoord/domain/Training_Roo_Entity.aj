// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.accoord.domain;

import com.accoord.domain.Training;
import java.lang.Integer;
import java.lang.Long;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Training_Roo_Entity {
    
    //declare @type: Training: @Entity;
    
    @PersistenceContext
    transient EntityManager Training.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Training.id;
    
    @Version
    @Column(name = "version")
    private Integer Training.version;
    
    public Long Training.getId() {
        return this.id;
    }
    
    public void Training.setId(Long id) {
        this.id = id;
    }
    
    public Integer Training.getVersion() {
        return this.version;
    }
    
    public void Training.setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void Training.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Training.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Training attached = this.entityManager.find(this.getClass(), this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Training.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public Training Training.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Training merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager Training.entityManager() {
        EntityManager em = new Training().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Training.countTrainings() {
        return entityManager().createQuery("select count(o) from com.accoord.domain.Training o", Long.class).getSingleResult();
    }
    
    public static List<Training> Training.findAllTrainings() {
        return entityManager().createQuery("select o from com.accoord.domain.Training o", Training.class).getResultList();
    }
    
    public static Training Training.findTraining(Long id) {
        if (id == null) return null;
        return entityManager().find(Training.class, id);
    }
    
    public static List<Training> Training.findTrainingEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from com.accoord.domain.Training o", Training.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
