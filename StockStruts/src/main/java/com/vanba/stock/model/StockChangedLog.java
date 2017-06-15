/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vanba.stock.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.struts.action.ActionForm;

/**
 *
 * @author FRAMGIA\nguyen.van.ba
 */
@Entity
@Table(name = "StockChangedLog")
public class StockChangedLog extends ActionForm implements Serializable {

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName = "stocklog_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
    private int id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createddate", insertable = false)
    private Date createdDate;
    private BigDecimal availableChanged;
    private int stockId;
    private int productId;

    
    @PrePersist
    protected void onCreate() {
        if (createdDate == null) {
            createdDate = new Date();
        }
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public BigDecimal getAvailableChanged() {
        return availableChanged;
    }

    public void setAvailableChanged(BigDecimal availableChanged) {
        this.availableChanged = availableChanged;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

}
