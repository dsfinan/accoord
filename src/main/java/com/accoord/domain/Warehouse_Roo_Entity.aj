// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.accoord.domain;

import com.accoord.domain.Warehouse;
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

privileged aspect Warehouse_Roo_Entity {
    
    //declare @type: Warehouse: @Entity;
    
    @PersistenceContext
    transient EntityManager Warehouse.entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Warehouse.id;
    
    @Version
    @Column(name = "version")
    private Integer Warehouse.version;
    
    public Long Warehouse.getId() {
        return this.id;
    }
    
    public void Warehouse.setId(Long id) {
        this.id = id;
    }
    
    public Integer Warehouse.getVersion() {
        return this.version;
    }
    
    public void Warehouse.setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void Warehouse.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Warehouse.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Warehouse attached = this.entityManager.find(this.getClass(), this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Warehouse.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public Warehouse Warehouse.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Warehouse merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager Warehouse.entityManager() {
        EntityManager em = new Warehouse().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Warehouse.countWarehouses() {
        return entityManager().createQuery("select count(o) from com.accoord.domain.Warehouse o", Long.class).getSingleResult();
    }
    
    public static List<Warehouse> Warehouse.findAllWarehouses() {
        return entityManager().createQuery("select o from com.accoord.domain.Warehouse o", Warehouse.class).getResultList();
    }
    
    public static Warehouse Warehouse.findWarehouse(Long id) {
        if (id == null) return null;
        return entityManager().find(Warehouse.class, id);
    }
    
    public static List<Warehouse> Warehouse.findWarehouseEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("select o from com.accoord.domain.Warehouse o", Warehouse.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
