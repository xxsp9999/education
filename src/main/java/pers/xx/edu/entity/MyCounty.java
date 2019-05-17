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
 * @createDate 2019年5月4日 下午9:18:21
 * @description 县实体
 */
@Entity
@Table(name="my_county")
public class MyCounty implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="county_name",columnDefinition="varchar(255) COMMENT '三级地名'")
	private String countyName;//县名（三级地名）
	
	@ManyToOne
	@JoinColumn(name="city_id",columnDefinition="int(11) COMMENT '所属市'")
	private MyCity cityId;//所属身份id
	
	@ManyToOne
	@JoinColumn(name="pid",columnDefinition="int(11) COMMENT '所属省份'")
	private MyProvince pid;//所属省份id
	
	@Column(name="latitude",columnDefinition="Double(16,2) COMMENT '纬度'")
	private Double latitude;
	
	@Column(name="longitude",columnDefinition="Double(16,2) COMMENT '经度'")
	private Double longitude;
	
	@Column(name="county_remark",columnDefinition="varchar(255) COMMENT '备注'")
	private String countyRemark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public MyCity getCityId() {
		return cityId;
	}

	public void setCityId(MyCity cityId) {
		this.cityId = cityId;
	}

	public MyProvince getPid() {
		return pid;
	}

	public void setPid(MyProvince pid) {
		this.pid = pid;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public String getCountyRemark() {
		return countyRemark;
	}

	public void setCountyRemark(String countyRemark) {
		this.countyRemark = countyRemark;
	}
	

}
