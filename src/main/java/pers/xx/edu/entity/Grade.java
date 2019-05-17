package pers.xx.edu.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author XieXing
 * @createDate 2019年4月19日 下午2:14:04
 * @description 年级表
 */
@Entity
@Table(name="grade")
public class Grade implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="grade_name",columnDefinition="varchar(255) COMMENT '年级名称'")
	private String gradeName;//年级名称
	
	@Column(name="grade_remark",columnDefinition="varchar(255) COMMENT '年级备注'")
	private String gradeRemark;//年级备注

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getGradeRemark() {
		return gradeRemark;
	}

	public void setGradeRemark(String gradeRemark) {
		this.gradeRemark = gradeRemark;
	}

}
