/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.kaliper.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "transaction")
@NamedQueries({
    @NamedQuery(name = "Transaction.findAll", query = "SELECT t FROM Transaction t"),
    @NamedQuery(name = "Transaction.findByTransactionId", query = "SELECT t FROM Transaction t WHERE t.transactionId = :transactionId"),
    @NamedQuery(name = "Transaction.findByTransactionType", query = "SELECT t FROM Transaction t WHERE t.transactionType = :transactionType"),
    @NamedQuery(name = "Transaction.findByTransactionState", query = "SELECT t FROM Transaction t WHERE t.transactionState = :transactionState"),
    @NamedQuery(name = "Transaction.findByTransactionFunnelState", query = "SELECT t FROM Transaction t WHERE t.transactionFunnelState = :transactionFunnelState"),
    @NamedQuery(name = "Transaction.findByTrackId", query = "SELECT t FROM Transaction t WHERE t.trackId = :trackId"),
    @NamedQuery(name = "Transaction.findByCreateTime", query = "SELECT t FROM Transaction t WHERE t.createTime = :createTime"),
    @NamedQuery(name = "Transaction.findByUpdateTime", query = "SELECT t FROM Transaction t WHERE t.updateTime = :updateTime")})
public class Transaction implements Serializable {
	
    private static final long serialVersionUID = 1L;
    @Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "transaction_id")
    private String transactionId;
    
    @Column(name = "transaction_type")
    private Integer transactionType;
    
    @Column(name = "transaction_state")
    private Integer transactionState;
    
    @Column(name = "transaction_funnel_state")
    private Integer transactionFunnelState;
    
    @Column(name = "track_id")
    private String trackId;
    
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    
    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    
    @ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "transaction_params", joinColumns = @JoinColumn(name = "transaction_id"))
	@MapKeyColumn(name = "key")
	@Column(name = "value")
	private Map<String, String> transactionParams = new HashMap<String, String>();
    
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="transaction_voucher")
    @MapKeyJoinColumn(name="transaction_id")
    @Column(name="amount")
    private Map<Voucher, Integer> vouchers;
    
    @JoinColumn(name = "payment_id", referencedColumnName = "payment_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Payment payment;

    public Transaction() {
    }

    public Transaction(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(Integer transactionType) {
        this.transactionType = transactionType;
    }

    public TransactionStatus getTransactionStatus() {
        return TransactionStatus.getEnumFor(transactionState);
    }

    public void setTransactionState(Integer transactionState) {
        this.transactionState = transactionState;
    }
    
    public void setTransactionStatus(TransactionStatus transactionState) {
        this.transactionState = transactionState.getValue();
    }

    public TransactionFunnelStatus getTransactionFunnelStatus() {
        return TransactionFunnelStatus.getEnumFor(transactionFunnelState);
    }

    public void setTransactionFunnelState(Integer transactionFunnelState) {
        this.transactionFunnelState = transactionFunnelState;
    }
    
    public void setTransactionFunnelStatus(TransactionFunnelStatus transactionFunnelState) {
        this.transactionFunnelState = transactionFunnelState.getValue();
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
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
	
    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment paymentId) {
        this.payment = paymentId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transactionId != null ? transactionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transaction)) {
            return false;
        }
        Transaction other = (Transaction) object;
        if ((this.transactionId == null && other.transactionId != null) || (this.transactionId != null && !this.transactionId.equals(other.transactionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.company.kaliper.model.Transaction[ transactionId=" + transactionId + " ]";
    }
    
}
