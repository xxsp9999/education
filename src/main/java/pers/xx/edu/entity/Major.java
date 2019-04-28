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
 * @description 专业实体
 * @create 2019年3月16日 下午5:38:03
 */
@Entity
@Table(name="major")
public class Major implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="major_name",columnDefinition="varchar(255) COMMENT '专业名称'")
	private String majorName;//名称
	
	@Column(name="major_number",columnDefinition="varchar(255) COMMENT '专业编号'")
	private String majorNumber;//编号
	
	@ManyToOne
	@JoinColumn(name="major_of_faculty",columnDefinition="int(11) COMMENT '所属院系'")
	private Faculty majorOfFaculty;//所属院系
	
	@JSONField(format = "yyyy年MM月dd日")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@Column(name="maj_add_time",columnDefinition="datetime COMMENT '添加时间'")
	private Date majAddTime;//添加时间
	
	@Column(name="major_remark",columnDefinition="varchar(255) COMMENT '备注'")
	private String marjorRemark;//备注

	public Integer getId() {
		return id;
	}

	public String getMajorName() {
		return majorName;
	}

	public String getMajorNumber() {
		return majorNumber;
	}

	public Faculty getMajorOfFaculty() {
		return majorOfFaculty;
	}

	public String getMarjorRemark() {
		return marjorRemark;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public void setMajorNumber(String majorNumber) {
		this.majorNumber = majorNumber;
	}

	public void setMajorOfFaculty(Faculty majorOfFaculty) {
		this.majorOfFaculty = majorOfFaculty;
	}

	public void setMarjorRemark(String marjorRemark) {
		this.marjorRemark = marjorRemark;
	}

	public Date getMajAddTime() {
		return majAddTime;
	}


	public void setMajAddTime(Date majAddTime) {
		this.majAddTime = majAddTime;
	}
	
	

}
