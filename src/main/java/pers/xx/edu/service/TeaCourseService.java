package pers.xx.edu.service;

import java.util.List;

import pers.xx.edu.entity.TeaCourse;
import pers.xx.edu.vo.SelectedCourseVo;

/**
 * @author XieXing
 * @createDate 2019年5月14日 下午2:39:16
 * @description 老师-课程接口
 */
public interface TeaCourseService extends BaseService<TeaCourse>{
	
	/**
	 * @author XieXing
	 * @createDate 2019年5月15日 上午10:07:01
	 * @description 查看该老师当前时间端是否已被安排该课程
	 * @param courseId
	 * @param teacherId
	 * @param tcSeason
	 * @param tcYear
	 * @return
	 */
	public TeaCourse isScheduled(Integer courseId,Integer teacherId,String tcSeason);

	/**
	 * @author XieXing
	 * @createDate 2019年5月16日 下午5:50:27
	 * @description 根据老师获取课程名
	 * @param teacherId
	 * @return
	 */
	public List<SelectedCourseVo> getByTeacherId(Integer teacherId);
}
