package pers.xx.edu.vo;

import java.util.Date;

/**
 * @author XieXing
 * @createDate 2019年4月12日 上午9:56:51
 * @description 老师VO类
 */
public class TeacherVo2 {
	
	
	private String teaName;//教师名称
	
	private String teaSex;//教师性别
	
	private String teaId;//身份证号
	
	private String teaNationality;//民族
	
	private String teaPhone;//电话
	
	private String teaEmail;//邮箱
	
	private String teaAddr;//家庭地址
	
	private Date teaBirth;// 出生日期
	
	private Date teaEntranceDate;// 入职时间

	public String getTeaName() {
		return teaName;
	}

	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}

	public String getTeaSex() {
		return teaSex;
	}

	public void setTeaSex(String teaSex) {
		this.teaSex = teaSex;
	}

	public String getTeaId() {
		return teaId;
	}

	public void setTeaId(String teaId) {
		this.teaId = teaId;
	}

	public String getTeaNationality() {
		return teaNationality;
	}

	public void setTeaNationality(String teaNationality) {
		this.teaNationality = teaNationality;
	}

	public String getTeaPhone() {
		return teaPhone;
	}

	public void setTeaPhone(String teaPhone) {
		this.teaPhone = teaPhone;
	}

	public String getTeaEmail() {
		return teaEmail;
	}

	public void setTeaEmail(String teaEmail) {
		this.teaEmail = teaEmail;
	}

	public String getTeaAddr() {
		return teaAddr;
	}

	public void setTeaAddr(String teaAddr) {
		this.teaAddr = teaAddr;
	}

	public Date getTeaBirth() {
		return teaBirth;
	}

	public void setTeaBirth(Date teaBirth) {
		this.teaBirth = teaBirth;
	}

	public Date getTeaEntranceDate() {
		return teaEntranceDate;
	}

	public void setTeaEntranceDate(Date teaEntranceDate) {
		this.teaEntranceDate = teaEntranceDate;
	}

	
	
}
