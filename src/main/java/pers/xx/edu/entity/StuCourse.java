package pers.xx.edu.entity;

import java.io.Serializable;
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
 * @createDate 2019年4月19日 下午2:19:02
 * @description 学生课程实体
 */
@Entity
@Table(name="stu_course")
public class StuCourse implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="sc_student",columnDefinition="int(11) COMMENT '学生'")
	private Student scStudent;//学生
	
	@ManyToOne
	@JoinColumn(name="sc_tea_course",columnDefinition="int(11) COMMENT '课程(包含老师信息)'")
	private TeaCourse scTeaCourse;//课程
	
	@Column(name="sc_score",columnDefinition="varchar(255) COMMENT '分数'")
	private String scScore;//分数
	
	@JSONField(format = "yyyy年MM月dd日")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@Column(name="sc_date",columnDefinition="datetime COMMENT '选课时间'")
	private Date scDate;//选课时间
	
	@Column(name="sc_state",columnDefinition="varchar(255) COMMENT '状态'")
	private String scState;//状态：通过，未通过,选中，已确认
	
	@Column(name="sc_season",columnDefinition="varchar(255) COMMENT '学期季节'")
	private String scSeason;//季节：春季，秋季
	
	@Column(name="sc_year",columnDefinition="varchar(255) COMMENT '学年'")
	private String scYear;
	
	@Column(name="sc_term",columnDefinition="varchar(255) COMMENT '学期'")
	private String scTerm;
	
	@Column(name="sc_remark",columnDefinition="varchar(255) COMMENT '备注'")
	private String scRemark;//备注

	public Student getScStudent() {
		return scStudent;
	}

	public void setScStudent(Student scStudent) {
		this.scStudent = scStudent;
	}

	public TeaCourse getScTeaCourse() {
		return scTeaCourse;
	}

	public void setScTeaCourse(TeaCourse scTeaCourse) {
		this.scTeaCourse = scTeaCourse;
	}

	public String getScScore() {
		return scScore;
	}

	public void setScScore(String scScore) {
		this.scScore = scScore;
	}

	public String getScState() {
		return scState;
	}

	public void setScState(String scState) {
		this.scState = scState;
	}

	public String getScRemark() {
		return scRemark;
	}

	public void setScRemark(String scRemark) {
		this.scRemark = scRemark;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getScTerm() {
		return scTerm;
	}

	public void setScTerm(String scTerm) {
		this.scTerm = scTerm;
	}

	public Date getScDate() {
		return scDate;
	}

	public void setScDate(Date scDate) {
		this.scDate = scDate;
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
	
	
}
