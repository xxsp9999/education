package pers.xx.edu.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import pers.xx.edu.entity.Student;
import pers.xx.edu.vo.EChartsVo2;

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
	
	/**
	 * @author XieXing
	 * @createDate 2019年4月30日 上午11:06:55
	 * @description 根据性别获取数据
	 * @param sex
	 * @return
	 */
	public Integer getBySex(String sex) {
		String hql = "select count(*) from Student where stuSex = ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, sex);
		if(query.list().size()==0){
			return 0;
		}
		return Integer.valueOf( query.list().get(0)+"");
	}
	
	
	/**
	 * @author XieXing
	 * @createDate 2019年5月7日 下午9:35:12
	 * @description 获取学生所在城市的数量
	 * @return
	 */
	public List<EChartsVo2> getStudentMapData(){
		String hql = "select c.cityName,count(*) from Student s ,MyCity c where s.stuCity = c.id group by c.cityName";
		Query query = getSession().createQuery(hql);
		return query.list();
	}

}
