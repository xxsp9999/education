package pers.xx.edu.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author XieXing
 * @createDate 2019年5月12日 下午5:34:35
 * @description 招聘VO类
 */
public class RecruitmentVo {
	private Integer id;
	
	private String companyName;//招聘公司 
	
	private String rcJob;//招聘职位
	
	@JSONField(format = "yyyy年MM月dd日")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date rcTime;//发布时间
	
	private String rcRemark;//备注

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getRcJob() {
		return rcJob;
	}

	public void setRcJob(String rcJob) {
		this.rcJob = rcJob;
	}

	public Date getRcTime() {
		return rcTime;
	}

	public void setRcTime(Date rcTime) {
		this.rcTime = rcTime;
	}

	public String getRcRemark() {
		return rcRemark;
	}

	public void setRcRemark(String rcRemark) {
		this.rcRemark = rcRemark;
	}
	
	
}
