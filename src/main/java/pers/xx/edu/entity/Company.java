package pers.xx.edu.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author XieXing
 * @createDate 2019年5月11日 下午3:07:25
 * @description 公司信息实体
 */
@Entity
@Table(name="company")
public class Company implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="company_number",columnDefinition="varchar(255) COMMENT '公司账号'")
	private String companyNumber;//公司账号

	@Column(name="company_name",columnDefinition="varchar(255) COMMENT '公司名称'")
	private String companyName;//公司名称
	
	@Column(name="company_password",columnDefinition="varchar(255) COMMENT '公司密码'")
	private String companyPassword;//公司密码
	
	@Column(name="company_Tel",columnDefinition="varchar(255) COMMENT '公司联系电话'")
	private String companyTel;//公司联系电话
	
	@Column(name="company_email",columnDefinition="varchar(255) COMMENT '公司邮箱'")
	private String companyEmail;//公司邮箱
	
	@Column(name="uscc",columnDefinition="varchar(255) COMMENT '统一社会信用代码'")
	private String uscc;//统一社会信用代码
	
	@Column(name="organization_code",columnDefinition="varchar(255) COMMENT '组织机构代码'")
	private String organizationCode;//组织机构代码
	
	@ManyToOne
	@JoinColumn(name="company_province",columnDefinition="int(11) COMMENT '公司所在省'")
	private MyProvince companyProvince;//公司所在省
	
	@ManyToOne
	@JoinColumn(name="compan_city",columnDefinition="int(11) COMMENT '公司所在市'")
	private MyCity companCity;//公司所在市
	
	@ManyToOne
	@JoinColumn(name="company_county",columnDefinition="int(11) COMMENT '公司所在县'")
	private MyCounty companyCounty;//公司所在县
	
	@Column(name="company_address",columnDefinition="varchar(255) COMMENT '公司所在详细地址'")
	private String companyAddress;//公司所在详细地址
	
	@Column(name="company_website",columnDefinition="varchar(255) COMMENT '公司所网址'")
	private String companyWebsite;//公司所网址
	
	@Column(name="company_logo",columnDefinition="varchar(255) COMMENT '公司logo'")
	private String companyLogo;//公司 Logo
	
	@Column(name="company_license",columnDefinition="varchar(255) COMMENT '公司营业执照扫描件'")
	private String companyLicense;//公司营业执照扫描件
	
	@Column(name="company_introduce",columnDefinition="mediumtext COMMENT '公司介绍'")
	private String companyIntroduce;//公司介绍
	
	@Column(name="company_remark",columnDefinition="varchar(255) COMMENT '公司备注'")
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

	public String getCompanyPassword() {
		return companyPassword;
	}

	public void setCompanyPassword(String companyPassword) {
		this.companyPassword = companyPassword;
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

	public String getCompanyLicense() {
		return companyLicense;
	}

	public void setCompanyLicense(String companyLicense) {
		this.companyLicense = companyLicense;
	}

	public String getCompanyRemark() {
		return companyRemark;
	}

	public void setCompanyRemark(String companyRemark) {
		this.companyRemark = companyRemark;
	}

	public String getCompanyWebsite() {
		return companyWebsite;
	}

	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}

	public String getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}

	public String getCompanyIntroduce() {
		return companyIntroduce;
	}

	public void setCompanyIntroduce(String companyIntroduce) {
		this.companyIntroduce = companyIntroduce;
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
	
	
	
}
