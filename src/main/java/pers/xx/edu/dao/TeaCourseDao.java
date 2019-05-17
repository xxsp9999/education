package pers.xx.edu.dao;



import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import pers.xx.edu.entity.TeaCourse;
import pers.xx.edu.vo.SelectedCourseVo;

/**
 * @author XieXing
 * @createDate 2019年5月14日 下午2:37:11
 * @description 老师课程DAO
 */
@Repository
public class TeaCourseDao extends BaseDao<TeaCourse>{
	/**
	 * @author XieXing
	 * @createDate 2019年5月15日 上午10:14:18
	 * @description 判断该时间段该老师是否已被安排该课程
	 * @param courseId
	 * @param teacherId
	 * @param tcSeason
	 * @param tcYear
	 * @return
	 */
	public TeaCourse isScheduled(Integer courseId, Integer teacherId, String tcSeason) {
		String hql = "from TeaCourse where tcCourse = ? and tcTeacher = ? and tcSeason = ?";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, courseId);
		query.setInteger(1, teacherId);
		query.setString(2, tcSeason);
		if (query.list().size()==0)
			return null;
		else
			return (TeaCourse) query.list().get(0);
	}
	
	/**
	 * @author XieXing
	 * @createDate 2019年5月16日 下午5:52:19
	 * @description 根据老师获取课程名id
	 * @param teacherId
	 * @return
	 */
	public List<SelectedCourseVo> getByTeacherId(Integer teacherId){
		String hql = "select tc.id,c.cName from TeaCourse tc,Teacher t,Course c where tc.tcTeacher = t.id and tc.tcCourse = c.id";
		Query query = getSession().createQuery(hql);
		return query.list();
	}

}
