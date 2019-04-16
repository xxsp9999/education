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
 * @description 模块表
 * @author XieXing
 * @create 2018-10-23下午5:05:10
 *
 */
@Entity
@Table(name = "module")
public class Module implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;//主键

	@Column(name = "name")
	private String name;//名字

	@Column(name = "code")
	private String code;//代码(如customer)

	@Column(name = "op_type")
	private String opType;//操作类型(如view)

	@Column(name = "url")
	private String url;//网址

	@Column(name = "img")
	private String img;//图片路径

	@Column(name = "level")
	private String level;//等级

	@Column(name = "sort")
	private String sort;//排序

	@ManyToOne
	@JoinColumn(name = "parent")
	private Module parent;//父模块（level = 0时，parent = null）

	@Column(name = "type")
	private String type;//请求类型

	@Column(name = "remark")
	private String remark;//备注

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "create_date")
	private Date createDate;//创建日期

	@ManyToOne
	@JoinColumn(name = "create_user")
	private User createUser;//创建人

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "update_date")
	private Date updateDate;//修改日期

	@ManyToOne
	@JoinColumn(name = "update_user")
	private User updateUser;//修改人

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

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Module getParent() {
		return parent;
	}

	public void setParent(Module parent) {
		this.parent = parent;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public User getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}

	@Override
	public String toString() {
		return "Module [id=" + id + ", name=" + name + ", code=" + code
				+ ", opType=" + opType + ", url=" + url + ", img=" + img
				+ ", level=" + level + ", sort=" + sort + ", parent=" + parent
				+ ", type=" + type + ", remark=" + remark + ", createDate="
				+ createDate + ", createUser=" + createUser + ", updateDate="
				+ updateDate + ", updateUser=" + updateUser + "]";
	}

	public Module() {

	}



}
