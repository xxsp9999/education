package pers.xx.edu.utils;


import java.math.BigDecimal;

/**
 * @ClassName AchievementNeed
 * @Author XuWeiZhi
 * @Description
 * @Date 2018-09-18 11:55 星期二 PetroEnter
 * @VERSION 1.0.0
 * @updater xiexing
 **/
public class AchievementNeed {

    @IsNeeded
    private Integer number;

    @IsNeeded
    private String organizationName;

    @IsNeeded
    private BigDecimal netProfitIndex;

    @IsNeeded
    private BigDecimal netProfitCompletion;

    @IsNeeded
    private BigDecimal netProfitScore; 

    @IsNeeded
    private BigDecimal partyProfitIndex;

    @IsNeeded
    private BigDecimal partyProfitCompletion;

    @IsNeeded
    private BigDecimal partyProfitScore;

    @IsNeeded
    private BigDecimal customerProfitIndex;

    @IsNeeded
    private BigDecimal customerProfitCompletion;//

    @IsNeeded
    private BigDecimal customerProfitScore;

    @IsNeeded
    private BigDecimal otherIndex;
    
    @IsNeeded
    private BigDecimal otherCompletion;

    @IsNeeded
    private BigDecimal otherScore;

    //增值业务考核得分
    @IsNeeded
    private BigDecimal valueAddScore;
    
   /* @IsNeeded
    private BigDecimal cashDividendsIndex;//

    @IsNeeded
    private BigDecimal cashDividendsCompletion;

    @IsNeeded
    private BigDecimal cashDividendsScore;*/

    @IsNeeded
    private BigDecimal fiveCostIndex;

    @IsNeeded
    private BigDecimal fiveCostCompletion;

    @IsNeeded
    private BigDecimal fiveCostScore;//

    @IsNeeded
    private BigDecimal debtReceivableIndex;

    @IsNeeded
    private BigDecimal debtReceivableCompletion;

    @IsNeeded
    private BigDecimal debtReceivableScore;

    @IsNeeded
    private BigDecimal qhseScore;

    @IsNeeded
    private BigDecimal manageScore;//

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
    
    @IsNeeded
    private BigDecimal otherFactorsReduceScore;

    @IsNeeded
    private BigDecimal otherFactorsScore;

    @IsNeeded
    private BigDecimal performanceAppraisalTotal;

    /*@IsNeeded
    private BigDecimal checkTypeCoefficient;

    @IsNeeded
    private BigDecimal performanceBonusCoefficient;//
*/
    @IsNeeded
    private String remark;


    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public BigDecimal getNetProfitIndex() {
        return netProfitIndex;
    }

    public void setNetProfitIndex(BigDecimal netProfitIndex) {
        this.netProfitIndex = netProfitIndex;
    }

    public BigDecimal getNetProfitCompletion() {
        return netProfitCompletion;
    }

    public void setNetProfitCompletion(BigDecimal netProfitCompletion) {
        this.netProfitCompletion = netProfitCompletion;
    }

    public BigDecimal getNetProfitScore() {
        return netProfitScore;
    }

    public void setNetProfitScore(BigDecimal netProfitScore) {
        this.netProfitScore = netProfitScore;
    }

    public BigDecimal getPartyProfitIndex() {
        return partyProfitIndex;
    }

    public void setPartyProfitIndex(BigDecimal partyProfitIndex) {
        this.partyProfitIndex = partyProfitIndex;
    }

    public BigDecimal getPartyProfitCompletion() {
        return partyProfitCompletion;
    }

    public void setPartyProfitCompletion(BigDecimal partyProfitCompletion) {
        this.partyProfitCompletion = partyProfitCompletion;
    }

    public BigDecimal getPartyProfitScore() {
        return partyProfitScore;
    }

    public void setPartyProfitScore(BigDecimal partyProfitScore) {
        this.partyProfitScore = partyProfitScore;
    }

    public BigDecimal getCustomerProfitIndex() {
        return customerProfitIndex;
    }

