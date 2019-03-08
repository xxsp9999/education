package pers.xx.edu.vo;


/**
 * 
 * @description 部门vo
 * @author 白贵才
 * @create 2017-10-19下午8:17:28
 *
 */
public class DepartmentVo{

	private String name;// 部门名称
	
	private String code;// 部门代码

	private int level;// 部门等级（0：无父类；1：有父类）

	private String parentDepartId;// 所属部门
	
	private String remark;// 备注

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

	public String getParentDepartId() {
		return parentDepartId;
	}

	public void setParentDepartId(String parentDepartId) {
		this.parentDepartId = parentDepartId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}
