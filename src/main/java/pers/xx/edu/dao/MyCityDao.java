package pers.xx.edu.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import pers.xx.edu.entity.MyCity;

/**
 * @author XieXing
 * @createDate 2019年5月5日 上午9:21:45
 * @description 市DAO
 */
@Repository
public class MyCityDao extends BaseDao<MyCity>{

	/**
	 * @author XieXing
	 * @createDate 2019年5月5日 上午9:40:37
	 * @description 根据省份id获取第二级地址
	 * @param pid
	 * @return
	 */
	public List<MyCity> getByPid(Integer pid) {
		String hql = "from MyCity where pid.id = ?";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, pid);
		return query.list();
	}

}
