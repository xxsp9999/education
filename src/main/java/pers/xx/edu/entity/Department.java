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

/**
 *
 * @description 部门表
 * @author 白贵才
 * @create 2017-10-19下午8:17:28
 *
 */
@Table(name = "department")
@Entity
public class Department implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;//主键

	@Column(name = "name")
	private String name;// 部门名称

	@Column(name = "code")
	private String code;// 部门代码

	@Column(name = "level")
	private int level;// 部门等级（0：无父类；1：有父类）

	@ManyToOne()
	@JoinColumn(name = "parent")
	private Department parent;// 所属部门

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "create_date")
	private Date createDate;//创建时间
	
	@Column(name = "remark")
	private String remark;// 备注

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Department getParent() {
		return parent;
	}

	public void setParent(Department parent) {
		this.parent = parent;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", code=" + code
				+ ", level=" + level + ", parent=" + parent + ", remark="
				+ remark + "]";
	}

	public Department() {
		super();
	}


}
