package pers.xx.edu.vo;



/**
 * 账单Vo类
 * @author lch
 *
 */
public class SettlementVo {

	//主键id
	private int id; 
	
	//结算客户名称
	private String customerName;
	
	//结算客户id
	private String customerId;
	
	//结算方式
	private String settlementType;
	
	//存储天数
	private Integer days=0;
	
	//应付金额
	private double apMoney=0.00;
	
	//已付金额
	private double alreadyApMoney=0.00;
	
	/*费用状态
	 * 0：未结算
	 * 1.部分结算
	 * 2.付清
	 */
	private String status="0";
	
	/**
	 *每月生成记录的年月
	 */
	private String yearMonth;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getSettlementType() {
		return settlementType;
	}

	public void setSettlementType(String settlementType) {
		this.settlementType = settlementType;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public double getApMoney() {
		return apMoney;
	}

	public void setApMoney(double apMoney) {
		this.apMoney = apMoney;
	}

	public double getAlreadyApMoney() {
		return alreadyApMoney;
	}

	public void setAlreadyApMoney(double alreadyApMoney) {
		this.alreadyApMoney = alreadyApMoney;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}
	
	
	
	
	
	
}
