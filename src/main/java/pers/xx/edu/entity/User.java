package pers.xx.edu.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;

/**
 *
 * @description 用户表
 * @author 白贵才
 * @create 2017-9-14下午12:03:06
 *
 */
@Entity
@Table(name = "user")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;//主键

	@Column(name = "number")
	private String number;//工号

	@Column(name = "staffJob")//工作职位
	private String staffJob;

	@JSONField(serialize=false)
	@Column(name = "password")
	private String password;//密码

	@Column(name = "name")
	private String name;//姓名

	@JSONField(serialize=false)
	@Column(name = "phone")
	private String phone;//电话

	@JSONField(serialize=false)
	@ManyToOne
	@JoinColumn(name = "department")
	private Department department;//部门

	@JSONField(serialize=false)
	@Column(name = "state")
	private int state = 1;//账号状态(1：正常;0:停用)

	@Column(name = "month_login_num")
	private Integer monthLoginNum=0;//本月登录次数

	@Column(name = "total_login_num")
	private Integer totalLoginNum=0;//总计登陆次数


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public String getStaffJob() {
		return staffJob;
	}

	public void setStaffJob(String staffJob) {
		this.staffJob = staffJob;
	}



	public void setNumber(String number) {
		this.number = number;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}


	public Integer getMonthLoginNum() {
		return monthLoginNum;
	}

	public void setMonthLoginNum(Integer monthLoginNum) {
		this.monthLoginNum = monthLoginNum;
	}

	public Integer getTotalLoginNum() {
		return totalLoginNum;
	}

	public void setTotalLoginNum(Integer totalLoginNum) {
		this.totalLoginNum = totalLoginNum;
	}


	
}
