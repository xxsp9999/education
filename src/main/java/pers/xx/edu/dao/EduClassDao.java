package pers.xx.edu.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import pers.xx.edu.entity.EduClass;

/**
 * @author XieXing
 * @createDate 2019年4月19日 下午4:14:06
 * @description 班级DAO类
 */
@Repository
public class EduClassDao extends BaseDao<EduClass>{
	
	/**
	 * @author XieXing
	 * @createDate 2019年5月4日 下午6:26:48
	 * @description 根据班级名称前缀查班级
	 * @param str
	 * @return
	 */
	public List<EduClass> getByLikeContent(String str){
		String hql = "from EduClass where claName like ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, str+"%");
		return query.list();
	}
	
	/**
	 * @author XieXing
	 * @createDate 2019年5月6日 上午11:13:32
	 * @description 根据学院获取班级
	 * @param facId
	 * @return
	 */
	public List<EduClass> getByFacId(Integer facId){
		String hql = "from EduClass where claFaculty = ?";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, facId);
		return query.list();
	}
	
	/**
	 * @author XieXing
	 * @createDate 2019年5月6日 上午11:13:21
	 * @description 根据专业获取班级
	 * @param majId
	 * @return
	 */
	public List<EduClass> getByMajId(Integer majId){
		String hql = "from EduClass where claMajor = ?";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, majId);
		return query.list();
	}
}
