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
 * @description 课程实体
 * @create 2019年3月16日 下午5:15:48
 */
@Entity
@Table(name="course")
public class Course implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="c_no",columnDefinition="varchar(255) COMMENT '课程号'")
	private String cNo;//课程号
	
	@Column(name="c_name",columnDefinition="varchar(255) COMMENT '课程名'")
	private String cName;//课程名
	
	@Column(name="c_English_name",columnDefinition="varchar(255) COMMENT '课程英文名'")
	private String cEnglishName;//课程英文名
	
	@Column(name="c_score",columnDefinition="varchar(255) COMMENT '学分'")
	private String cScore;//学分
	
	@Column(name="c_remark",columnDefinition="varchar(255) COMMENT '备注'")
	private String cRemark;//课程备注

	public Integer getId() {
		return id;
	}

	public String getcNo() {
		return cNo;
	}

	
	public String getcScore() {
		return cScore;
	}

	public String getcRemark() {
		return cRemark;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public void setcScore(String cScore) {
		this.cScore = cScore;
	}

	public void setcRemark(String cRemark) {
		this.cRemark = cRemark;
	}

	public String getcEnglishName() {
		return cEnglishName;
	}

	public void setcEnglishName(String cEnglishName) {
		this.cEnglishName = cEnglishName;
	}
	
	

}
