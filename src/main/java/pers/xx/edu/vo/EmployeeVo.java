package pers.xx.edu.vo;


import java.io.Serializable;
import java.util.Date;

/**
 * @author xiexing
 * @description EmployeeVo类
 * @create 2018年9月26日 下午3:15:54
 */
public class EmployeeVo implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String companyName;
	private String addr;
	private String registeredCapital;
	private String corporation;
	private String businessScope;
	private Date createDate;
	private Date businessTerm;
	private String registeredAuthority;
	private String shareholder;
	private String creditCode;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getRegisteredCapital() {
		return registeredCapital;
	}
	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital;
	}
	public String getCorporation() {
		return corporation;
	}
	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}
	public String getBusinessScope() {
		return businessScope;
	}
	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getBusinessTerm() {
		return businessTerm;
	}
	public void setBusinessTerm(Date businessTerm) {
		this.businessTerm = businessTerm;
	}
	public String getRegisteredAuthority() {
		return registeredAuthority;
	}
	public void setRegisteredAuthority(String registeredAuthority) {
		this.registeredAuthority = registeredAuthority;
	}
	public String getShareholder() {
		return shareholder;
	}
	public void setShareholder(String shareholder) {
		this.shareholder = shareholder;
	}
	public String getCreditCode() {
		return creditCode;
	}
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
}