    public void setCustomerProfitIndex(BigDecimal customerProfitIndex) {
        this.customerProfitIndex = customerProfitIndex;
    }

    public BigDecimal getCustomerProfitCompletion() {
        return customerProfitCompletion;
    }

    public void setCustomerProfitCompletion(BigDecimal customerProfitCompletion) {
        this.customerProfitCompletion = customerProfitCompletion;
    }

    public BigDecimal getCustomerProfitScore() {
        return customerProfitScore;
    }

    public void setCustomerProfitScore(BigDecimal customerProfitScore) {
        this.customerProfitScore = customerProfitScore;
    }

    public BigDecimal getOtherScore() {
        return otherScore;
    }

    public void setOtherScore(BigDecimal otherScore) {
        this.otherScore = otherScore;
    }

   /* public BigDecimal getViewAddScore() {
        return viewAddScore;
    }

    public void setViewAddScore(BigDecimal viewAddScore) {
        this.viewAddScore = viewAddScore;
    }

  */  
    public BigDecimal getDebtReceivableIndex() {
        return debtReceivableIndex;
    }

    public void setDebtReceivableIndex(BigDecimal debtReceivableIndex) {
        this.debtReceivableIndex = debtReceivableIndex;
    }

    public BigDecimal getDebtReceivableCompletion() {
        return debtReceivableCompletion;
    }

    public void setDebtReceivableCompletion(BigDecimal debtReceivableCompletion) {
        this.debtReceivableCompletion = debtReceivableCompletion;
    }

    public BigDecimal getDebtReceivableScore() {
        return debtReceivableScore;
    }

    public void setDebtReceivableScore(BigDecimal debtReceivableScore) {
        this.debtReceivableScore = debtReceivableScore;
    }

    public BigDecimal getQhseScore() {
        return qhseScore;
    }

    public void setQhseScore(BigDecimal qhseScore) {
        this.qhseScore = qhseScore;
    }

    public BigDecimal getManageScore() {
        return manageScore;
    }

    public void setManageScore(BigDecimal manageScore) {
        this.manageScore = manageScore;
    }

    public BigDecimal getOtherCompletion() {
        return otherCompletion;
    }

    public void setOtherCompletion(BigDecimal otherCompletion) {
        this.otherCompletion = otherCompletion;
    }

    public BigDecimal getOtherFactorsReduceScore() {
        return otherFactorsReduceScore;
    }

    public void setOtherFactorsReduceScore(BigDecimal otherFactorsReduceScore) {
        this.otherFactorsReduceScore = otherFactorsReduceScore;
    }

    public BigDecimal getOtherFactorsScore() {
        return otherFactorsScore;
    }

    public void setOtherFactorsScore(BigDecimal otherFactorsScore) {
        this.otherFactorsScore = otherFactorsScore;
    }

    public BigDecimal getPerformanceAppraisalTotal() {
        return performanceAppraisalTotal;
    }

    public void setPerformanceAppraisalTotal(BigDecimal performanceAppraisalTotal) {
        this.performanceAppraisalTotal = performanceAppraisalTotal;
    }



   
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getFiveCostIndex() {
        return fiveCostIndex;
    }

    public void setFiveCostIndex(BigDecimal fiveCostIndex) {
        this.fiveCostIndex = fiveCostIndex;
    }

    public BigDecimal getFiveCostCompletion() {
        return fiveCostCompletion;
    }

    public void setFiveCostCompletion(BigDecimal fiveCostCompletion) {
        this.fiveCostCompletion = fiveCostCompletion;
    }

    public BigDecimal getFiveCostScore() {
        return fiveCostScore;
    }

    public void setFiveCostScore(BigDecimal fiveCostScore) {
        this.fiveCostScore = fiveCostScore;
    }

	/**
	 * @return the otherIndex
	 */
	public BigDecimal getOtherIndex() {
		return otherIndex;
	}

	/**
	 * @param otherIndex the otherIndex to set
	 */
	public void setOtherIndex(BigDecimal otherIndex) {
		this.otherIndex = otherIndex;
	}

