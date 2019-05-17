package pers.xx.edu.service;

import java.util.List;

import pers.xx.edu.entity.MyCity;

/**
 * @author XieXing
 * @createDate 2019年5月5日 上午9:25:46
 * @description 市(第二级地名接口)
 */
public interface MyCityService extends BaseService<MyCity>{
	/**
	 * @author XieXing
	 * @createDate 2019年5月5日 上午9:37:45
	 * @description 根据省份id获取市（第二级地址）
	 * @param pid
	 * @return
	 */
	List<MyCity> getByPid(Integer pid);
}
