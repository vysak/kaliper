/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.kaliper.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 *
 * @author vyshakh
 */
@Entity
@Table(name = "payment")
@NamedQueries({
    @NamedQuery(name = "Payment.findAll", query = "SELECT p FROM Payment p"),
    @NamedQuery(name = "Payment.findByPaymentId", query = "SELECT p FROM Payment p WHERE p.paymentId = :paymentId"),
    @NamedQuery(name = "Payment.findByOrderRef", query = "SELECT p FROM Payment p WHERE p.orderRef = :orderRef"),
    @NamedQuery(name = "Payment.findByAmount", query = "SELECT p FROM Payment p WHERE p.amount = :amount"),
    @NamedQuery(name = "Payment.findByBillingAddress", query = "SELECT p FROM Payment p WHERE p.billingAddress = :billingAddress"),
    @NamedQuery(name = "Payment.findByShippingAddress", query = "SELECT p FROM Payment p WHERE p.shippingAddress = :shippingAddress"),
    @NamedQuery(name = "Payment.findByPaymentStatus", query = "SELECT p FROM Payment p WHERE p.paymentStatus = :paymentStatus"),
    @NamedQuery(name = "Payment.findByPaymentFlag", query = "SELECT p FROM Payment p WHERE p.paymentFlag = :paymentFlag"),
    @NamedQuery(name = "Payment.findByClientIp", query = "SELECT p FROM Payment p WHERE p.clientIp = :clientIp"),
    @NamedQuery(name = "Payment.findByClientDetails", query = "SELECT p FROM Payment p WHERE p.clientDetails = :clientDetails"),
    @NamedQuery(name = "Payment.findByFirstName", query = "SELECT p FROM Payment p WHERE p.firstName = :firstName"),
    @NamedQuery(name = "Payment.findByLastName", query = "SELECT p FROM Payment p WHERE p.lastName = :lastName"),
    @NamedQuery(name = "Payment.findByEmaiId", query = "SELECT p FROM Payment p WHERE p.emaiId = :emaiId"),
    @NamedQuery(name = "Payment.findByPhoneNumber", query = "SELECT p FROM Payment p WHERE p.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "Payment.findByCreateTime", query = "SELECT p FROM Payment p WHERE p.createTime = :createTime"),
    @NamedQuery(name = "Payment.findByUpdateTime", query = "SELECT p FROM Payment p WHERE p.updateTime = :updateTime")})
public class Payment implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "payment_id")
    private String paymentId;
    
    @Column(name = "order_ref")
    private String orderRef;
    
    @Column(name = "amount")
    private Integer amount;
    
    @Column(name = "billing_address")
    private String billingAddress;
    
    @Column(name = "shipping_address")
    private String shippingAddress;
    
    @Column(name = "payment_status")
    private Integer paymentStatus;
    
    @Column(name = "payment_flag")
    private Integer paymentFlag;
    
    @Column(name = "client_ip")
    private String clientIp;
    
    @Column(name = "client_details")
    private String clientDetails;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "emai_id")
    private String emaiId;
    
    @Column(name = "phone_number")
    private String phoneNumber;
    
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    
    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    
    @OneToMany(mappedBy = "payment", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Collection<Transaction> transactions;
    
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="payment_voucher")
    @MapKeyJoinColumn(name="payment_id")
    @Column(name="amount")
    private Map<Voucher, Integer> vouchers;
    
    public Payment() {
    }

    public Payment(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getOrderRef() {
        return orderRef;
    }

    public void setOrderRef(String orderRef) {
        this.orderRef = orderRef;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public PaymentStatus getPaymentStatus() {
        return PaymentStatus.getEnumFor(paymentStatus);
    }

    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    
    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus.getValue();
    }

    public Integer getPaymentFlag() {
        return paymentFlag;
    }

    public void setPaymentFlag(Integer paymentFlag) {
        this.paymentFlag = paymentFlag;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getClientDetails() {
        return clientDetails;
    }

    public void setClientDetails(String clientDetails) {
        this.clientDetails = clientDetails;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmaiId() {
        return emaiId;
    }

    public void setEmaiId(String emaiId) {
        this.emaiId = emaiId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public Collection<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Collection<Transaction> transactions) {
        this.transactions = transactions;
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
        hash += (paymentId != null ? paymentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Payment)) {
            return false;
        }
        Payment other = (Payment) object;
        if ((this.paymentId == null && other.paymentId != null) || (this.paymentId != null && !this.paymentId.equals(other.paymentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.company.kaliper.model.Payment[ paymentId=" + paymentId + " ]";
    }
    
}
