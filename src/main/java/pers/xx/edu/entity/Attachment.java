package pers.xx.edu.entity;

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
 * @description 课件文件信息类
 * @create 2019年2月26日 下午8:57:47
 */
@Entity
@Table(name="attachment")
public class Attachment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="courseware",columnDefinition="int(11) COMMENT '课件信息'")
	private Courseware courseware;//课件信息
	
	@Column(name="file_name",columnDefinition="varchar(255) COMMENT '文件名 '")
	private String fileName;//文件名
	
	@Column(name="at_path",columnDefinition="varchar(255) COMMENT '文件路径 '")
	private String path;//文件路径
	
	@JSONField(format = "yyyy年MM月dd日")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="upload_time",columnDefinition="datetime COMMENT '上传时间'")
	private Date uploadTime;//上传时间
	
	@Column(name="at_remark",columnDefinition="varchar(255) COMMENT '备注'")
	private String remark;//备注

	public Integer getId() {
		return id;
	}

	public String getPath() {
		return path;
	}

	public String getRemark() {
		return remark;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public void setPath(String path) {
		this.path = path;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public Courseware getCourseware() {
		return courseware;
	}

	public void setCourseware(Courseware courseware) {
		this.courseware = courseware;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	
	
}
