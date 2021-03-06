// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.accoord.domain;

import com.accoord.domain.Distribution;
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

privileged aspect Distribution_Roo_Entity {
    
    //declare @type: Distribution: @Entity;
    
    @PersistenceContext
    transient EntityManager Distribution.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Distribution.id;
    
    @Version
    @Column(name = "version")
    private Integer Distribution.version;
    
    public Long Distribution.getId() {
        return this.id;
    }
    
    public void Distribution.setId(Long id) {
        this.id = id;
    }
    
    public Integer Distribution.getVersion() {
        return this.version;
    }
    
    public void Distribution.setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void Distribution.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Distribution.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Distribution attached = this.entityManager.find(this.getClass(), this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Distribution.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public Distribution Distribution.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Distribution merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager Distribution.entityManager() {
        EntityManager em = new Distribution().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Distribution.countDistributions() {
        return entityManager().createQuery("select count(o) from com.accoord.domain.Distribution o", Long.class).getSingleResult();
    }
    
    public static List<Distribution> Distribution.findAllDistributions() {
        return entityManager().createQuery("select o from com.accoord.domain.Distribution o", Distribution.class).getResultList();
    }
    
    public static Distribution Distribution.findDistribution(Long id) {
        if (id == null) return null;
        return entityManager().find(Distribution.class, id);
    }
    
    public static List<Distribution> Distribution.findDistributionEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from com.accoord.domain.Distribution o", Distribution.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
