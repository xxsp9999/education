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
 * @createDate 2019年4月12日 下午2:14:33
 * @description 领导职务
 */
@Entity
@Table(name="leader_title")
public class LeaderTitle implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="lea_title_name",columnDefinition="varchar(255) COMMENT '职务名'")
	private String leaTitleName;//职务名
	
	@Column(name="lea_title_number",columnDefinition="varchar(255) COMMENT '职务编号'")
	private String leaTitleNumber;//职务编号
	
	@Column(name="lea_title_remark",columnDefinition="varchar(255) COMMENT '职务备注'")
	private String leaTitleRemark;//职务备注
	
	

	public LeaderTitle() {
		super();
	}

	//如果写了类的构造函数，记得添加其默认的构造函数，否则会出错
	public LeaderTitle(Integer id, String leaTitleName, String leaTitleNumber, String leaTitleRemark) {
		super();
		this.id = id;
		this.leaTitleName = leaTitleName;
		this.leaTitleNumber = leaTitleNumber;
		this.leaTitleRemark = leaTitleRemark;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLeaTitleName() {
		return leaTitleName;
	}

	public void setLeaTitleName(String leaTitleName) {
		this.leaTitleName = leaTitleName;
	}

	public String getLeaTitleNumber() {
		return leaTitleNumber;
	}

	public void setLeaTitleNumber(String leaTitleNumber) {
		this.leaTitleNumber = leaTitleNumber;
	}

	public String getLeaTitleRemark() {
		return leaTitleRemark;
	}

	public void setLeaTitleRemark(String leaTitleRemark) {
		this.leaTitleRemark = leaTitleRemark;
	}
	
	
}
