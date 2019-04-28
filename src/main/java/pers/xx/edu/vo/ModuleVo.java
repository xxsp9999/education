package pers.xx.edu.vo;


/**
 * 
 * @description 模块Vo 
 * @author XieXing
 * @create 2018-10-23下午5:05:10
 *
 */
public class ModuleVo{

	private String name;//名字
	
	private String code;//代码(如customer)
	
	private String opType;//操作类型(如view)
	
	private String url;//网址
	
	private String img;//图标
	
	private String sort;//排序
	
	private String type;//请求类型
	
	private String remark;//备注

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	

}
