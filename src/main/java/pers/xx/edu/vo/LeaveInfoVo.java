package pers.xx.edu.vo;

/**
 * @author XieXing
 * @createDate 2019年4月21日 下午3:23:20
 * @description 请假信息VO类
 */
public class LeaveInfoVo {

	private Integer id;

	private Integer leaveInfoDays;// 请假天数

	private String leaveInfoType;// 请假类型
	
	private String leaveInfoState;//未提交 审核中 审核通过 审核未通过

	private String leaveInfoReason;// 请假原因

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLeaveInfoDays() {
		return leaveInfoDays;
	}

	public void setLeaveInfoDays(Integer leaveInfoDays) {
		this.leaveInfoDays = leaveInfoDays;
	}

	public String getLeaveInfoType() {
		return leaveInfoType;
	}

	public void setLeaveInfoType(String leaveInfoType) {
		this.leaveInfoType = leaveInfoType;
	}

	public String getLeaveInfoState() {
		return leaveInfoState;
	}

	public void setLeaveInfoState(String leaveInfoState) {
		this.leaveInfoState = leaveInfoState;
	}

	public String getLeaveInfoReason() {
		return leaveInfoReason;
	}

	public void setLeaveInfoReason(String leaveInfoReason) {
		this.leaveInfoReason = leaveInfoReason;
	}
	
	

}
