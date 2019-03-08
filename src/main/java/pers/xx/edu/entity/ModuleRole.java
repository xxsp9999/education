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
 * @description 模块-角色对应
 * @author 白贵才
 * @create 2017-10-23上午11:36:11
 *
 */
@Entity
@Table(name = "module_role")
public class ModuleRole implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;//主键

	@ManyToOne
	@JoinColumn(name = "module")
	private Module module;//模块

	@ManyToOne
	@JoinColumn(name = "role")
	private Role role;//角色

	@Column
	private String remark;//备注

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "create_date")
	private Date createDate;//创建日期

	@ManyToOne
	@JoinColumn(name = "create_user")
	private User createUser;//创建人

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "update_date")
	private Date updateDate;//修改日期

	@ManyToOne
	@JoinColumn(name = "update_user")
	private User updateUser;//修改人

	@Column(name = "state")
	private int state = 1;//开启关闭状态(1:开启，0：关闭)

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "ModuleRole [id=" + id + ", module=" + module + ", role=" + role
				+ ", remark=" + remark + ", createDate=" + createDate
				+ ", createUser=" + createUser + ", updateDate=" + updateDate
				+ ", updateUser=" + updateUser + ", state=" + state + "]";
	}

	public ModuleRole() {
		super();
	}

}
