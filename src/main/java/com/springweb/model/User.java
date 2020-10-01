package com.springweb.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ESM_USER")
@NamedQuery(
	    name = "User.findAllUsers",
	    query = "SELECT u FROM User u")
public class User {

	public User() {

	}

	@Size(min = 2, max = 10)
	@Id
	@Column(name = "USER_ID")
	private String userId;
	@Size(min = 2, max = 500)
	private String password;
	@Size(min = 10, max = 11)
	@Column(name = "MOBILE_NO")
	private String mobileNo;
	@Column(name = "EMAIL_ID")
	private String emailId;
	private String address1;
	private String address2;
	private String state;
	private String city;
	private String pincode;
	private String country;
	@Transient
	private Map<String, String> rolesList;
	
	@Transient
	private String[] selectedRoles;
	
	@OneToMany
	@JoinTable(name="ESM_USER_ROLE", joinColumns=@JoinColumn(name="USER_ID"),inverseJoinColumns=@JoinColumn(name="ROLE_NAME"))
	Set<Role> roles = new HashSet<Role>();

	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String  getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Map<String, String> getRolesList() {
		return rolesList;
	}

	public void setRolesList(Map<String, String> rolesList) {
		this.rolesList = rolesList;
	}
	public String[] getSelectedRoles() {
		return selectedRoles;
	}
	public void setSelectedRoles(String[] selectedRoles) {
		this.selectedRoles = selectedRoles;
	}

}
