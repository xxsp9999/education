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
 * @createDate 2019年5月4日 下午9:16:26
 * @description 
 */
@Entity
@Table(name="my_province")
public class MyProvince implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="province_name",columnDefinition="varchar(255) COMMENT '省名'")
	private String provinceName;
	
	@Column(name="province_remark",columnDefinition="varchar(255) COMMENT '备注'")
	private String proviceRemark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getProviceRemark() {
		return proviceRemark;
	}

	public void setProviceRemark(String proviceRemark) {
		this.proviceRemark = proviceRemark;
	}
	
	
	
}
