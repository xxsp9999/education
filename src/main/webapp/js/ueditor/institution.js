function addCellAttr(rowId, val, rawObject, cm, rdata) {
	if (rawObject.status == "待审核"  || rawObject.message == "议案申请" ||rawObject.statu == "事项整改中") {
		return "style='color:#627de5'";
	}
	if(rawObject.statu == "事项验收中"){
		return "style='color:blue'";
	}
	if(rawObject.statu == "验收通过" || rawObject.message == "审核通过" || rawObject.message == "议案通过" || rawObject.message == "议案通过" || rawObject.status == "发布成功"){
		return "style='color:#0AA770;font-weight: bold'";//显示绿色
	}
	if (rawObject.message == "部门审核" || rawObject.message == "领导审核" || rawObject.message == "企管审核" || rawObject.status == "审核通过" || rawObject.message == "地建部已审核" || rawObject.message == "质安部已审核" || rawObject.message == "客户部已审核") {
		return "style='color:blue'";//显示蓝色
	}
	
	if (rawObject.status == "未通过" || rawObject.statu == "验收驳回" || rawObject.message == "驳回" || rawObject.message == "企管驳回"  || rawObject.message == "部门驳回" || rawObject.message == "领导驳回" || rawObject.status == "发布驳回" || rawObject.status == "审核驳回") {
		return "style='color:red'";
	}
	
}