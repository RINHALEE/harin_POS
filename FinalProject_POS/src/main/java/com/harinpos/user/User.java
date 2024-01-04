package com.harinpos.user;

import org.springframework.beans.factory.annotation.Autowired;

public class User {
	@Autowired
	private String id;
	@Autowired
	private String password;
	@Autowired
	private String position;
	@Autowired
	private String name;

	public User(String id, String password, String position, String name) {
		this.id = id;
		this.password = password;
		this.position = position;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}