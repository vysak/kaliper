/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.kaliper.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "voucher_model")
@NamedQueries({
    @NamedQuery(name = "VoucherModel.findAll", query = "SELECT v FROM VoucherModel v"),
    @NamedQuery(name = "VoucherModel.findByModelId", query = "SELECT v FROM VoucherModel v WHERE v.modelId = :modelId"),
    @NamedQuery(name = "VoucherModel.findByModelName", query = "SELECT v FROM VoucherModel v WHERE v.modelName = :modelName"),
    @NamedQuery(name = "VoucherModel.findByModelDesc", query = "SELECT v FROM VoucherModel v WHERE v.modelDesc = :modelDesc"),
    @NamedQuery(name = "VoucherModel.findByCreateTime", query = "SELECT v FROM VoucherModel v WHERE v.createTime = :createTime"),
    @NamedQuery(name = "VoucherModel.findByUpdateTime", query = "SELECT v FROM VoucherModel v WHERE v.updateTime = :updateTime")})
public class VoucherModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "model_id")
    private Integer modelId;
    
    @Basic(optional = false)
    @Column(name = "model_name")
    private String modelName;
    
    @Column(name = "model_desc")
    private String modelDesc;
    
    @Lob
    @Column(name = "model_data")
    private byte[] modelData;
    
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    
    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    
    @OneToMany(mappedBy = "modelId")
    private Collection<Voucher> voucherCollection;

    public VoucherModel() {
    }

    public VoucherModel(Integer modelId) {
        this.modelId = modelId;
    }

    public VoucherModel(Integer modelId, String modelName) {
        this.modelId = modelId;
        this.modelName = modelName;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelDesc() {
        return modelDesc;
    }

    public void setModelDesc(String modelDesc) {
        this.modelDesc = modelDesc;
    }

    public byte[] getModelData() {
        return modelData;
    }

    public void setModelData(byte[] modelData) {
        this.modelData = modelData;
    }
    
    @PrePersist
	protected void onCreate() {
		this.updateTime = this.createTime = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updateTime = new Date();
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

    public Collection<Voucher> getVoucherCollection() {
        return voucherCollection;
    }

    public void setVoucherCollection(Collection<Voucher> voucherCollection) {
        this.voucherCollection = voucherCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (modelId != null ? modelId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VoucherModel)) {
            return false;
        }
        VoucherModel other = (VoucherModel) object;
        if ((this.modelId == null && other.modelId != null) || (this.modelId != null && !this.modelId.equals(other.modelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.company.kaliper.model.VoucherModel[ modelId=" + modelId + " ]";
    }
    
}
