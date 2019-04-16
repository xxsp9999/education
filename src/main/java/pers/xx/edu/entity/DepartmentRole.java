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
 * @description 角色-部门对应表
 * @author XieXing
 * @create 2018-10-23上午11:40:46
 *
 */
@Entity
@Table(name = "role_department")
public class DepartmentRole implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;//主键

	@ManyToOne
	@JoinColumn(name = "role")
	private Role role;//角色

	@ManyToOne
	@JoinColumn(name = "department")
	private Department department;//部门

	@Column(name = "remark")
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
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

	@Override
	public String toString() {
		return "DepartmentRole [id=" + id + ", role=" + role + ", department="
				+ department + ", remark=" + remark + ", createDate="
				+ createDate + ", createUser=" + createUser + ", updateDate="
				+ updateDate + ", updateUser=" + updateUser + "]";
	}

	public DepartmentRole() {
		super();
	}


}
