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

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author XieXing
 * @description 管理员实体
 * @create 2019年3月16日 下午5:31:14
 */
@Entity
@Table(name="manager")
public class Manager implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="manager_name",columnDefinition="varchar(255) COMMENT '管理员姓名'")
	private String managerName;//姓名
	
	@Column(name="manager_Number",columnDefinition="varchar(255) COMMENT '管理员编号'")
	private String managerNumber;//编号
	
	@Column(name="manager_password",columnDefinition="varchar(255) COMMENT '管理员密码'")
	private String managerPassword;//密码
	
	@Column(name="manager_sex",columnDefinition="varchar(255) COMMENT '管理员性别'")
	private String managerSex;//性别
	
	@Column(name="manager_nationality",columnDefinition="varchar(255) COMMENT '管理员族别'")
	private String managerNationality ;//族别
	
	@JSONField(format = "yyyy年MM月dd日")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@Column(name="manager_birth",columnDefinition="datetime COMMENT '管理员出生日期'")
	private Date managerBirth;//出生日期
	
	@JSONField(format = "yyyy年MM月dd日")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@Column(name="manager_entrance_date",columnDefinition="datetime COMMENT '管理员入职时间'")
	private Date managerEntranceDate;//入职时间
	
	@Column(name="manager_addr",columnDefinition="varchar(255) COMMENT '管理员家庭地址'")
	private String managerAddr;//家庭地址
	
	@Column(name="manager_id",columnDefinition="varchar(255) COMMENT '管理员身份者号'")
	private String managerId;//身份证号
	
	@Column(name="manager_phone",columnDefinition="varchar(255) COMMENT '管理员电话'")
	private String managerPhone;//管理员电话
	
	@Column(name="manager_email",columnDefinition="varchar(255) COMMENT '管理员邮箱'")
	private String managerEmail;//管理员邮箱
	
	@Column(name="manager_remark",columnDefinition="varchar(255) COMMENT '备注'")
	private String managerRemark;//备注
	
	@Column(name="manager_img",columnDefinition="varchar(255) COMMENT '管理员照片'")
	private String managerImg;//管理员照片

	public Integer getId() {
		return id;
	}

	public String getManagerName() {
		return managerName;
	}

	public String getManagerNumber() {
		return managerNumber;
	}

	public String getManagerPassword() {
		return managerPassword;
	}

	public String getManagerSex() {
		return managerSex;
	}

	public String getManagerId() {
		return managerId;
	}

	public String getManagerRemark() {
		return managerRemark;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public void setManagerNumber(String managerNumber) {
		this.managerNumber = managerNumber;
	}

	public void setManagerPassword(String managerPassword) {
		this.managerPassword = managerPassword;
	}

	public void setManagerSex(String managerSex) {
		this.managerSex = managerSex;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public void setManagerRemark(String managerRemark) {
		this.managerRemark = managerRemark;
	}

	public Date getManagerBirth() {
		return managerBirth;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public String getManagerEmail() {
		return managerEmail;
	}

	public void setManagerBirth(Date managerBirth) {
		this.managerBirth = managerBirth;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}

	public String getManagerAddr() {
		return managerAddr;
	}

	public void setManagerAddr(String managerAddr) {
		this.managerAddr = managerAddr;
	}

	public String getManagerNationality() {
		return managerNationality;
	}

	public void setManagerNationality(String managerNationality) {
		this.managerNationality = managerNationality;
	}

	public Date getManagerEntranceDate() {
		return managerEntranceDate;
	}

	public void setManagerEntranceDate(Date managerEntranceDate) {
		this.managerEntranceDate = managerEntranceDate;
	}

	public String getManagerImg() {
		return managerImg;
	}

	public void setManagerImg(String managerImg) {
		this.managerImg = managerImg;
	}
	
	

}
