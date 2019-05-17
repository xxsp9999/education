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
 * @createDate 2019年4月19日 下午2:55:46
 * @description 导员年级表
 */
@Entity
@Table(name="ins_grade")
public class InsGrade implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="ig_instructor",columnDefinition="int(11) COMMENT '导员'")
	private Instructor igInstructor;//导员
	
	@ManyToOne
	@JoinColumn(name="ig_grade",columnDefinition="int(11) COMMENT '年级'")
	private Grade igGrade;//年级
	
	@Column(name="ig_reamr",columnDefinition="varchar(255) COMMENT '备注'")
	private String igRemark;//备注

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Instructor getIgInstructor() {
		return igInstructor;
	}

	public void setIgInstructor(Instructor igInstructor) {
		this.igInstructor = igInstructor;
	}

	public Grade getIgGrade() {
		return igGrade;
	}

	public void setIgGrade(Grade igGrade) {
		this.igGrade = igGrade;
	}

	public String getIgRemark() {
		return igRemark;
	}

	public void setIgRemark(String igRemark) {
		this.igRemark = igRemark;
	}

	
	

}
