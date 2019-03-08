package pers.xx.edu.utils;

import java.util.Date;

/**
 * @author xiexing
 * @description 员工导入need类
 * @create 2018-9-7下午10:36:14
 */
public class StaffNeed {
	
	@IsNeeded
	private String id;
	
	@IsNeeded
	private String companyName;
	
	@IsNeeded
	private String staffId;
	
	@IsNeeded
	private String staffName;
	
	@IsNeeded
	private String job;
	
	@IsNeeded
	private String position;
	
	
	@IsNeeded
	private Date startJob;
	
	@IsNeeded
	private Date endJob;

	@IsNeeded
	private String jobStatus;
	
	@IsNeeded
	private String remark;
	
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the staffId
	 */
	public String getStaffId() {
		return staffId;
	}

	/**
	 * @param staffId the staffId to set
	 */
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	/**
	 * @return the staffName
	 */
	public String getStaffName() {
		return staffName;
	}

	/**
	 * @param staffName the staffName to set
	 */
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the job
	 */
	public String getJob() {
		return job;
	}

	/**
	 * @param job the job to set
	 */
	public void setJob(String job) {
		this.job = job;
	}

	/**
	 * @return the jobStatus
	 */
	public String getJobStatus() {
		return jobStatus;
	}

	/**
	 * @param jobStatus the jobStatus to set
	 */
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	/**
	 * @return the startJob
	 */
	public Date getStartJob() {
		return startJob;
	}

	/**
	 * @param startJob the startJob to set
	 */
	public void setStartJob(Date startJob) {
		this.startJob = startJob;
	}

	/**
	 * @return the endJob
	 */
	public Date getEndJob() {
		return endJob;
	}

	/**
	 * @param endJob the endJob to set
	 */
	public void setEndJob(Date endJob) {
		this.endJob = endJob;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StaffNeed [id=" + id + ", companyName=" + companyName
				+ ", staffId=" + staffId + ", staffName=" + staffName + ", job="
				+ job + ", position=" + position + ", startJob=" + startJob
				+ ", endJob=" + endJob + ", jobStatus=" + jobStatus
				+ ", remark=" + remark + "]";
	}

	
}
