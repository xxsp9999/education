package pers.xx.edu.vo;



/**
 * 
 * @description 参数Vo
 * @author XieXing
 * @create 2018-11-1下午4:19:21
 *
 */
public class ParamsVo {

	private String name;//名称
	
	private String description;//描述
	
	private String params;//参数
	
	private String code;//代码
	
	private String classType;//数据类型
	
	private int deleteable = 1;//可删除(0:不可以;1:可以)

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public int getDeleteable() {
		return deleteable;
	}

	public void setDeleteable(int deleteable) {
		this.deleteable = deleteable;
	}

}
