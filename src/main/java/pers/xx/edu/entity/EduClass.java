package pers.xx.edu.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author XieXing
 * @description 班级实体
 * @create 2019年3月16日 下午5:36:16
 */
@Entity
@Table(name="edu_class")
public class EduClass implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="cla_name",columnDefinition="varchar(255) COMMENT '班级名称'")
	private String claName;//班级名称
	
	@Column(name="cla_number",columnDefinition="varchar(255) COMMENT '班级号'")
	private String claNumber;//班级号
	
	@ManyToOne
	@JoinColumn(name="cla_grade",columnDefinition="int(11) COMMENT '所属年级'")
	private Grade claGrade;//所属年级
	
	@ManyToOne
	@JoinColumn(name="cla_headteacher",columnDefinition="int(11) COMMENT '班主任'")
	private Teacher claHeadteacher;//班主任
	
	@Column(name="cla_remark",columnDefinition="varchar(255) COMMENT '备注'")
	private String claRemark;//备注

	public Integer getId() {
		return id;
	}

	public String getClaNumber() {
		return claNumber;
	}

	
	public String getClaName() {
		return claName;
	}

	public void setClaName(String claName) {
		this.claName = claName;
	}

	public String getClaRemark() {
		return claRemark;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setClaNumber(String claNumber) {
		this.claNumber = claNumber;
	}

	public void setClaRemark(String claRemark) {
		this.claRemark = claRemark;
	}

	public Grade getClaGrade() {
		return claGrade;
	}

	public void setClaGrade(Grade claGrade) {
		this.claGrade = claGrade;
	}

	public Teacher getClaHeadteacher() {
		return claHeadteacher;
	}

	public void setClaHeadteacher(Teacher claHeadteacher) {
		this.claHeadteacher = claHeadteacher;
	}

}
