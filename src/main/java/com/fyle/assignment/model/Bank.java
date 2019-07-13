package com.fyle.assignment.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.fyle.assignment.model.common.BaseEntity;

import io.swagger.annotations.ApiModelProperty;

@Entity(name = "Bank")
@Table(name = "bank")
@TypeDefs({ @TypeDef(name = "AddressType", typeClass = AddressType.class), })
public class Bank extends BaseEntity {

    @NotBlank(message = "IFSC code is mandatory")
    @Column(name = "ifsc", nullable = false, updatable = false, unique = true)
    @ApiModelProperty(notes = "IFSC code is unique for everyOne.")
    private String ifsc;

    @Column(name = "address", columnDefinition = "jsonb")
    @Type(type = "AddressType")
    @ApiModelProperty(notes = "Address of the Bank which contains state zipcode and others")
    private Address address;

    @Column(name = "bank_id")
    @ApiModelProperty(notes = "Bank ID")
    private Long bankId;
    @Column(name = "branch")
    @ApiModelProperty(notes = "Name of Branch of Particular Bank")
    private String branch;
    @ApiModelProperty(notes = "Name of Bank")
    @Column(name = "bank_name")
    private String bankName;

    public Bank(@NotBlank(message = "IFSC code is mandatory") String ifsc, Address address, Long bankId, String branch,
	    String bankName) {
	super();
	this.ifsc = ifsc;
	this.address = address;
	this.bankId = bankId;
	this.branch = branch;
	this.bankName = bankName;
    }

    public Bank() {
	super();
    }

    public String getIfsc() {
	return ifsc;
    }

    public void setIfsc(String ifsc) {
	this.ifsc = ifsc;
    }

    public Address getAddress() {
	return address;
    }

    public void setAddress(Address address) {
	this.address = address;
    }

    public Long getBankId() {
	return bankId;
    }

    public void setBankId(Long bankId) {
	this.bankId = bankId;
    }

    public String getBranch() {
	return branch;
    }

    public void setBranch(String branch) {
	this.branch = branch;
    }

    public String getBankName() {
	return bankName;
    }

    public void setBankName(String bankName) {
	this.bankName = bankName;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((address == null) ? 0 : address.hashCode());
	result = prime * result + ((bankId == null) ? 0 : bankId.hashCode());
	result = prime * result + ((bankName == null) ? 0 : bankName.hashCode());
	result = prime * result + ((branch == null) ? 0 : branch.hashCode());
	result = prime * result + ((ifsc == null) ? 0 : ifsc.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Bank other = (Bank) obj;
	if (address == null) {
	    if (other.address != null)
		return false;
	} else if (!address.equals(other.address))
	    return false;
	if (bankId == null) {
	    if (other.bankId != null)
		return false;
	} else if (!bankId.equals(other.bankId))
	    return false;
	if (bankName == null) {
	    if (other.bankName != null)
		return false;
	} else if (!bankName.equals(other.bankName))
	    return false;
	if (branch == null) {
	    if (other.branch != null)
		return false;
	} else if (!branch.equals(other.branch))
	    return false;
	if (ifsc == null) {
	    if (other.ifsc != null)
		return false;
	} else if (!ifsc.equals(other.ifsc))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Bank [ifsc=" + ifsc + ", address=" + address + ", bankId=" + bankId + ", branch=" + branch
		+ ", bankName=" + bankName + "]";
    }

}
