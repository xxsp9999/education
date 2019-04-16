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
 * @description 角色表
 * @author XieXing
 * @create 2018-10-19下午8:06:38
 *
 */
@Entity
@Table(name = "role")
public class Role implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;//主键

	@Column(name = "name")
	private String name;//角色名称

	@Column(name = "code")
	private String code;//角色代码

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

	@Column(name = "remark")
	private String remark;//备注

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", code=" + code
				+ ", createDate=" + createDate + ", createUser=" + createUser
				+ ", updateDate=" + updateDate + ", updateUser=" + updateUser
				+ ", remark=" + remark + "]";
	}

}
