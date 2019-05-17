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
 * @createDate 2019年5月11日 下午3:35:43
 * @description 招聘信息实体
 */
@Entity
@Table(name="recruitment")
public class Recruitment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="rc_company",columnDefinition="int(11) COMMENT '招聘公司'")
	private Company rcCompany;//招聘公司 
	
	@Column(name="rc_job",columnDefinition="varchar(255) COMMENT '招聘职位'")
	private String rcJob;//招聘职位
	
	@JSONField(format = "yyyy年MM月dd日")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@Column(name="rc_time",columnDefinition="datetime COMMENT '发布时间'")
	private Date rcTime;//发布时间
	
	@Column(name="rc_content",columnDefinition="mediumtext COMMENT '招聘内容'")
	private String rcContent;//招聘内容
	
	@Column(name="rc_remark",columnDefinition="varchar(255) COMMENT '备注'")
	private String rcRemark;//备注

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Company getRcCompany() {
		return rcCompany;
	}

	public void setRcCompany(Company rcCompany) {
		this.rcCompany = rcCompany;
	}

	public String getRcJob() {
		return rcJob;
	}

	public void setRcJob(String rcJob) {
		this.rcJob = rcJob;
	}

	public String getRcContent() {
		return rcContent;
	}

	public void setRcContent(String rcContent) {
		this.rcContent = rcContent;
	}

	public String getRcRemark() {
		return rcRemark;
	}

	public void setRcRemark(String rcRemark) {
		this.rcRemark = rcRemark;
	}

	public Date getRcTime() {
		return rcTime;
	}

	public void setRcTime(Date rcTime) {
		this.rcTime = rcTime;
	}

	
	
	
}
