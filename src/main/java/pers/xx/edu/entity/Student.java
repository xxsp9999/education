package pers.xx.edu.entity;

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
 * @description 学生实体
 * @create 2019年3月16日 下午3:42:27
 */
@Entity
@Table(name="student")
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;//主键id
	
	@Column(name="tea_name",columnDefinition="varchar(255) COMMENT '学生名字'")
	private String stuName;//姓名
	
	@Column(name="stu_number",columnDefinition="varchar(255) COMMENT '学号'")
	private String stuNumber;//学号
	
	@Column(name="stu_password",columnDefinition="varchar(255) COMMENT '密码'")
	private String stuPassword;//密码
	
	@Column(name="stu_sex",columnDefinition="varchar(255) COMMENT '性别'")
	private String stuSex;//性别
	
	@Column(name="stu_id",columnDefinition="varchar(255) COMMENT '身份证号码'")
	private String stuId;//身份证号码
	
	@Column(name="stu_phone",columnDefinition="varchar(255) COMMENT '电话'")
	private String stuPhone;//电话
	
	@Column(name="stu_email",columnDefinition="varchar(255) COMMENT '邮箱'")
	private String stuEmail;//邮箱
	
	@JSONField(format = "yyyy年MM月dd日")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@Column(name="stu_entrance_date",columnDefinition="datetime COMMENT '入学时间'")
	private Date stuEntranceDate;//入学时间
	
	@JSONField(format = "yyyy年MM月dd日")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@Column(name="stu_birth",columnDefinition="datetime COMMENT '出生日期'")
	private Date stuBirth;//出生日期
	
	@Column(name="stu_nationality",columnDefinition="varchar(255) COMMENT '民族'")
	private String stuNationality;//民族
	
	@Column(name="stu_addr",columnDefinition="varchar(255) COMMENT '学生家庭地址'")
	private String stuAddr;//学生家庭地址
	
	@Column(name="stu_remark",columnDefinition="varchar(255) COMMENT '备注'")
	private String stuRemark;//备注

	public Integer getId() {
		return id;
	}

	public String getStuName() {
		return stuName;
	}

	public String getStuNumber() {
		return stuNumber;
	}

	public String getStuPassword() {
		return stuPassword;
	}

	public String getStuSex() {
		return stuSex;
	}

	public String getStuId() {
		return stuId;
	}

	public Date getStuEntranceDate() {
		return stuEntranceDate;
	}

	public Date getStuBirth() {
		return stuBirth;
	}

	public String getStuNationality() {
		return stuNationality;
	}

	public String getStuAddr() {
		return stuAddr;
	}

	public String getStuRemark() {
		return stuRemark;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public void setStuNumber(String stuNumber) {
		this.stuNumber = stuNumber;
	}

	public void setStuPassword(String stuPassword) {
		this.stuPassword = stuPassword;
	}

	public void setStuSex(String stuSex) {
		this.stuSex = stuSex;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public void setStuEntranceDate(Date stuEntranceDate) {
		this.stuEntranceDate = stuEntranceDate;
	}

	public void setStuBirth(Date stuBirth) {
		this.stuBirth = stuBirth;
	}

	public void setStuNationality(String stuNationality) {
		this.stuNationality = stuNationality;
	}

	public void setStuAddr(String stuAddr) {
		this.stuAddr = stuAddr;
	}

	public void setStuRemark(String stuRemark) {
		this.stuRemark = stuRemark;
	}

	public String getStuPhone() {
		return stuPhone;
	}

	public String getStuEmail() {
		return stuEmail;
	}

	public void setStuPhone(String stuPhone) {
		this.stuPhone = stuPhone;
	}

	public void setStuEmail(String stuEmail) {
		this.stuEmail = stuEmail;
	}
	

}
