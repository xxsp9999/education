package pers.xx.edu.vo;

import java.util.Date;

/**
 * @author XieXing
 * @createDate 2019年4月11日 下午3:19:27
 * @description 学生vo类
 */
public class StudentVo2 {
	
	
	private String stuName;//姓名
	
	private String stuSex;//性别
	
	private String stuId;//身份证号码
	
	private String stuPhone;//电话
	
	private String stuEmail;//邮箱
	
	private String stuNationality;//民族
	
	private String stuAddr;//学生家庭地址
	
	private Date stuBirth;

	
	public String getStuName() {
		return stuName;
	}

	
	public Date getStuBirth() {
		return stuBirth;
	}


	public void setStuBirth(Date stuBirth) {
		this.stuBirth = stuBirth;
	}


	public void setStuName(String stuName) {
		this.stuName = stuName;
	}


	public String getStuSex() {
		return stuSex;
	}

	public void setStuSex(String stuSex) {
		this.stuSex = stuSex;
	}

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public String getStuPhone() {
		return stuPhone;
	}

	public void setStuPhone(String stuPhone) {
		this.stuPhone = stuPhone;
	}

	public String getStuEmail() {
		return stuEmail;
	}

	public void setStuEmail(String stuEmail) {
		this.stuEmail = stuEmail;
	}

	public String getStuNationality() {
		return stuNationality;
	}

	public void setStuNationality(String stuNationality) {
		this.stuNationality = stuNationality;
	}

	public String getStuAddr() {
		return stuAddr;
	}

	public void setStuAddr(String stuAddr) {
		this.stuAddr = stuAddr;
	}

	
	
	
	
	
}
