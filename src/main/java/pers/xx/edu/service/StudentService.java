package pers.xx.edu.service;

import pers.xx.edu.entity.Student;
import pers.xx.edu.vo.StudentVo;

/**
 * @author XieXing
 * @description 学生接口
 * @create 2019年3月17日 下午6:28:59
 */
public interface StudentService extends BaseService<Student> {
	/**
	 * @author XieXing
	 * @createDate 2019年4月11日 下午4:33:10
	 * @description 编辑学生信息
	 * @param studentVo
	 * @param stuEntranceDate
	 * @param stuBirth
	 * @param facultyId
	 * @param majorId
	 */
	void edit(StudentVo studentVo, String stuEntranceDate, String stuBirth, Integer facultyId, Integer majorId);

}
