package pers.xx.edu.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 *
 * @description json数据格式化
 * @author 白贵才
 * @create 2017-3-23下午2:13:13
 *
 */
public class JsonUtils {

	/**
	 * @description 把数据格式化为json类型
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {
		return JSON.toJSONString(obj,
				SerializerFeature.DisableCircularReferenceDetect,
				SerializerFeature.WriteNullStringAsEmpty,
				SerializerFeature.WriteNullNumberAsZero,
				SerializerFeature.WriteDateUseDateFormat).replace("\\", "");
	}

//	[Stock [id=1, warehouse=Warehouse [id=1, number=10001, address=十陵, name=一号仓库, zone=A区, shelves=北京基地, manager=null], goods=Goods [id=2, barCode=789654525, skuCode=null, rfidCode=4A85B86038B6528B3536, name=Johnson强生婴儿, amount=1, spec=10瓶/箱, unitVolume=0.0, unitWeight=0.0, measurementUnit=箱, price=59.9, introduce=一种沐浴露, warehouse=null, category=null, manufacturer=null, borrowTimeLimit=0, state=1, examine=, lowRemind=0, highRemind=0, createDate=null, createUser=null, updateDate=2017-11-07 11:31:01.0, updateUser=User [id=1, number=10001, password=4QrcOUm6Wau+VuBX8g+IPg==, name=白贵才, phone=15184407719, department=Department [id=1, name=仓库部, code=admin, level=0, parent=null, remark=仓库管理员], state=1, token=ut79QRmJQ/p2qVtxQfm3LA==, monthLoginNum=null, thisMonth=null, totalLoginNum=null], palletUnit=4, codeOut=null, codeIn=null], price=1.0, batch=1, batchNum=1, lowRemind=1, highRemind=1, state=1, isShiftPark=1, createDate=2018-06-23 11:00:27.0, createUser=User [id=1, number=10001, password=4QrcOUm6Wau+VuBX8g+IPg==, name=白贵才, phone=15184407719, department=Department [id=1, name=仓库部, code=admin, level=0, parent=null, remark=仓库管理员], state=1, token=ut79QRmJQ/p2qVtxQfm3LA==, monthLoginNum=null, thisMonth=null, totalLoginNum=null], updateDate=2018-06-23 11:01:14.0, updateUser=User [id=1, number=10001, password=4QrcOUm6Wau+VuBX8g+IPg==, name=白贵才, phone=15184407719, department=Department [id=1, name=仓库部, code=admin, level=0, parent=null, remark=仓库管理员], state=1, token=ut79QRmJQ/p2qVtxQfm3LA==, monthLoginNum=null, thisMonth=null, totalLoginNum=null], customer=com.hdo.entity.Customer@4c10976f, days=1, costType=CostType [id=1, code=SMPD, name=A类, spec=1元/平方米/天, price=1.0, remark=按货物面积和天数收费, createDate=2017-12-28 10:27:04.0, updateDate=2018-01-30 12:13:43.0], isPallet=1, salesOrderNumber=null, orderType=null, contractNumber=null, goodsType=null, detailed=null, date=null, name=null, amount=null, storageLocation=null, warehouseCoding=null, warehouseName=null, transferInW=null, transferInS=null, logisticsArea=null, transferInL=null], Stock [id=2, warehouse=null, goods=null, price=null, batch=null, batchNum=0, lowRemind=0, highRemind=100000000, state=1, isShiftPark=1, createDate=null, createUser=null, updateDate=null, updateUser=null, customer=null, days=0, costType=null, isPallet=0, salesOrderNumber=null, orderType=null, contractNumber=null, goodsType=null, detailed=null, date=null, name=null, amount=null, storageLocation=null, warehouseCoding=null, warehouseName=null, transferInW=null, transferInS=null, logisticsArea=null, transferInL=null], Stock [id=3, warehouse=null, goods=null, price=null, batch=null, batchNum=0, lowRemind=0, highRemind=100000000, state=1, isShiftPark=1, createDate=null, createUser=null, updateDate=null, updateUser=null, customer=null, days=0, costType=null, isPallet=0, salesOrderNumber=null, orderType=null, contractNumber=null, goodsType=null, detailed=null, date=null, name=null, amount=null, storageLocation=null, warehouseCoding=null, warehouseName=null, transferInW=null, transferInS=null, logisticsArea=null, transferInL=null], Stock [id=4, warehouse=null, goods=null, price=null, batch=null, batchNum=0, lowRemind=0, highRemind=100000000, state=1, isShiftPark=1, createDate=null, createUser=null, updateDate=null, updateUser=null, customer=null, days=0, costType=null, isPallet=0, salesOrderNumber=null, orderType=null, contractNumber=null, goodsType=null, detailed=null, date=null, name=null, amount=null, storageLocation=null, warehouseCoding=null, warehouseName=null, transferInW=null, transferInS=null, logisticsArea=null, transferInL=null], Stock [id=5, warehouse=null, goods=null, price=null, batch=null, batchNum=0, lowRemind=0, highRemind=100000000, state=1, isShiftPark=1, createDate=null, createUser=null, updateDate=null, updateUser=null, customer=null, days=0, costType=null, isPallet=0, salesOrderNumber=null, orderType=null, contractNumber=null, goodsType=null, detailed=null, date=null, name=null, amount=null, storageLocation=null, warehouseCoding=null, warehouseName=null, transferInW=null, transferInS=null, logisticsArea=null, transferInL=null], Stock [id=6, warehouse=null, goods=null, price=null, batch=null, batchNum=0, lowRemind=0, highRemind=100000000, state=1, isShiftPark=1, createDate=null, createUser=null, updateDate=null, updateUser=null, customer=null, days=0, costType=null, isPallet=0, salesOrderNumber=null, orderType=null, contractNumber=null, goodsType=null, detailed=null, date=null, name=null, amount=null, storageLocation=null, warehouseCoding=null, warehouseName=null, transferInW=null, transferInS=null, logisticsArea=null, transferInL=null], Stock [id=7, warehouse=null, goods=null, price=null, batch=null, batchNum=0, lowRemind=0, highRemind=100000000, state=1, isShiftPark=1, createDate=null, createUser=null, updateDate=null, updateUser=null, customer=null, days=0, costType=null, isPallet=0, salesOrderNumber=null, orderType=null, contractNumber=null, goodsType=null, detailed=null, date=null, name=null, amount=null, storageLocation=null, warehouseCoding=null, warehouseName=null, transferInW=null, transferInS=null, logisticsArea=null, transferInL=null], Stock [id=8, warehouse=null, goods=null, price=null, batch=null, batchNum=0, lowRemind=0, highRemind=100000000, state=1, isShiftPark=1, createDate=null, createUser=null, updateDate=null, updateUser=null, customer=null, days=0, costType=null, isPallet=0, salesOrderNumber=销售订单号1, orderType=订单类型, contractNumber=合同号, goodsType=产品类型, detailed=产品明细, date=2018-06-26 00:00:00.0, name=名称, amount=544, storageLocation=储存地点, warehouseCoding=库位编码, warehouseName=库位名称, transferInW=调入库位, transferInS=调入存储地点, logisticsArea=物流区域, transferInL=调入物流区域], Stock [id=9, warehouse=null, goods=null, price=null, batch=null, batchNum=0, lowRemind=0, highRemind=100000000, state=1, isShiftPark=1, createDate=null, createUser=null, updateDate=null, updateUser=null, customer=null, days=0, costType=null, isPallet=0, salesOrderNumber=销售订单号2, orderType=订单类型, contractNumber=合同号, goodsType=产品类型, detailed=产品明细, date=2018-06-26 00:00:00.0, name=名称, amount=544, storageLocation=储存地点, warehouseCoding=库位编码, warehouseName=库位名称, transferInW=调入库位, transferInS=调入存储地点, logisticsArea=物流区域, transferInL=调入物流区域], Stock [id=10, warehouse=null, goods=null, price=null, batch=null, batchNum=0, lowRemind=0, highRemind=100000000, state=1, isShiftPark=1, createDate=null, createUser=null, updateDate=null, updateUser=null, customer=null, days=0, costType=null, isPallet=0, salesOrderNumber=销售订单号3, orderType=订单类型, contractNumber=合同号, goodsType=产品类型, detailed=产品明细, date=2018-06-26 00:00:00.0, name=名称, amount=544, storageLocation=储存地点, warehouseCoding=库位编码, warehouseName=库位名称, transferInW=调入库位, transferInS=调入存储地点, logisticsArea=物流区域, transferInL=调入物流区域]]
//	[com.hdo.vo.GoodsNumVo@25e37a55]
	/**
	 * @description 把数据格式化为标准json类型,且null变成"null"
	 * @param obj
	 * @return
	 */
	public static String toStandardJson(Object obj) {
		return JSON.toJSONString(obj,
				SerializerFeature.DisableCircularReferenceDetect,
				SerializerFeature.WriteNullStringAsEmpty,
				SerializerFeature.WriteNullListAsEmpty,
				SerializerFeature.WriteNullBooleanAsFalse,
				SerializerFeature.WriteNullNumberAsZero,
				SerializerFeature.WriteDateUseDateFormat,
				SerializerFeature.WriteNonStringValueAsString).replace("\\", "");
	}

}
