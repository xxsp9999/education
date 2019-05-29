package pers.xx.edu.vo;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author XieXing
 * @createDate 2019年5月29日 上午9:57:27
 * @description 学生课程VO类
 */
public class StuCourseVo {
	
	private Integer id;
	
	private String cNo;//课程号
	
	private String cName;//课程名
	
	private String scScore;//分数
	
	@JSONField(format = "yyyy年MM月dd日")
	private Date scDate;//选课时间
	
	private String scState;//状态：通过，未通过,选中，已确认
	
	private String scSeason;//季节：春季，秋季
	
	private String scYear;
	
	private String scTerm;
	
	private String scRemark;//备注

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getcNo() {
		return cNo;
	}

	public void setcNo(String cNo) {
		this.cNo = cNo;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getScScore() {
		return scScore;
	}

	public void setScScore(String scScore) {
		this.scScore = scScore;
	}

	public Date getScDate() {
		return scDate;
	}

	public void setScDate(Date scDate) {
		this.scDate = scDate;
	}

	public String getScState() {
		return scState;
	}

	public void setScState(String scState) {
		this.scState = scState;
	}

	public String getScSeason() {
		return scSeason;
	}

	public void setScSeason(String scSeason) {
		this.scSeason = scSeason;
	}

	public String getScYear() {
		return scYear;
	}

	public void setScYear(String scYear) {
		this.scYear = scYear;
	}

	public String getScTerm() {
		return scTerm;
	}

	public void setScTerm(String scTerm) {
		this.scTerm = scTerm;
	}

	public String getScRemark() {
		return scRemark;
	}

	public void setScRemark(String scRemark) {
		this.scRemark = scRemark;
	}
	
	
	
}
