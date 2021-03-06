// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.accoord.domain;

import com.accoord.domain.SecurityIncident;
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

privileged aspect SecurityIncident_Roo_Entity {
    
    @PersistenceContext
    transient EntityManager SecurityIncident.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long SecurityIncident.id;
    
    @Version
    @Column(name = "version")
    private Integer SecurityIncident.version;
    
    public Long SecurityIncident.getId() {
        return this.id;
    }
    
    public void SecurityIncident.setId(Long id) {
        this.id = id;
    }
    
    public Integer SecurityIncident.getVersion() {
        return this.version;
    }
    
    public void SecurityIncident.setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void SecurityIncident.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void SecurityIncident.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            SecurityIncident attached = this.entityManager.find(this.getClass(), this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void SecurityIncident.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public SecurityIncident SecurityIncident.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        SecurityIncident merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager SecurityIncident.entityManager() {
        EntityManager em = new SecurityIncident().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long SecurityIncident.countSecurityIncidents() {
        return entityManager().createQuery("select count(o) from com.accoord.domain.SecurityIncident o", Long.class).getSingleResult();
    }
    
    public static List<SecurityIncident> SecurityIncident.findAllSecurityIncidents() {
        return entityManager().createQuery("select o from com.accoord.domain.SecurityIncident o", SecurityIncident.class).getResultList();
    }
    
    public static SecurityIncident SecurityIncident.findSecurityIncident(Long id) {
        if (id == null) return null;
        return entityManager().find(SecurityIncident.class, id);
    }
    
    public static List<SecurityIncident> SecurityIncident.findSecurityIncidentEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from com.accoord.domain.SecurityIncident o", SecurityIncident.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
