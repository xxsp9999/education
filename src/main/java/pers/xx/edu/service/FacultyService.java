package pers.xx.edu.service;

import pers.xx.edu.entity.Faculty;

/**
 * @author XieXing
 * @createDate 2019年4月9日 下午3:27:12
 * @description 学院接口
 */
public interface FacultyService extends BaseService<Faculty>{
	
	/**
	 * @author XieXing
	 * @createDate 2019年4月11日 下午2:02:06
	 * @description 根据学院代码获取学院
	 * @param facNumber
	 * @return
	 */
	Faculty getFacultyByfacNumber(String facNumber);

}
