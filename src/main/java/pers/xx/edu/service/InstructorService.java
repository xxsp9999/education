package pers.xx.edu.service;

import pers.xx.edu.entity.Instructor;
import pers.xx.edu.vo.InstructorVo;

/**
 * @author XieXing
 * @description 辅导员接口
 * @create 2019年3月17日 下午6:31:08
 */
public interface InstructorService extends BaseService<Instructor> {
	/**
	 * @author XieXing
	 * @createDate 2019年4月12日 上午11:19:55
	 * @description 导员信息编辑
	 * @param instructorVo
	 * @param instructorEntranceDate
	 * @param instructorBirth
	 */
	void edit(InstructorVo instructorVo, String instructorEntranceDate, String instructorBirth);
}
