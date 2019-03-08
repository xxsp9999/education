package pers.xx.edu.vo;


/**
 * @author zhanghongbo
 * @description 显示树形结构节点对象
 */
public class TreeNode {

	public TreeNode() {
	}

	private String id;// 节点id
	private String name;// 节点显示名称
	private String pId;// 父节点id
	private String type;// 节点类型
	private String checked;// 是否选中 true|false
	private String nocheck;// 是否显示复选框
	private String isParent;// 是否父节点
	private String open;//是否展开该节点

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getNocheck() {
		return nocheck;
	}

	public void setNocheck(String nocheck) {
		this.nocheck = nocheck;
	}

	public String getIsParent() {
		return isParent;
	}

	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}
	
	

}