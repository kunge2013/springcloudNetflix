package com.kframe.eurekaclient;

public class User {
	private int userid;
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(int userid, String name) {
		super();
		this.userid = userid;
		this.name = name;
	}
	private String name;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
