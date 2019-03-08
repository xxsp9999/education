package pers.xx.edu.vo;


/**
 * 临时库存Vo
 * @author lch
 *
 */
public class TempStockVo {
	
	private String barCode;//条形码编号()
	
	private String name;//名称
	
	private int amount = 0;//数量
	
	private String spec;//规格
	
	private String measurementUnit;//计量单位
	
	private float price= 0.0f;//单价(RMB)
	
	private String introduce;//物品介绍
	
	private String state = "1";//状态(1:正常)
	
	private String examine;//审核信息

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getMeasurementUnit() {
		return measurementUnit;
	}

	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getExamine() {
		return examine;
	}

	public void setExamine(String examine) {
		this.examine = examine;
	}
	
	
}
