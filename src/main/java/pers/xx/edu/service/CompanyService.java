package pers.xx.edu.service;

import pers.xx.edu.entity.Company;

/**
 * @author XieXing
 * @createDate 2019年5月11日 下午4:18:06
 * @description 公司类操作接口
 */
public interface CompanyService extends BaseService<Company>{
	/**
	 * 
	 * @author XieXing
	 * @createDate 2019年5月25日 下午5:44:47
	 * @description 企业登陆
	 * @param code
	 * @param password
	 * @return
	 */
	Company login(String code,String password);
}
