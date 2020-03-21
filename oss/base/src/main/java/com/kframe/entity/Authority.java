package com.kframe.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.kframe.annotations.Comment;
/**
 * @author fk
 *
 */
@Entity
@Table(name = "Authority")
@Comment("菜单权限配置表")
public class Authority extends BaseSimpleEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7588466807948890930L;

	
	@Column(name = "name", length = 20)
	private String name;
	
	@Column(name = "url", length = 100)
	private String url;
	
	@Column(name = "pid")
	private int pid;
	
	@Column(name = "description", length = 255)
	private String description;
	
	@ManyToMany(fetch = FetchType.LAZY)//懒加载   快速查询 不会查询role表  
    @JoinTable(
            name = "role_auth",
            joinColumns = {@JoinColumn(name = "auth_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
	private List<Role> roles;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	
	public Authority() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Authority [name=" + name + ", url=" + url + ", pid=" + pid + ", description=" + description + ", roles="
				+ roles + ", id=" + id + ", createtime=" + createtime + ", updatetime=" + updatetime + "]";
	}
	
}
