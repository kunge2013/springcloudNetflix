package com.kframe.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import com.kframe.annotations.Comment;
@Comment("UUID 类型基础实体")
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1925989785295115138L;

	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
	
	@Column(name ="createtime", insertable = true)
	private long createtime;
	
	@Column(name ="updatetime", updatable  = true)
	private long updatetime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public BaseEntity(String id, long createtime, long updatetime) {
		super();
		this.id = id;
		this.createtime = createtime;
		this.updatetime = updatetime;
	}

	@Override
	public String toString() {
		return "BaseEntity [id=" + id + ", createtime=" + createtime + ", updatetime=" + updatetime + "]";
	}
	
	
}
