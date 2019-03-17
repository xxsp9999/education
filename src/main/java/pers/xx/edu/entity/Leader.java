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
 * @description 领导实体
 * @create 2019年3月16日 下午5:31:14
 */
@Entity
@Table(name="leader")
public class Leader {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="leader_name",columnDefinition="varchar(255) COMMENT '领导姓名'")
	private String leaderName;//姓名
	
	@Column(name="leader_Number",columnDefinition="varchar(255) COMMENT '领导编号'")
	private String leaderNumber;//编号
	
	@Column(name="leader_password",columnDefinition="varchar(255) COMMENT '领导密码'")
	private String leaderPassword;//密码
	
	@Column(name="leader_position",columnDefinition="varchar(255) COMMENT '领导职位'")
	private String leaderPosition;//密码
	
	@Column(name="leader_sex",columnDefinition="varchar(255) COMMENT '领导性别'")
	private String leaderSex;//性别
	
	@Column(name="leader_nationality",columnDefinition="varchar(255) COMMENT '领导族别'")
	private String leaderNationality ;//族别
	
	@JSONField(format = "yyyy年MM月dd日")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@Column(name="leader_birth",columnDefinition="datetime COMMENT '领导出生日期'")
	private Date leaderBirth;//出生日期
	
	@JSONField(format = "yyyy年MM月dd日")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@Column(name="leader_entrance_date",columnDefinition="datetime COMMENT '领导入职时间'")
	private Date leaderEntranceDate;//入职时间
	
	@Column(name="leader_addr",columnDefinition="varchar(255) COMMENT '领导家庭地址'")
	private String leaderAddr;//家庭地址
	
	@Column(name="leader_id",columnDefinition="varchar(255) COMMENT '领导身份者号'")
	private String leaderId;//身份证号
	
	@Column(name="leader_phone",columnDefinition="varchar(255) COMMENT '领导电话'")
	private String leaderPhone;//领导电话
	
	@Column(name="leader_email",columnDefinition="varchar(255) COMMENT '领导邮箱'")
	private String leaderEmail;//领导邮箱
	
	@Column(name="leader_remark",columnDefinition="varchar(255) COMMENT '备注'")
	private String leaderRemark;//备注

	public Integer getId() {
		return id;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public String getLeaderNumber() {
		return leaderNumber;
	}

	public String getLeaderPassword() {
		return leaderPassword;
	}

	public String getLeaderPosition() {
		return leaderPosition;
	}

	public String getLeaderSex() {
		return leaderSex;
	}

	public String getLeaderNationality() {
		return leaderNationality;
	}

	public Date getLeaderBirth() {
		return leaderBirth;
	}

	public Date getLeaderEntranceDate() {
		return leaderEntranceDate;
	}

	public String getLeaderAddr() {
		return leaderAddr;
	}

	public String getLeaderId() {
		return leaderId;
	}

	public String getLeaderPhone() {
		return leaderPhone;
	}

	public String getLeaderEmail() {
		return leaderEmail;
	}

	public String getLeaderRemark() {
		return leaderRemark;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public void setLeaderNumber(String leaderNumber) {
		this.leaderNumber = leaderNumber;
	}

	public void setLeaderPassword(String leaderPassword) {
		this.leaderPassword = leaderPassword;
	}

	public void setLeaderPosition(String leaderPosition) {
		this.leaderPosition = leaderPosition;
	}

	public void setLeaderSex(String leaderSex) {
		this.leaderSex = leaderSex;
	}

	public void setLeaderNationality(String leaderNationality) {
		this.leaderNationality = leaderNationality;
	}

	public void setLeaderBirth(Date leaderBirth) {
		this.leaderBirth = leaderBirth;
	}

	public void setLeaderEntranceDate(Date leaderEntranceDate) {
		this.leaderEntranceDate = leaderEntranceDate;
	}

	public void setLeaderAddr(String leaderAddr) {
		this.leaderAddr = leaderAddr;
	}

	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}

	public void setLeaderPhone(String leaderPhone) {
		this.leaderPhone = leaderPhone;
	}

	public void setLeaderEmail(String leaderEmail) {
		this.leaderEmail = leaderEmail;
	}

	public void setLeaderRemark(String leaderRemark) {
		this.leaderRemark = leaderRemark;
	}

}
