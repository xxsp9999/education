package pers.xx.edu.service;

import pers.xx.edu.entity.Teacher;
import pers.xx.edu.vo.TeacherVo;

/**
 * @author XieXing
 * @description
 * @create 2019年3月17日 下午6:29:54
 */
public interface TeacherService extends BaseService<Teacher>{

	
	/**
	 * @author XieXing
	 * @createDate 2019年4月12日 上午10:08:29
	 * @description 编辑教师信息接口
	 * @param teacherVo
	 * @param teaEntranceDate
	 * @param teaBirth
	 * @param teaTitleId
	 */
	void edit(TeacherVo teacherVo, String teaEntranceDate, String teaBirth, Integer teaTitleId);
}
