package com.accoord.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;

import com.accoord.shared.EventType;
import com.accoord.shared.Sector;

@RooJavaBean
@RooToString
@RooEntity
@Entity(name="stock_item")
public class StockItem {
	
	@NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date created;
	
	private String item;
	
	private String unittype;
	
	private Float numberofunits;
	
	private String notes;
	
    @Enumerated
    private Sector sector;
    
    private Float kilos;
    
    private Float cubicmeters;
    
    @ManyToOne
    @JoinColumn(name="warehouse")
    private Warehouse warehouse;
	
}
