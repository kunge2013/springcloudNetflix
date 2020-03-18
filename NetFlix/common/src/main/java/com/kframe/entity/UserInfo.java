package com.kframe.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.kframe.annotations.Comment;
/**
 * 用戶信息
 * @author fk
 */
@Entity
@Table(name = "userdetail")
public class UserInfo extends BaseSimpleEntity implements UserDetails, Serializable {
	
	@Comment("男")
	public static final short SEX_MAIL = 2;
	
	@Comment("女")
	public static final short SEX_FEMAIL = 4;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6562335913509302463L;

	@Column(name ="username", length = 20)
	private String username = "";

	@Column(name ="password", length = 255)
	private String password = "";

	@Comment("性别  0 未知 2 男 4 女")
	@Column(name ="sex")
	private short sex;//性别
	
	@Comment("出生年月 yyyy-mm-dd")
	@Column(name = "birth", length = 20)
	private String birth;
	
	@Comment("电话号码")
	@Column(name = "mobile", length = 20)
	private String mobile;
	
	@Comment("国家编码")
	@Column(name = "nation", length = 2)
	private int nation;
	
	//急加载 会查询role表
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_ROlE",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")})
    private List<Role> roles;

    
	public short getSex() {
		return sex;
	}

	public void setSex(short sex) {
		this.sex = sex;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
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

	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getNation() {
		return nation;
	}

	public void setNation(int nation) {
		this.nation = nation;
	}
	
}