	/**
	 * @return the valueAddScore
	 */
	public BigDecimal getValueAddScore() {
		return valueAddScore;
	}

	/**
	 * @param valueAddScore the valueAddScore to set
	 */
	public void setValueAddScore(BigDecimal valueAddScore) {
		this.valueAddScore = valueAddScore;
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
	
	/**
     * @description 四舍五入保留两位小数
     * @param bigDecimal
     * @return
     */
	private BigDecimal cleanDate(BigDecimal bigDecimal) {
		return bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * @description 数据格式化
	 */
	public  void dateFormat() {
		netProfitIndex = cleanDate(netProfitIndex);
		netProfitScore = cleanDate(netProfitScore);
		netProfitScore = cleanDate(netProfitScore);
		partyProfitIndex = cleanDate(partyProfitIndex);
		partyProfitCompletion = cleanDate(partyProfitCompletion);
		partyProfitScore = cleanDate(partyProfitScore);
		customerProfitIndex = cleanDate(customerProfitIndex);
		customerProfitCompletion = cleanDate(customerProfitCompletion);
		customerProfitScore = cleanDate(customerProfitScore);
		otherIndex = cleanDate(otherIndex);
		otherScore = cleanDate(otherScore);
		valueAddScore = cleanDate(valueAddScore);
		fiveCostIndex = cleanDate(fiveCostIndex);
		fiveCostCompletion = cleanDate(fiveCostCompletion);
		fiveCostScore = cleanDate(fiveCostScore);
		debtReceivableIndex = cleanDate(valueAddScore);
		debtReceivableCompletion = cleanDate(valueAddScore);
		debtReceivableScore = cleanDate(debtReceivableScore);
		qhseScore = cleanDate(qhseScore);
		manageScore = cleanDate(manageScore);
		financeManageScore = cleanDate(financeManageScore);
		techniqueManageScore = cleanDate(techniqueManageScore);
		orgnizeManageScore = cleanDate(orgnizeManageScore);
		stabilityManageScore = cleanDate(stabilityManageScore);
		otherFactorsReduceScore = cleanDate(otherFactorsReduceScore);
		otherFactorsScore = cleanDate(otherFactorsScore);
		performanceAppraisalTotal = cleanDate(performanceAppraisalTotal);
	}
	
	@Override
	public String toString() {
		return "AchievementNeed [number=" + number + ", organizationName="
				+ organizationName + ", netProfitIndex=" + netProfitIndex
				+ ", netProfitCompletion=" + netProfitCompletion
				+ ", netProfitScore=" + netProfitScore + ", partyProfitIndex="
				+ partyProfitIndex + ", partyProfitCompletion="
				+ partyProfitCompletion + ", partyProfitScore="
				+ partyProfitScore + ", customerProfitIndex="
				+ customerProfitIndex + ", customerProfitCompletion="
				+ customerProfitCompletion + ", customerProfitScore="
				+ customerProfitScore + ", otherIndex=" + otherIndex
				+ ", otherCompletion=" + otherCompletion + ", otherScore="
				+ otherScore + ", valueAddScore=" + valueAddScore
				+ ", fiveCostIndex=" + fiveCostIndex + ", fiveCostCompletion="
				+ fiveCostCompletion + ", fiveCostScore=" + fiveCostScore
				+ ", debtReceivableIndex=" + debtReceivableIndex
				+ ", debtReceivableCompletion=" + debtReceivableCompletion
				+ ", debtReceivableScore=" + debtReceivableScore
				+ ", qhseScore=" + qhseScore + ", manageScore=" + manageScore
				+ ", financeManageScore=" + financeManageScore
				+ ", techniqueManageScore=" + techniqueManageScore
				+ ", orgnizeManageScore=" + orgnizeManageScore
				+ ", stabilityManageScore=" + stabilityManageScore
				+ ", otherFactorsReduceScore=" + otherFactorsReduceScore
				+ ", otherFactorsScore=" + otherFactorsScore
				+ ", performanceAppraisalTotal=" + performanceAppraisalTotal
				+ ", remark=" + remark + "]";
	}
}
