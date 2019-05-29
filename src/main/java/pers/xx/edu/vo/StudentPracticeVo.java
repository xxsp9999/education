package pers.xx.edu.vo;

/**
 * @author XieXing
 * @createDate 2019年5月25日 上午11:05:17
 * @description 学生实践VO类
 */
public class StudentPracticeVo{

	private Integer id;

	private String itemName;// 实践事项

	private String prizeSituation;// 获奖情况

	private String certificatePath;// 实践证书

	private String practiceScore;// 相关评分

	private String checkState;// 审核状态

	private String practiceRemark;// 备注

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getPrizeSituation() {
		return prizeSituation;
	}

	public void setPrizeSituation(String prizeSituation) {
		this.prizeSituation = prizeSituation;
	}

	public String getCertificatePath() {
		return certificatePath;
	}

	public void setCertificatePath(String certificatePath) {
		this.certificatePath = certificatePath;
	}

	public String getPracticeScore() {
		return practiceScore;
	}

	public void setPracticeScore(String practiceScore) {
		this.practiceScore = practiceScore;
	}

	public String getCheckState() {
		return checkState;
	}

	public void setCheckState(String checkState) {
		this.checkState = checkState;
	}

	public String getPracticeRemark() {
		return practiceRemark;
	}

	public void setPracticeRemark(String practiceRemark) {
		this.practiceRemark = practiceRemark;
	}

}
