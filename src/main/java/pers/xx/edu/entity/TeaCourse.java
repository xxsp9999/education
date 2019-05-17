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
 * @createDate 2019年4月19日 下午2:30:20
 * @description 老师课程实体
 */
@Entity
@Table(name = "tea_course")
public class TeaCourse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="tc_teacher",columnDefinition="int(11) COMMENT '老师'")
	private Teacher tcTeacher;//老师
	
	@ManyToOne
	@JoinColumn(name="tc_course",columnDefinition="int(11) COMMENT '课程'")
	private Course tcCourse;//课程
	
	@Column(name="tc_capacity",columnDefinition="int(11) COMMENT '课程容量'")
	private Integer tcCapacity;//课程容量
	
	@Column(name="tc_allowance",columnDefinition="int(11) COMMENT '课程余量'")
	private Integer tcAllowance;//课程余量
	
	@Column(name="tc_selected",columnDefinition="int(11) COMMENT '已选学生人数'")
	private Integer tcSelected;//已选学生人数
	
	@JSONField(format = "yyyy年MM月dd日")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@Column(name="tc_date",columnDefinition="datetime COMMENT '选课时间'")
	private Date tcDate;//排课时间
	
	@Column(name="tc_season",columnDefinition="varchar(255) COMMENT '排课季节'")
	private String tcSeason;//排课季节：春季，秋季
	
	@Column(name="tc_remark",columnDefinition="varchar(255) COMMENT '备注'")
	private String tcRemark;//备注

	public Teacher getTcTeacher() {
		return tcTeacher;
	}

	public void setTcTeacher(Teacher tcTeacher) {
		this.tcTeacher = tcTeacher;
	}

	public Course getTcCourse() {
		return tcCourse;
	}

	public void setTcCourse(Course tcCourse) {
		this.tcCourse = tcCourse;
	}

	public String getTcRemark() {
		return tcRemark;
	}

	public void setTcRemark(String tcRemark) {
		this.tcRemark = tcRemark;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTcSeason() {
		return tcSeason;
	}

	public void setTcSeason(String tcSeason) {
		this.tcSeason = tcSeason;
	}

	public Date getTcDate() {
		return tcDate;
	}

	public void setTcDate(Date tcDate) {
		this.tcDate = tcDate;
	}

	public Integer getTcCapacity() {
		return tcCapacity;
	}

	public void setTcCapacity(Integer tcCapacity) {
		this.tcCapacity = tcCapacity;
	}

	public Integer getTcAllowance() {
		return tcAllowance;
	}

	public void setTcAllowance(Integer tcAllowance) {
		this.tcAllowance = tcAllowance;
	}

	public Integer getTcSelected() {
		return tcSelected;
	}

	public void setTcSelected(Integer tcSelected) {
		this.tcSelected = tcSelected;
	}

}
