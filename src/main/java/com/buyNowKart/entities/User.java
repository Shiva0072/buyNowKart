package com.buyNowKart.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue
	private long id;
	@Column(length = 100)
	private String name;
	@Column(length = 200)
	private String email;
	@Column(length = 200)
	private String password;
	@Column(length = 2000, name="address")
	private String address;
	@Column(length = 10, name="mobile")
	private String mobile;
	@Column(length = 1500,name = "picture")
	private String picture;
	@Column(name="user_type")
	private String userType;
	
	public User() {}
	
	public User(String name, String email, String password, String address, String mobile, String picture, String userType) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.mobile = mobile;
		this.picture = picture;
		this.userType = userType;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", address="
				+ address + ", mobile=" + mobile + ", picture=" + picture + ", userType=" + userType + "]";
	}

}
