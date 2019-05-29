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
 * @createDate 2019年5月17日 下午4:41:40
 * @description 课件信息实体
 */
@Entity
@Table(name="courseware")
public class Courseware implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="cs_explain",columnDefinition="varchar(255) COMMENT '课件说明'")
	private String explain;//课件说明
	
	@ManyToOne
	@JoinColumn(name="cs_tc",columnDefinition="int(11) COMMENT '老师课程id'")
	private TeaCourse csTc;//老师-课程
	
	@Column(name="cs_remark",columnDefinition="varchar(255) COMMENT '备注'")
	private String csremark;//备注

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public TeaCourse getCsTc() {
		return csTc;
	}

	public void setCsTc(TeaCourse csTc) {
		this.csTc = csTc;
	}

	public String getCsremark() {
		return csremark;
	}

	public void setCsremark(String csremark) {
		this.csremark = csremark;
	}



}
