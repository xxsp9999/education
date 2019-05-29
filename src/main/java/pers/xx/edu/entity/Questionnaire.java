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
 * @createDate 2019年5月28日 上午10:17:00
 * @description 求职意向实体
 */
@Entity
@Table(name="questionnaire")
public class Questionnaire implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="qn_student",columnDefinition="int(11) COMMENT '学生'")
	private Student qnStudent;//学生
	
	@Column(name="qn_type",columnDefinition="varchar(255) COMMENT '职业类型'")
	private String qnType;//职业类型
	
	@Column(name="qn_content",columnDefinition="varchar(255) COMMENT '具体工作'")
	private String qnContent;//具体工作
	
	@Column(name="qn_address",columnDefinition="varchar(255) COMMENT '工作地点'")
	private String qnAddress;//工作地点

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQnType() {
		return qnType;
	}

	public void setQnType(String qnType) {
		this.qnType = qnType;
	}

	public String getQnContent() {
		return qnContent;
	}

	public void setQnContent(String qnContent) {
		this.qnContent = qnContent;
	}

	public String getQnAddress() {
		return qnAddress;
	}

	public void setQnAddress(String qnAddress) {
		this.qnAddress = qnAddress;
	}

	public Student getQnStudent() {
		return qnStudent;
	}

	public void setQnStudent(Student qnStudent) {
		this.qnStudent = qnStudent;
	}

}
