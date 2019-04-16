package pers.xx.edu.vo;


/**
 * 
 * @description 用户vo 
 * @author XieXing
 * @create 2018年10月31日15:07:26
 *
 */
public class UserVo{
	
	private int id = 0;//ID

	private String number;//工号
	
	private String name;//姓名
	
	private String phone;//电话
	
	private int identityId;//部门id
	
	private String identity;//部门
	
	private int state = 1;//账号状态(1：正常;0:停用)
	
	private String token;//token
	
	private String companyName ;//员工所属公司信息
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getIdentityId() {
		return identityId;
	}

	public void setIdentityId(int identityId) {
		this.identityId = identityId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
