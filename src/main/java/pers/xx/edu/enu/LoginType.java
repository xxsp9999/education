package pers.xx.edu.enu;

/**
 * 不同登录用户的枚举
 * 
 * @author XieXing
 *
 */
public enum LoginType {

	USER("超级管理员", 1), STUDENT("学生", 2), TEACHER("老师", 3), INSTRUCTOR("导员", 4), LEADER("领导", 5),
	MANAGER("管理员", 6);

	// 登录的类型(角色)
	private String type;

	// 类型ID(角色ID)
	private Integer typeId;

	private LoginType(String type, Integer typeId) {
		this.type = type;
		this.typeId = typeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	
	
}
