package pers.xx.edu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author XieXing
 * @description 班级实体
 * @create 2019年3月16日 下午5:36:16
 */
@Entity
@Table(name="edu_class")
public class EduClass {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="cla_name",columnDefinition="varchar(255) COMMENT '班级名称'")
	private String claName;//班级名称
	
	@Column(name="cla_number",columnDefinition="varchar(255) COMMENT '班级号'")
	private String claNumber;//班级号
	
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
	
	

}
