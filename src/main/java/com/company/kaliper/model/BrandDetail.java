/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.kaliper.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vyshakh
 */
@Entity
@Table(name = "brand_detail")
@NamedQueries({
    @NamedQuery(name = "BrandDetail.findAll", query = "SELECT b FROM BrandDetail b"),
    @NamedQuery(name = "BrandDetail.findByBrandId", query = "SELECT b FROM BrandDetail b WHERE b.brandId = :brandId"),
    @NamedQuery(name = "BrandDetail.findByBrandName", query = "SELECT b FROM BrandDetail b WHERE b.brandName = :brandName"),
    @NamedQuery(name = "BrandDetail.findByBrandDesc", query = "SELECT b FROM BrandDetail b WHERE b.brandDesc = :brandDesc"),
    @NamedQuery(name = "BrandDetail.findByCreateTime", query = "SELECT b FROM BrandDetail b WHERE b.createTime = :createTime"),
    @NamedQuery(name = "BrandDetail.findByUpdateTime", query = "SELECT b FROM BrandDetail b WHERE b.updateTime = :updateTime")})
public class BrandDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "brand_id")
    private Integer brandId;
    
    @Column(name = "brand_name")
    private String brandName;
    
    @Column(name = "brand_desc")
    private String brandDesc;
    
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    
    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    public BrandDetail() {
    }

    public BrandDetail(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandDesc() {
        return brandDesc;
    }

    public void setBrandDesc(String brandDesc) {
        this.brandDesc = brandDesc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    @PrePersist
	protected void onCreate() {
		this.updateTime = this.createTime = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updateTime = new Date();
	}

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (brandId != null ? brandId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BrandDetail)) {
            return false;
        }
        BrandDetail other = (BrandDetail) object;
        if ((this.brandId == null && other.brandId != null) || (this.brandId != null && !this.brandId.equals(other.brandId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.company.kaliper.model.BrandDetail[ brandId=" + brandId + " ]";
    }
    
}
