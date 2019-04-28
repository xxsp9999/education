package pers.xx.edu.utils;

import pers.xx.edu.entity.Role;

/**
 * @author XieXing
 * @createDate 2019年4月15日 上午9:07:45
 * @description 登陆信息工具类
 */
public class LoginInfo {
	
	private Integer userId;//用户Id
	
	private String userName;//用户名称
	
	private String img;//照片
	
	private Role userRole;//用户角色

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Role getUserRole() {
		return userRole;
	}

	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	

}
