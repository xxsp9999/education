package pers.xx.edu.utils;

import java.math.BigDecimal;

/**
 * @author xiexing
 * @description 企管类Need类
 * @create 2018-9-7下午10:36:14
 */
public class AchievementManageNeed {

    @IsNeeded
    private Integer number;

    @IsNeeded
    private String organizationName;

    @IsNeeded
    private BigDecimal qhseScore;

    @IsNeeded
    private BigDecimal manageScore;

   //财务考核管理
    @IsNeeded
    private BigDecimal financeManageScore;
    
    //技术考核管理
    @IsNeeded
    private BigDecimal techniqueManageScore;
    
    //组织考核管理
    @IsNeeded
    private BigDecimal orgnizeManageScore;
    
    //维稳考核管理
    @IsNeeded
    private BigDecimal stabilityManageScore;

    //企管绩效总分
    @IsNeeded
    private BigDecimal manageTotal;
    
    

	public BigDecimal getManageTotal() {
		return manageTotal;
	}

	public void setManageTotal(BigDecimal manageTotal) {
		this.manageTotal = manageTotal;
	}

	/**
	 * @return the number
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}

	/**
	 * @return the organizationName
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/**
	 * @param organizationName the organizationName to set
	 */
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * @return the qhseScore
	 */
	public BigDecimal getQhseScore() {
		return qhseScore;
	}

	/**
	 * @param qhseScore the qhseScore to set
	 */
	public void setQhseScore(BigDecimal qhseScore) {
		this.qhseScore = qhseScore;
	}

	/**
	 * @return the manageScore
	 */
	public BigDecimal getManageScore() {
		return manageScore;
	}

	/**
	 * @param manageScore the manageScore to set
	 */
	public void setManageScore(BigDecimal manageScore) {
		this.manageScore = manageScore;
	}

	/**
	 * @return the financeManageScore
	 */
	public BigDecimal getFinanceManageScore() {
		return financeManageScore;
	}

	/**
	 * @param financeManageScore the financeManageScore to set
	 */
	public void setFinanceManageScore(BigDecimal financeManageScore) {
		this.financeManageScore = financeManageScore;
	}

	/**
	 * @return the techniqueManageScore
	 */
	public BigDecimal getTechniqueManageScore() {
		return techniqueManageScore;
	}

	/**
	 * @param techniqueManageScore the techniqueManageScore to set
	 */
	public void setTechniqueManageScore(BigDecimal techniqueManageScore) {
		this.techniqueManageScore = techniqueManageScore;
	}

	/**
	 * @return the orgnizeManageScore
	 */
	public BigDecimal getOrgnizeManageScore() {
		return orgnizeManageScore;
	}

	/**
	 * @param orgnizeManageScore the orgnizeManageScore to set
	 */
	public void setOrgnizeManageScore(BigDecimal orgnizeManageScore) {
		this.orgnizeManageScore = orgnizeManageScore;
	}

	/**
	 * @return the stabilityManageScore
	 */
	public BigDecimal getStabilityManageScore() {
		return stabilityManageScore;
	}

	/**
	 * @param stabilityManageScore the stabilityManageScore to set
	 */
	public void setStabilityManageScore(BigDecimal stabilityManageScore) {
		this.stabilityManageScore = stabilityManageScore;
	}
}
