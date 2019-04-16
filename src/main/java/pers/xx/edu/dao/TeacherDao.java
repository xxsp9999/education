package pers.xx.edu.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import pers.xx.edu.entity.Teacher;

/**
 * @author XieXing
 * @description 老师DAO
 * @create 2019年3月17日 下午6:24:34
 */
@Repository
public class TeacherDao extends BaseDao<Teacher>{
	
	/**
	 * @author XieXing
	 * @createDate 2019年4月15日 上午9:25:12
	 * @description 教师登陆
	 * @param code
	 * @param userPassword
	 * @return
	 */
	public Teacher login(String code, String userPassword) {
		String hql = "from Teacher where teaNumber = ? and teaPassword = ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, code);
		query.setString(1, userPassword);
		if(query.list().size()==0){
			return null;
		}
		return (Teacher) query.list().get(0);
	}

}
