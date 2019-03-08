package pers.xx.edu.utils;

import java.math.BigDecimal;

/**
 * @author xiexing
 * @description
 * @create 2018-9-7下午10:36:14
 */
public class AchievementFinanceNeed {
	
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
	    private BigDecimal financialTotal;
	    

		/**
		 * @return the number
		 */
		public Integer getNumber() {
			return number;
		}

		public BigDecimal getFinancialTotal() {
			return financialTotal;
		}

		public void setFinancialTotal(BigDecimal financialTotal) {
			this.financialTotal = financialTotal;
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
		 * @return the netProfitIndex
		 */
		public BigDecimal getNetProfitIndex() {
			return netProfitIndex;
		}

		/**
		 * @param netProfitIndex the netProfitIndex to set
		 */
		public void setNetProfitIndex(BigDecimal netProfitIndex) {
			this.netProfitIndex = netProfitIndex;
		}

		/**
		 * @return the netProfitCompletion
		 */
		public BigDecimal getNetProfitCompletion() {
			return netProfitCompletion;
		}

		/**
		 * @param netProfitCompletion the netProfitCompletion to set
		 */
		public void setNetProfitCompletion(BigDecimal netProfitCompletion) {
			this.netProfitCompletion = netProfitCompletion;
		}

		/**
		 * @return the netProfitScore
		 */
		public BigDecimal getNetProfitScore() {
			return netProfitScore;
		}

		/**
		 * @param netProfitScore the netProfitScore to set
		 */
		public void setNetProfitScore(BigDecimal netProfitScore) {
			this.netProfitScore = netProfitScore;
		}

		/**
		 * @return the partyProfitIndex
		 */
		public BigDecimal getPartyProfitIndex() {
			return partyProfitIndex;
		}

		/**
		 * @param partyProfitIndex the partyProfitIndex to set
		 */
		public void setPartyProfitIndex(BigDecimal partyProfitIndex) {
			this.partyProfitIndex = partyProfitIndex;
		}

		/**
		 * @return the partyProfitCompletion
		 */
		public BigDecimal getPartyProfitCompletion() {
			return partyProfitCompletion;
		}

		/**
		 * @param partyProfitCompletion the partyProfitCompletion to set
		 */
		public void setPartyProfitCompletion(BigDecimal partyProfitCompletion) {
			this.partyProfitCompletion = partyProfitCompletion;
		}

		/**
		 * @return the partyProfitScore
		 */
		public BigDecimal getPartyProfitScore() {
			return partyProfitScore;
		}

		/**
		 * @param partyProfitScore the partyProfitScore to set
		 */
		public void setPartyProfitScore(BigDecimal partyProfitScore) {
			this.partyProfitScore = partyProfitScore;
		}

		/**
		 * @return the customerProfitIndex
		 */
		public BigDecimal getCustomerProfitIndex() {
			return customerProfitIndex;
		}

		/**
		 * @param customerProfitIndex the customerProfitIndex to set
		 */
		public void setCustomerProfitIndex(BigDecimal customerProfitIndex) {
			this.customerProfitIndex = customerProfitIndex;
		}

		/**
		 * @return the customerProfitCompletion
		 */
		public BigDecimal getCustomerProfitCompletion() {
			return customerProfitCompletion;
		}

		/**
		 * @param customerProfitCompletion the customerProfitCompletion to set
		 */
		public void setCustomerProfitCompletion(BigDecimal customerProfitCompletion) {
			this.customerProfitCompletion = customerProfitCompletion;
		}

		/**
		 * @return the customerProfitScore
		 */
		public BigDecimal getCustomerProfitScore() {
			return customerProfitScore;
		}

		/**
		 * @param customerProfitScore the customerProfitScore to set
		 */
		public void setCustomerProfitScore(BigDecimal customerProfitScore) {
			this.customerProfitScore = customerProfitScore;
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
		 * @return the otherCompletion
		 */
		public BigDecimal getOtherCompletion() {
			return otherCompletion;
		}

		/**
		 * @param otherCompletion the otherCompletion to set
		 */
		public void setOtherCompletion(BigDecimal otherCompletion) {
			this.otherCompletion = otherCompletion;
		}

		/**
		 * @return the otherScore
		 */
		public BigDecimal getOtherScore() {
			return otherScore;
		}

		/**
		 * @param otherScore the otherScore to set
		 */
		public void setOtherScore(BigDecimal otherScore) {
			this.otherScore = otherScore;
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
		 * @return the fiveCostIndex
		 */
		public BigDecimal getFiveCostIndex() {
			return fiveCostIndex;
		}

		/**
		 * @param fiveCostIndex the fiveCostIndex to set
		 */
		public void setFiveCostIndex(BigDecimal fiveCostIndex) {
			this.fiveCostIndex = fiveCostIndex;
		}

		/**
		 * @return the fiveCostCompletion
		 */
		public BigDecimal getFiveCostCompletion() {
			return fiveCostCompletion;
		}

		/**
		 * @param fiveCostCompletion the fiveCostCompletion to set
		 */
		public void setFiveCostCompletion(BigDecimal fiveCostCompletion) {
			this.fiveCostCompletion = fiveCostCompletion;
		}

		/**
		 * @return the fiveCostScore
		 */
		public BigDecimal getFiveCostScore() {
			return fiveCostScore;
		}

		/**
		 * @param fiveCostScore the fiveCostScore to set
		 */
		public void setFiveCostScore(BigDecimal fiveCostScore) {
			this.fiveCostScore = fiveCostScore;
		}

		/**
		 * @return the debtReceivableIndex
		 */
		public BigDecimal getDebtReceivableIndex() {
			return debtReceivableIndex;
		}

		/**
		 * @param debtReceivableIndex the debtReceivableIndex to set
		 */
		public void setDebtReceivableIndex(BigDecimal debtReceivableIndex) {
			this.debtReceivableIndex = debtReceivableIndex;
		}

		/**
		 * @return the debtReceivableCompletion
		 */
		public BigDecimal getDebtReceivableCompletion() {
			return debtReceivableCompletion;
		}

		/**
		 * @param debtReceivableCompletion the debtReceivableCompletion to set
		 */
		public void setDebtReceivableCompletion(BigDecimal debtReceivableCompletion) {
			this.debtReceivableCompletion = debtReceivableCompletion;
		}

		/**
		 * @return the debtReceivableScore
		 */
		public BigDecimal getDebtReceivableScore() {
			return debtReceivableScore;
		}

		/**
		 * @param debtReceivableScore the debtReceivableScore to set
		 */
		public void setDebtReceivableScore(BigDecimal debtReceivableScore) {
			this.debtReceivableScore = debtReceivableScore;
		}
	    
}
