package pers.xx.edu.service;

import java.util.List;

import pers.xx.edu.entity.Major;

/**
 * @author XieXing
 * @createDate 2019年4月9日 下午5:19:11
 * @description 专业接口
 */
public interface MajorService extends BaseService<Major> {
	/**
	 * @author XieXing
	 * @createDate 2019年4月11日 下午2:13:54
	 * @description 根据专业代码获取专业
	 * @param majorNumber 专业代码
	 * @return
	 */
	Major getMajorBymajorNumber(String majorNumber);

	List<Major> getMajorsByFacultyId(Integer facId);
}
