package com.kframe.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
/**
 * 角色表
 * @author fk
 */
@Entity
@Table(name ="role")
public class Role extends BaseSimpleEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1555667221996766418L;

	@Column(name = "name", length = 20)
	private String name;
	
    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
	private List<UserInfo> users;
	
    @ManyToMany(mappedBy = "roles",fetch = FetchType.EAGER)
    private List<Authority> authoritys;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserInfo> getUsers() {
		return users;
	}

	public void setUsers(List<UserInfo> users) {
		this.users = users;
	}

	
	public List<Authority> getAuthoritys() {
		return authoritys;
	}

	public void setAuthoritys(List<Authority> authoritys) {
		this.authoritys = authoritys;
	}

	public Role() {
		// TODO Auto-generated constructor stub
	}
	
}
