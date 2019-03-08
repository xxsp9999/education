package pers.xx.edu.enu;

/**
 * 不同登录用户的枚举
 * @author lch
 *
 */
public enum LoginType {

	//后台管理员为  USER-->admin  CUSTOMER-->customer
	USER("User"),CUSTOMER("Customer");
	
	//登录的类型
	private String type;
	
	private LoginType(String type){
		this.type=type;
	}
	
	public String toString(){
		return this.type.toString();
	}
}
