package pers.xx.edu.service;

import pers.xx.edu.entity.StuCourse;

/**
 * @author XieXing
 * @createDate 2019年5月14日 下午2:38:18
 * @description 学生-课程接口
 */
public interface StuCourseService extends BaseService<StuCourse>{

	/**
	 * @author XieXing
	 * @createDate 2019年5月15日 下午8:56:24
	 * @description 根据学生id和老师-课程id获取学生-课程
	 * @param teaCourseId
	 * @return
	 */
	public StuCourse getByStuIdAndTeaCourseId(Integer stuId,Integer teaCourseId);
	
	
	public void deleteByTeaCourseIds(Integer id);
}
