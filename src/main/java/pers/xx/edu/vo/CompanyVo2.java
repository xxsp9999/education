package pers.xx.edu.vo;

import pers.xx.edu.entity.MyCity;
import pers.xx.edu.entity.MyCounty;
import pers.xx.edu.entity.MyProvince;

/**
 * @author XieXing
 * @createDate 2019年5月12日 下午5:26:43
 * @description 
 */
public class CompanyVo2 {
	private Integer id;
	
	private String companyNumber;//公司账号

	private String companyName;//公司名称
	
	private String companyTel;//公司联系电话
	
	private String companyEmail;//公司邮箱
	
	private String uscc;//统一社会信用代码
	
	private String organizationCode;//组织机构代码
	
	private MyProvince companyProvince;//公司所在省
	
	private MyCity companCity;//公司所在市
	
	private MyCounty companyCounty;//公司所在县
	
	private String companyAddress;//公司所在详细地址
	
	private String companyWebsite;//公司所网址
	
	private String companyRemark;//公司备注

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompanyNumber() {
		return companyNumber;
	}

	public void setCompanyNumber(String companyNumber) {
		this.companyNumber = companyNumber;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyTel() {
		return companyTel;
	}

	public void setCompanyTel(String companyTel) {
		this.companyTel = companyTel;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getUscc() {
		return uscc;
	}

	public void setUscc(String uscc) {
		this.uscc = uscc;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public MyProvince getCompanyProvince() {
		return companyProvince;
	}

	public void setCompanyProvince(MyProvince companyProvince) {
		this.companyProvince = companyProvince;
	}

	public MyCity getCompanCity() {
		return companCity;
	}

	public void setCompanCity(MyCity companCity) {
		this.companCity = companCity;
	}

	public MyCounty getCompanyCounty() {
		return companyCounty;
	}

	public void setCompanyCounty(MyCounty companyCounty) {
		this.companyCounty = companyCounty;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyWebsite() {
		return companyWebsite;
	}

	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}

	public String getCompanyRemark() {
		return companyRemark;
	}

	public void setCompanyRemark(String companyRemark) {
		this.companyRemark = companyRemark;
	}
	
	
	
}
