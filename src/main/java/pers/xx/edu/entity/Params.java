package pers.xx.edu.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @description 参数模块
 * @author 白贵才
 * @create 2017-9-14下午2:50:23
 *
 */
@Entity
@Table(name = "params")
public class Params implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;//主键

	@Column(name = "name")
	private String name;//名称

	@Column(name = "desciption")
	private String description;//描述

	@Column(name = "params")
	private String params;//参数

	@Column(name = "code")
	private String code;//代码

	@Column(name = "class_type")
	private String classType;//数据类型

	@Column(name = "deleteable")
	private int deleteable = 1;//可删除(0:不可以;1:可以)

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "create_date")
	private Date createDate;//创建时间

	@ManyToOne
	@JoinColumn(name = "create_user")
	private User createUser;//创建人

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "update_date")
	private Date updateDate;//更新时间

	@ManyToOne
	@JoinColumn(name = "update_user")
	private User updateUser;//更新人

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public int getDeleteable() {
		return deleteable;
	}

	public void setDeleteable(int deleteable) {
		this.deleteable = deleteable;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public User getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}

	public Params() {
		super();
	}

	@Override
	public String toString() {
		return "Params [id=" + id + ", name=" + name + ", description="
				+ description + ", params=" + params + ", code=" + code
				+ ", classType=" + classType + ", deleteable=" + deleteable
				+ ", createDate=" + createDate + ", createUser=" + createUser
				+ ", updateDate=" + updateDate + ", updateUser=" + updateUser
				+ "]";
	}

}
