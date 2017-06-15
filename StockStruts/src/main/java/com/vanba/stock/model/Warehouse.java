/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vanba.stock.model;

import java.io.Serializable;
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
@Table(name = "Stock")
public class Warehouse extends ActionForm implements Serializable {

    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName = "stock_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
    private int id;
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createddate", insertable = false)
    private Date createdDate;
    private String address;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
