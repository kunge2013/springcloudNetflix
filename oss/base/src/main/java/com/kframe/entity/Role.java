package com.kframe.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
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

    @OneToOne(fetch = FetchType.LAZY)  
    @JoinColumn(name="updateuser")  
    public UserInfo updateuser;
    
    
    public UserInfo getUpdateuser() {
		return updateuser;
	}

	public void setUpdateuser(UserInfo updateuser) {
		this.updateuser = updateuser;
	}

	/**
     * 更新用户
     */
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

	@Override
	public String toString() {
		return "Role [name=" + name + ", users=" + users + ", authoritys=" + authoritys + ", id=" + id + ", createtime="
				+ createtime + ", updatetime=" + updatetime + "]";
	}

	
	
}
