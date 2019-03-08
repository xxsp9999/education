package pers.xx.edu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class SearchUtils {
	
	public static Map<String,Object> getRearchContionIn(HttpServletRequest request, String beginTime, String endTime,String state,String deliverNumber) throws ParseException{
				
		        Map<String,Object>  map=new HashMap<String, Object>();
				Date startDate = null;
				Date stopDate = null;		
			/*	Calendar c=Calendar.getInstance();		*/        
				
				if (beginTime != null&& !"".equals(beginTime) ) {
					startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(beginTime);
					System.out.println(startDate);
				}/*else{
		  		 	c.setTime(new Date());
		  		 	c.add(Calendar.DATE, -7);
		  		 	startDate=c.getTime();	 	
		  		}*/
				
				if (endTime != null&& !"".equals(endTime)) {
					stopDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime);
				}/*else{
		  			stopDate=new Date();
		  		}*/
				
				if(state!=null && !"".equals(state)){
					if(!"0".equals(state)){
					    map.put("state = ?",Integer.valueOf(state));
					}
				}else{
					map.put("state = ?",1);
				}
				
				if(deliverNumber!=null && !"".equals(deliverNumber)){
					map.put("deliverNumber = ?",deliverNumber);
				}
				
			   if(startDate!=null && stopDate!=null){
				    map.put("inOpDate >= ?", startDate);
				    map.put("inOpDate < ?", stopDate);		
			   }
				return map;
	} 
	
	public static Map<String,Object> getRearchContionOut(HttpServletRequest request, String beginTime, String endTime,String state,String number,String carrier) throws ParseException{

		
        Map<String,Object>  map=new HashMap<String, Object>();
		Date startDate = null;
		Date stopDate = null;		
		Calendar c=Calendar.getInstance();		        
	
		if (beginTime != null&& !"".equals(beginTime) ) {
			startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(beginTime);
		}else{
  		 	c.setTime(new Date());
  		 	c.add(Calendar.DATE, -7);
  		 	startDate=c.getTime();
  		}
		
		if(state!=null && !"".equals(state)){
			if(!"0".equals(state)){
			    map.put("state = ?",Integer.valueOf(state));
			}
		}
		
		if (endTime != null&& !"".equals(endTime)) {
			stopDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime);
		}else{
  			stopDate=new Date();

  		}
		if(state!=null && !"".equals(state)){
			if(!"0".equals(state)){
			    map.put("state = ?",Integer.valueOf(state));
			}
		}else{
			map.put("state = ?",1);
		}
		  if(number!=null && !"".equals(number) ){
			  map.put("number like ?", "%"+number+"%");
       }
      
		  if(carrier!=null && !"".equals(carrier) ){
        	map.put("carrier like ?","%"+ carrier+"%");
       }
		
		if (startDate != null && stopDate != null) {
			map.put("out_op_date >= ?", startDate);
			map.put("out_op_date < ?", stopDate);
		}
		
return map;
}
	/**
	 * @description 新增根据物品名字查询
	 * @createByDate 2017年12月11日17:15:01
	 */
//	if (goodsSearchWord != null && !"".equals(goodsSearchWord)) {
//		Map<String, Object> params2 = new HashMap<String, Object>();
//		Map<String, String> groupBy = new HashMap<String, String>();
//		params2.put("goods.name like ?", "%" + goodsSearchWord + "%");
//		groupBy.put("group by ", "goodsInStorage");
//
//		List<GoodsInStorageDetail> tempInDetailList = inStorageDetailService.getList(params2, groupBy);
//		if (tempInDetailList != null && tempInDetailList.size() > 0) {
//			Integer[] tempIds = new Integer[tempInDetailList.size()];
//			for (int i = 0; i < tempIds.length; i++) {
//				tempIds[i] = tempInDetailList.get(i) == null ? 0
//						: tempInDetailList.get(i).getGoodsInStorage().getId();
//			}
//
//			params.put("id in (:id)", tempIds);
//		} else {
//			// 传入为0 的数组，肯定就是查不到这个数据
//			Integer[] tempIds = new Integer[1];
//			tempIds[0] = 0;
//			params.put("id in (:id)", tempIds);
//		}
//	}
}
