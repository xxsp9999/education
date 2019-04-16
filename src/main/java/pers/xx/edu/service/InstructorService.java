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
	 * @createDate 2019年4月15日 上午9:20:30
	 * @description 导员登陆验证
	 * @param code
	 * @param password
	 * @return
	 */
	Instructor login(String code,String password);
	
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
