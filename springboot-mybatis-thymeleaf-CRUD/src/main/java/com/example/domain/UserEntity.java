package com.example.domain;

import java.io.Serializable;

public class UserEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String username;
	private String password;
	private Integer age;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UserEntity() {
		super();
	}
	public UserEntity(String username,String password,Integer age) {
		super();
		this.username = username;
		this.password = password;
		this.age = age;
	}
	
	public String getUsername() {
		return username;
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
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "username:" + username + ";password:" + password + ";age:" +age;
	}
}
