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
 * @createDate 2019年5月25日 上午9:25:32
 * @description 学生活动信息实体
 */
@Entity
@Table(name="student_practice")
public class StudentPractice implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="practice_student",columnDefinition="int(11) COMMENT '学生'")
	private Student practiceStudent;//学生
	
	@Column(name="item_name",columnDefinition="varchar(255) COMMENT '实践事项'")
	private String itemName;//实践事项
	
	@Column(name="prize_situation",columnDefinition="varchar(255) COMMENT '获奖情况'")
	private String prizeSituation;//获奖情况
	
	@Column(name="certificate_path",columnDefinition="varchar(255) COMMENT '实践证书'")
	private String certificatePath;//实践证书
	
	@Column(name="practice_score",columnDefinition="varchar(255) COMMENT '相关评分'")
	private String practiceScore;//相关评分
	
	@Column(name="check_state",columnDefinition="varchar(255) COMMENT '审核状态'")
	private String checkState;//审核状态
	
	@JSONField(format = "yyyy年MM月dd日")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@Column(name="practice_date",columnDefinition="datetime COMMENT '活动时间'")
	private Date praceticeDate;//活动时间
	
	@Column(name="practice_remark",columnDefinition="varchar(255) COMMENT '实践备注'")
	private String practiceRemark;//备注

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

	public Student getPracticeStudent() {
		return practiceStudent;
	}

	public void setPracticeStudent(Student practiceStudent) {
		this.practiceStudent = practiceStudent;
	}

	public Date getPraceticeDate() {
		return praceticeDate;
	}

	public void setPraceticeDate(Date praceticeDate) {
		this.praceticeDate = praceticeDate;
	}

}
