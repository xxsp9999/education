package pers.xx.edu.utils;
/**
 * @author XieXing
 * @description 登陆状态工具类
 * @create 2019年2月25日 下午2:29:38
 */
public class LoginStatus {
	
	private boolean status;//状态
	
	private String message;//信息
	
	private Integer power;//权限 
	
	public LoginStatus() {
		super();
	}
	
	/**
	 * @param status
	 * @param message
	 */
	public LoginStatus(boolean status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	/**
	 * @param status
	 * @param message
	 * @param power
	 */
	public LoginStatus(boolean status, String message, Integer power) {
		super();
		this.status = status;
		this.message = message;
		this.power = power;
	}
	public boolean isStatus() {
		return status;
	}
	public String getMessage() {
		return message;
	}
	public Integer getPower() {
		return power;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setPower(Integer power) {
		this.power = power;
	}

}
