package pers.xx.edu.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import pers.xx.edu.entity.Student;

/**
 * @author XieXing
 * @description 学生DAO
 * @create 2019年3月17日 下午6:23:29
 */
@Repository
public class StudentDao extends BaseDao<Student>{
	
	/**
	 * @author XieXing
	 * @createDate 2019年4月15日 上午9:26:27
	 * @description 学生登陆
	 * @param code
	 * @param userPassword
	 * @return
	 */
	public Student login(String code, String userPassword) {
		String hql = "from Student where stuNumber = ? and stuPassword = ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, code);
		query.setString(1, userPassword);
		if(query.list().size()==0){
			return null;
		}
		return (Student) query.list().get(0);
	}

}
