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
 * @description 辅导员实体
 * @create 2019年3月16日 下午5:26:57
 */
@Entity
@Table(name="instructor")
public class Instructor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="instructor_name",columnDefinition="varchar(255) COMMENT '导员姓名'")
	private String instructorName;//名字
	
	@Column(name="instructor_number",columnDefinition="varchar(255) COMMENT '导员编号'")
	private String instructorNumber;//编号
	
	@Column(name="instructor_sex",columnDefinition="varchar(255) COMMENT '导员性别'")
	private String instructorSex;//性别
	
	@Column(name="instructor_nationality",columnDefinition="varchar(255) COMMENT '导员族别'")
	private String instructorNationality;//民族
	
	@JSONField(format = "yyyy年MM月dd日")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@Column(name="instructor_birth",columnDefinition="datetime COMMENT '导员出生日期'")
	private Date instructorBirth;//出生日期
	
	@JSONField(format = "yyyy年MM月dd日")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@Column(name="instructor_entrance_date",columnDefinition="datetime COMMENT '导员入职时间'")
	private Date instructorEntranceDate;//入职时间
	
	@Column(name="instructor_addr",columnDefinition="varchar(255) COMMENT '导员家庭地址'")
	private String instructorAddr;//导员家庭地址
	
	@Column(name="instructor_phone",columnDefinition="varchar(255) COMMENT '导员电话'")
	private String instructorPhone;//联系方式
	
	@Column(name="instructor_email",columnDefinition="varchar(255) COMMENT '导员邮箱'")
	private String instructorEmail;//邮箱
	
	@Column(name="instructor_remark",columnDefinition="varchar(255) COMMENT '备注'")
	private String instructorRemark;//备注

	public Integer getId() {
		return id;
	}

	public String getInstructorName() {
		return instructorName;
	}

	public String getInstructorNumber() {
		return instructorNumber;
	}

	public String getInstructorSex() {
		return instructorSex;
	}

	public Date getInstructorBirth() {
		return instructorBirth;
	}

	public String getInstructorPhone() {
		return instructorPhone;
	}

	public String getInstructorEmail() {
		return instructorEmail;
	}

	public String getInstructorRemark() {
		return instructorRemark;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	public void setInstructorNumber(String instructorNumber) {
		this.instructorNumber = instructorNumber;
	}

	public void setInstructorSex(String instructorSex) {
		this.instructorSex = instructorSex;
	}

	public void setInstructorBirth(Date instructorBirth) {
		this.instructorBirth = instructorBirth;
	}

	public void setInstructorPhone(String instructorPhone) {
		this.instructorPhone = instructorPhone;
	}

	public void setInstructorEmail(String instructorEmail) {
		this.instructorEmail = instructorEmail;
	}

	public void setInstructorRemark(String instructorRemark) {
		this.instructorRemark = instructorRemark;
	}

	public String getInstructorAddr() {
		return instructorAddr;
	}

	public void setInstructorAddr(String instructorAddr) {
		this.instructorAddr = instructorAddr;
	}

	public String getInstructorNationality() {
		return instructorNationality;
	}

	public void setInstructorNationality(String instructorNationality) {
		this.instructorNationality = instructorNationality;
	}

	public Date getInstructorEntranceDate() {
		return instructorEntranceDate;
	}

	public void setInstructorEntranceDate(Date instructorEntranceDate) {
		this.instructorEntranceDate = instructorEntranceDate;
	}

	
	
	

}
