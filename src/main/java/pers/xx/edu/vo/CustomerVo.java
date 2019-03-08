package pers.xx.edu.vo;

/**
 * 客户Vo类
 * @author lch
 *
 */
public class CustomerVo {

	private Integer id;
	//账号
	private String number;
	
	//密码
	//private String password;
	
	//用户名
	private String name;
	
	//电话
	private String phone;
	
	//账号状态(1：正常;0:停用)
	private int state = 1;
	
	//token
	private String token;
	
	//部门id
    private int identityId;
	
    //部门
	private String identity;
	
	//邮箱
	private String mail;
	
    //公司名称
	private String companyName;
	
	//地址
	private String address;
	
	//收货地址
	private String receiverAddress; 
	
	//发货地址
	private String deliveryAddress;
	
	//联系人姓名
	private String contactManName;
	
	//联系人电话
	private String contactManTell; 
	
	//联系人邮箱
	private String contactManMail;
	
	//联系人地址
	private String contactManAddress;
	
	//送货地址
	private String sendAddress;
	
	//收货单位
	private String receiverUnit;
	
	//租赁面积/大小
	private double rentArea=0;
	
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getContactManName() {
		return contactManName;
	}

	public void setContactManName(String contactManName) {
		this.contactManName = contactManName;
	}

	public String getContactManTell() {
		return contactManTell;
	}

	public void setContactManTell(String contactManTell) {
		this.contactManTell = contactManTell;
	}

	public String getContactManMail() {
		return contactManMail;
	}

	public void setContactManMail(String contactManMail) {
		this.contactManMail = contactManMail;
	}

	public String getContactManAddress() {
		return contactManAddress;
	}

	public void setContactManAddress(String contactManAddress) {
		this.contactManAddress = contactManAddress;
	}

	public int getIdentityId() {
		return identityId;
	}

	public void setIdentityId(int identityId) {
		this.identityId = identityId;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSendAddress() {
		return sendAddress;
	}

	public void setSendAddress(String sendAddress) {
		this.sendAddress = sendAddress;
	}

	public String getReceiverUnit() {
		return receiverUnit;
	}

	public void setReceiverUnit(String receiverUnit) {
		this.receiverUnit = receiverUnit;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getRentArea() {
		return rentArea;
	}

	public void setRentArea(double rentArea) {
		this.rentArea = rentArea;
	}
	
	
	
	
	
}
