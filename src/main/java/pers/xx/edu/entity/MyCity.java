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
 * @createDate 2019年5月4日 下午9:17:16
 * @description 
 */
@Entity
@Table(name="my_city")
public class MyCity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="city_name",columnDefinition="varchar(255) COMMENT '市名'")
	private String cityName;//市名
	
	@ManyToOne
	@JoinColumn(name="pid",columnDefinition="int(11) COMMENT '所属省份'")
	private MyProvince pid;//所属身份id
	
	@Column(name="city_remark",columnDefinition="varchar(255) COMMENT '备注'")
	private String cityRemark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	

	public MyProvince getPid() {
		return pid;
	}

	public void setPid(MyProvince pid) {
		this.pid = pid;
	}

	public String getCityRemark() {
		return cityRemark;
	}

	public void setCityRemark(String cityRemark) {
		this.cityRemark = cityRemark;
	}
	
	

}
