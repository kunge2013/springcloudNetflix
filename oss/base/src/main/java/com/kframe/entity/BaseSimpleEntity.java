package com.kframe.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kframe.annotations.Comment;
@Comment("自增类型 基础实体")
@MappedSuperclass
public abstract class BaseSimpleEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1925989785295115138L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
	
	
	@Column(name ="createtime", insertable = true)
	protected long createtime;
	
	@Column(name ="updatetime", updatable  = true)
	protected long updatetime;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCreatetime() {
		return createtime;
	}

	public void setCreatetime(long createtime) {
		this.createtime = createtime;
	}

	public long getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(long updatetime) {
		this.updatetime = updatetime;
	}
	
	
	
}
