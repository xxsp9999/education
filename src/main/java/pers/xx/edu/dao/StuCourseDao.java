package pers.xx.edu.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import pers.xx.edu.entity.StuCourse;

/**
 * @author XieXing
 * @createDate 2019年5月14日 下午2:35:51
 * @description 学生课程DAO类
 */
@Repository
public class StuCourseDao extends BaseDao<StuCourse>{

	/**
	 * @author XieXing
	 * @createDate 2019年5月15日 下午8:56:24
	 * @description 根据老师-课程id获取学生-课程
	 * @param teaCourseId
	 * @return
	 */
	public StuCourse getByTeaCourseId(Integer stuId,Integer teaCourseId) {
		String hql = "from StuCourse where scStudent = ? and scTeaCourse = ?";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, stuId);
		query.setInteger(1, teaCourseId);
		if(query.list().size()==0) {
			return null;
		}else {
			return (StuCourse) query.list().get(0);
		}
	}
	
	/**
	 * @author XieXing
	 * @createDate 2019年5月16日 上午9:47:06
	 * @description 根据老师-课程id删除学生所课程
	 * @param teaCourseIds
	 */
	public void deleteByTeaCourseIds(Integer id) {
		String hql = "delete from StuCourse where scTeaCourse = ?)";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, id);
		query.executeUpdate();
	}
}
