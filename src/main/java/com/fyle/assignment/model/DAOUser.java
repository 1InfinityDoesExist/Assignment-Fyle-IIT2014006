package com.fyle.assignment.model;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fyle.assignment.util.StringEncryptDecryptConverter;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "dao_user")
public class DAOUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "UserName Can't Be Blank")
    @Column(name = "user_name", unique = true, nullable = false, updatable = true)
    private String username;
    @NotBlank(message = "Password Field Cannot Be Blank")
    @Column(name = "password", nullable = false, updatable = true)
    @JsonIgnore
    private String password;
    @Column(name = "email", unique = true, updatable = true, nullable = false)
    @ApiModelProperty(notes = "Email of the user")
    @Convert(converter = StringEncryptDecryptConverter.class)
    private String email;
    @Column(name = "mobile_phone", nullable = false, updatable = true, unique = true)
    @ApiModelProperty(notes = "Mobile phone number of the user")
    @Convert(converter = StringEncryptDecryptConverter.class)
    private String mobilePhone;

    public String getUsername() {
	return username;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getMobilePhone() {
	return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
	this.mobilePhone = mobilePhone;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

}