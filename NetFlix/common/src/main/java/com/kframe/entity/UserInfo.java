package com.kframe.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * 用戶信息
 * @author fk
 */
@Entity
@Table(name = "userdetail")
public class UserInfo extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6562335913509302463L;

	private String username = "";

	private String password = "";

	private int userid;
	
	
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
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

	public UserInfo(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
}
