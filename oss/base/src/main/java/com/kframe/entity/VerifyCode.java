package com.kframe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.kframe.annotations.Comment;

@Comment("验证码")
@Entity
@Table(name = "verifycode")
public class VerifyCode extends BaseSimpleEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8104584743385543506L;

	public static final long IMAGE_PERIOD = 1000 * 60;

	@Comment("验证码")
	@Column(name ="code")
	private String code;
	
	
	@Comment("过期时间")
	@Column(name ="expiretime")
	private long expiretime;
	
	
	@Comment("base64image内容")
	@Column(name ="base64image")
	private String base64image;


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public long getExpiretime() {
		return expiretime;
	}


	public void setExpiretime(long expiretime) {
		this.expiretime = expiretime;
	}


	public String getBase64image() {
		return base64image;
	}


	public void setBase64image(String base64image) {
		this.base64image = base64image;
	}
	
	public VerifyCode() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 校验码生成
	 * @param code
	 * @param base64image
	 * @return
	 */
	public  static VerifyCode from(String code, String base64image) {
		VerifyCode verifyCode = new VerifyCode();
		verifyCode.setCode(code);
		verifyCode.setBase64image(base64image);
		verifyCode.setExpiretime(IMAGE_PERIOD + System.currentTimeMillis());
		return verifyCode;
	}
}
