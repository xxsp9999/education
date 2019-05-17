package pers.xx.edu.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import pers.xx.edu.entity.Faculty;

/**
 * @author XieXing
 * @createDate 2019年4月9日 下午3:25:57
 * @description 学院DAO类
 */
@Repository
public class FacultyDao extends BaseDao<Faculty>{
	/**
	 * @author XieXing
	 * @createDate 2019年4月11日 下午2:07:09
	 * @description 根据学院代码获取学院信息
	 * @param facNumber 学院代码
	 * @return
	 */
	public Faculty getFacultyByfacNumber(String facNumber){
		String hql = "from Faculty where facNumber = ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, facNumber);
		List<Faculty> faculties= query.list();
		return faculties.size()>0?faculties.get(0):null;
	}
}
