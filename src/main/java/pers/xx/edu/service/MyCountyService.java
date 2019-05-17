package pers.xx.edu.service;

import java.util.List;

import pers.xx.edu.entity.MyCounty;

/**
 * @author XieXing
 * @createDate 2019年5月5日 上午9:26:26
 * @description 县（第三级地名接口）
 */
public interface MyCountyService extends BaseService<MyCounty>{
	/**
	 * @author XieXing
	 * @createDate 2019年5月5日 上午9:39:25
	 * @description 根据第二级地址获取第三级地址
	 * @param cid
	 * @return
	 */
	List<MyCounty> getByCityId(Integer cid);
}
