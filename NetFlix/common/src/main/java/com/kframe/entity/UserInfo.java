package com.kframe.entity;

import java.io.Serializable;
import java.util.ArrayList;
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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kframe.annotations.Comment;
/**
 * 用戶信息
 * @author fk
 */
@Entity
@Table(name = "userdetail")
public class UserInfo extends BaseSimpleEntity implements UserDetails, Serializable {
	
	@Comment("未知")
	public static final short SEX_UNKNOW = 0;
	
	@Comment("男")
	public static final short SEX_MAIL = 2;
	
	@Comment("女")
	public static final short SEX_FEMAIL = 4;
	
	//默认密码 123456
    public static final String DEFAULT_PASSWORD = "123456";
	/**
	 * 
	 */
	private static final long serialVersionUID = 6562335913509302463L;

	@Column(name ="username", length = 20)
	public String username = "";

	@JsonIgnore
	@JSONField(serialize = false)
	@Column(name ="password", length = 255)
	public String password = "";

	@Comment("性别  0 未知 2 男 4 女")
	@Column(name ="sex")
	public short sex;//性别
	
	@Comment("出生年月 yyyy-mm-dd")
	@Column(name = "birth", length = 20)
	public String birth;
	
	@Comment("电话号码")
	@Column(name = "mobile", length = 20)
	public String mobile;
	
	@Comment("国家编码")
	@Column(name = "nation", length = 2)
	public int nation;
	
	//急加载 会查询role表
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_ROlE",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")})
    public List<Role> roles;

    @Comment("邮箱")
	@Column(name = "email")
    public String email;
    
    @Comment("用户状态 2 启用 4 禁用 ")
	@Column(name = "status")
    private short status;
    
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

	public UserInfo() {
		// TODO Auto-generated constructor stub
	}
	public UserInfo(String username, String password, String birth, String mobile, int nation,
			List<Role> roles) {
		super();
		this.username = username;
		this.password = password;
		this.birth = birth;
		this.mobile = mobile;
		this.nation = nation;
		this.roles = roles;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        List<Role> roles = this.getRoles();
        if (roles == null ) {
        	roles = new ArrayList<Role>();
        }
        for (Role role : roles) {
            for(Authority aurh:role.getAuthoritys())
            auths.add(new SimpleGrantedAuthority(aurh.getName()));
        }
        return auths;
	}

	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
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

	@Override
	public String toString() {
		return "UserInfo [username=" + username + ", password=" + password + ", sex=" + sex + ", birth=" + birth
				+ ", mobile=" + mobile + ", nation=" + nation + ", roles=" + roles + ", id=" + id + ", createtime="
				+ createtime + ", updatetime=" + updatetime + "]";
	}
	
}
