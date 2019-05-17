package pers.xx.edu.vo;

/**
 * @author XieXing
 * @createDate 2019年5月2日 上午11:26:35
 * @description 班级VO类
 */
public class EduClassVo {
	
	private Integer id;
	
	private String claName;//班级名称
	
	private String claNumber;//班级号
	
	private String claRemark;//备注

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClaName() {
		return claName;
	}

	public void setClaName(String claName) {
		this.claName = claName;
	}

	public String getClaNumber() {
		return claNumber;
	}

	public void setClaNumber(String claNumber) {
		this.claNumber = claNumber;
	}

	public String getClaRemark() {
		return claRemark;
	}

	public void setClaRemark(String claRemark) {
		this.claRemark = claRemark;
	}
	
}
