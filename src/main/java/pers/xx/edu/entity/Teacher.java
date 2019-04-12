package pers.xx.edu.entity;

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

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author XieXing
 * @description 教师实体类
 * @create 2019年3月16日 下午5:18:40
 */
@Entity
@Table(name="teacher")
public class Teacher {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="tea_name",columnDefinition="varchar(255) COMMENT '教师名称'")
	private String teaName;//教师名称
	
	@Column(name="tea_number",columnDefinition="varchar(255) COMMENT '教师号'")
	private String teaNumber;//教师号
	
	@Column(name="tea_password",columnDefinition="varchar(255) COMMENT '密码'")
	private String teaPassword;//密码
	
	@ManyToOne
	@JoinColumn(name="tea_title",columnDefinition="int(11) COMMENT '职称'")
	private TeaTitle teaTitle;//职称
	
	@Column(name="tea_sex",columnDefinition="varchar(255) COMMENT '教师性别'")
	private String teaSex;//教师性别
	
	@Column(name="tea_id",columnDefinition="varchar(255) COMMENT '身份证号'")
	private String teaId;//身份证号
	
	@Column(name="tea_nationality",columnDefinition="varchar(255) COMMENT '民族'")
	private String teaNationality;//民族
	
	@Column(name="tea_phone",columnDefinition="varchar(255) COMMENT '电话'")
	private String teaPhone;//电话
	
	@Column(name="tea_email",columnDefinition="varchar(255) COMMENT '邮箱'")
	private String teaEmail;//邮箱
	
	@Column(name="tea_addr",columnDefinition="varchar(255) COMMENT '家庭地址'")
	private String teaAddr;//家庭地址
	
	@JSONField(format = "yyyy年MM月dd日")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@Column(name="tea_birth",columnDefinition="datetime COMMENT '出生日期'")
	private Date teaBirth;//出生日期
	
	@JSONField(format = "yyyy年MM月dd日")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@Column(name="tea_entrance_date",columnDefinition="datetime COMMENT '入职时间'")
	private Date teaEntranceDate;//入职时间
	
	@Column(name="tea_remark",columnDefinition="varchar(255) COMMENT '备注'")
	private String teaRemark;//备注

	public Integer getId() {
		return id;
	}

	public String getTeaName() {
		return teaName;
	}

	public String getTeaNumber() {
		return teaNumber;
	}

	public String getTeaPassword() {
		return teaPassword;
	}

	public String getTeaSex() {
		return teaSex;
	}

	public String getTeaId() {
		return teaId;
	}

	public String getTeaNationality() {
		return teaNationality;
	}

	public String getTeaAddr() {
		return teaAddr;
	}

	public Date getTeaBirth() {
		return teaBirth;
	}

	public Date getTeaEntranceDate() {
		return teaEntranceDate;
	}

	public String getTeaRemark() {
		return teaRemark;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}

	public void setTeaNumber(String teaNumber) {
		this.teaNumber = teaNumber;
	}

	public void setTeaPassword(String teaPassword) {
		this.teaPassword = teaPassword;
	}

	public void setTeaSex(String teaSex) {
		this.teaSex = teaSex;
	}

	public void setTeaId(String teaId) {
		this.teaId = teaId;
	}

	public void setTeaNationality(String teaNationality) {
		this.teaNationality = teaNationality;
	}

	public void setTeaAddr(String teaAddr) {
		this.teaAddr = teaAddr;
	}

	public void setTeaBirth(Date teaBirth) {
		this.teaBirth = teaBirth;
	}

	public void setTeaEntranceDate(Date teaEntranceDate) {
		this.teaEntranceDate = teaEntranceDate;
	}

	public void setTeaRemark(String teaRemark) {
		this.teaRemark = teaRemark;
	}

	public String getTeaPhone() {
		return teaPhone;
	}

	public String getTeaEmail() {
		return teaEmail;
	}

	public void setTeaPhone(String teaPhone) {
		this.teaPhone = teaPhone;
	}

	public void setTeaEmail(String teaEmail) {
		this.teaEmail = teaEmail;
	}

	public TeaTitle getTeaTitle() {
		return teaTitle;
	}

	public void setTeaTitle(TeaTitle teaTitle) {
		this.teaTitle = teaTitle;
	}
	
	

}
