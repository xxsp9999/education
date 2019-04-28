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
 * @description 教师职称
 * @create 2019年3月16日 下午5:23:56
 */
@Entity
@Table(name="tea_title")
public class TeaTitle implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="title_name",columnDefinition="varchar(255) COMMENT '职称名'")
	private String titleName;//职称名
	
	@Column(name="title_number",columnDefinition="varchar(255) COMMENT '职称编号'")
	private String titleNumber;//职称编号
	
	@Column(name="title_remark",columnDefinition="varchar(255) COMMENT '职称备注'")
	private String titleRemark;//职称备注

	public Integer getId() {
		return id;
	}

	public String getTitleName() {
		return titleName;
	}

	public String getTitleNumber() {
		return titleNumber;
	}

	public String getTitleRemark() {
		return titleRemark;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public void setTitleNumber(String titleNumber) {
		this.titleNumber = titleNumber;
	}

	public void setTitleRemark(String titleRemark) {
		this.titleRemark = titleRemark;
	}
	
	
	

}
