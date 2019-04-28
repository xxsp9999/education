package pers.xx.edu.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author XieXing
 * @description 院系
 * @create 2019年3月16日 下午5:47:38
 */
@Entity
@Table(name="faculty")
public class Faculty implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="fac_number",columnDefinition="varchar(255) COMMENT '院系编号'")
	private String facNumber;//编号
	
	@Column(name="fac_name",columnDefinition="varchar(255) COMMENT '院系名称'")
	private String facName;//院系名称
	
	@JSONField(format = "yyyy年MM月dd日")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@Column(name="fac_add_time",columnDefinition="datetime COMMENT '添加时间'")
	private Date facAddTime;//添加时间
	
	@Column(name="fac_remark",columnDefinition="varchar(255) COMMENT '备注'")
	private String facRemark;//备注

	public Integer getId() {
		return id;
	}

	public String getFacName() {
		return facName;
	}

	public String getFacNumber() {
		return facNumber;
	}

	public String getFacRemark() {
		return facRemark;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setFacName(String facName) {
		this.facName = facName;
	}

	public void setFacNumber(String facNumber) {
		this.facNumber = facNumber;
	}

	public void setFacRemark(String facRemark) {
		this.facRemark = facRemark;
	}

	public Date getFacAddTime() {
		return facAddTime;
	}

	public void setFacAddTime(Date facAddTime) {
		this.facAddTime = facAddTime;
	}

}
