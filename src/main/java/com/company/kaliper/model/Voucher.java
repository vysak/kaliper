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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author vyshakh
 */
@Entity
@Table(name = "voucher")
@NamedQueries({
    @NamedQuery(name = "Voucher.findAll", query = "SELECT v FROM Voucher v"),
    @NamedQuery(name = "Voucher.findByVoucherId", query = "SELECT v FROM Voucher v WHERE v.voucherId = :voucherId"),
    @NamedQuery(name = "Voucher.findByVoucherCode", query = "SELECT v FROM Voucher v WHERE v.voucherCode = :voucherCode"),
    @NamedQuery(name = "Voucher.findByValidFrom", query = "SELECT v FROM Voucher v WHERE v.validFrom = :validFrom"),
    @NamedQuery(name = "Voucher.findByExpiryDate", query = "SELECT v FROM Voucher v WHERE v.expiryDate = :expiryDate"),
    @NamedQuery(name = "Voucher.findByRevoked", query = "SELECT v FROM Voucher v WHERE v.revoked = :revoked"),
    @NamedQuery(name = "Voucher.findByRevokedBy", query = "SELECT v FROM Voucher v WHERE v.revokedBy = :revokedBy"),
    @NamedQuery(name = "Voucher.findByInitialCredit", query = "SELECT v FROM Voucher v WHERE v.initialCredit = :initialCredit"),
    @NamedQuery(name = "Voucher.findByAvailableCredit", query = "SELECT v FROM Voucher v WHERE v.availableCredit = :availableCredit"),
    @NamedQuery(name = "Voucher.findByCreatedBy", query = "SELECT v FROM Voucher v WHERE v.createdBy = :createdBy"),
    @NamedQuery(name = "Voucher.findByCategory", query = "SELECT v FROM Voucher v WHERE v.category = :category"),
    @NamedQuery(name = "Voucher.findByStatus", query = "SELECT v FROM Voucher v WHERE v.status = :status"),
    @NamedQuery(name = "Voucher.findByCreateTime", query = "SELECT v FROM Voucher v WHERE v.createTime = :createTime"),
    @NamedQuery(name = "Voucher.findByUpdateTime", query = "SELECT v FROM Voucher v WHERE v.updateTime = :updateTime")})
public class Voucher implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "voucher_id" , unique=true)
    private String voucherId;
    
    @Basic(optional = false)
    @Column(name = "voucher_code" , unique=true)
    private String voucherCode;
    
    @Column(name = "valid_from")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validFrom;
    
    @Column(name = "expiry_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;
    
    @Column(name = "order_id")
    private String orderId;
    
    @Column(name = "revoked_by")
    private String revokedBy;
    
    @Column(name = "initial_credit")
    private Integer initialCredit;
    
    @Column(name = "available_credit")
    private Integer availableCredit;
    
    @Column(name = "created_by")
    private String createdBy;
    
    @Basic(optional = false)
    @Column(name = "category")
    private int category;
    
    @Basic(optional = false)
    @Column(name = "status")
    private int status;
    
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    
    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    
    @JoinColumn(name = "model_id", referencedColumnName = "model_id")
    @ManyToOne
    private VoucherModel modelId;

    public Voucher() {
    }

    public Voucher(String voucherId) {
        this.voucherId = voucherId;
    }

    public Voucher(String voucherId, String voucherCode, int category, int status) {
        this.voucherId = voucherId;
        this.voucherCode = voucherCode;
        this.category = category;
        this.status = status;
    }

    public String getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getRevokedBy() {
        return revokedBy;
    }

    public void setRevokedBy(String revokedBy) {
        this.revokedBy = revokedBy;
    }

    public Integer getInitialCredit() {
        return initialCredit;
    }

    public void setInitialCredit(Integer initialCredit) {
        this.initialCredit = initialCredit;
    }

    public Integer getAvailableCredit() {
        return availableCredit;
    }

    public void setAvailableCredit(Integer availableCredit) {
        this.availableCredit = availableCredit;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public VoucherStatus getStatus() {
        return VoucherStatus.getEnumFor(status);
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public void setStatus(VoucherStatus status) {
        this.status = status.getValue();
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

    public VoucherModel getModelId() {
        return modelId;
    }

    public void setModelId(VoucherModel modelId) {
        this.modelId = modelId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (voucherId != null ? voucherId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Voucher)) {
            return false;
        }
        Voucher other = (Voucher) object;
        if ((this.voucherId == null && other.voucherId != null) || (this.voucherId != null && !this.voucherId.equals(other.voucherId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.company.kaliper.model.Voucher[ voucherId=" + voucherId + " ]";
    }
    
}
