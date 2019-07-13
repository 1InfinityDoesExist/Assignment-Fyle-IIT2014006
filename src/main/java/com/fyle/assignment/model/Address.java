package com.fyle.assignment.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class Address implements Serializable {
    private static final long serialVersionUID = -9108950901351710107L;

    @ApiModelProperty(notes = "Address")
    private String addressLine1;

    @ApiModelProperty(notes = "City")
    private String city;
    @ApiModelProperty(notes = "State")
    private String state;

    @ApiModelProperty(notes = "District")
    private String district;

    public Address() {
	super();
	// TODO Auto-generated constructor stub
    }

    public Address(String addressLine1, String city, String state, String district) {
	super();
	this.addressLine1 = addressLine1;
	this.city = city;
	this.state = state;
	this.district = district;
    }

    public String getAddressLine1() {
	return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
	this.addressLine1 = addressLine1;
    }

    public String getCity() {
	return city;
    }

    public void setCity(String city) {
	this.city = city;
    }

    public String getState() {
	return state;
    }

    public void setState(String state) {
	this.state = state;
    }

    public String getDistrict() {
	return district;
    }

    public void setDistrict(String district) {
	this.district = district;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((addressLine1 == null) ? 0 : addressLine1.hashCode());
	result = prime * result + ((city == null) ? 0 : city.hashCode());
	result = prime * result + ((district == null) ? 0 : district.hashCode());
	result = prime * result + ((state == null) ? 0 : state.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Address other = (Address) obj;
	if (addressLine1 == null) {
	    if (other.addressLine1 != null)
		return false;
	} else if (!addressLine1.equals(other.addressLine1))
	    return false;
	if (city == null) {
	    if (other.city != null)
		return false;
	} else if (!city.equals(other.city))
	    return false;
	if (district == null) {
	    if (other.district != null)
		return false;
	} else if (!district.equals(other.district))
	    return false;
	if (state == null) {
	    if (other.state != null)
		return false;
	} else if (!state.equals(other.state))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Address [addressLine1=" + addressLine1 + ", city=" + city + ", state=" + state + ", district="
		+ district + "]";
    }

}