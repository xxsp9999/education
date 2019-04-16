package pers.xx.edu.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author XieXing
 * @description 登录统计实体类
 * @create 2018年11月17日 上午9:46:20
 */
@Entity
@Table(name = "loginnum")
public class LoginNum implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	// 登录工号
	@Column(name = "number")
	private String number;
	// 用户姓名
	@Column(name = "name")
	private String name;

	// 身份
	@Column(name = "department")
	private String department;
	// 总计登陆次数
	@Column(name = "total_login_num")
	private Integer totalLoginNum = 0;
	// ip地址
	@Column(name = "ip_address")
	private String ipAddress;
	// 总计登陆时间
	@Column(name = "login_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date loginTime;
    
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Integer getTotalLoginNum() {
		return totalLoginNum;
	}

	public void setTotalLoginNum(Integer totalLoginNum) {
		this.totalLoginNum = totalLoginNum;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

}
