package pers.xx.edu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author XieXing
 * @createDate 2019年4月12日 下午2:14:33
 * @description 领导职位
 */
@Entity
@Table(name="leader_title")
public class LeaderTitle {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="lea_title_name",columnDefinition="varchar(255) COMMENT '职称名'")
	private String leaTitleName;//职称名
	
	@Column(name="lea_title_number",columnDefinition="varchar(255) COMMENT '职称编号'")
	private String leaTitleNumber;//职称编号
	
	@Column(name="lea_title_remark",columnDefinition="varchar(255) COMMENT '职称备注'")
	private String leaTitleRemark;//职称备注

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
