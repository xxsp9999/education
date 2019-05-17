package pers.xx.edu.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import pers.xx.edu.entity.MyCounty;

/**
 * @author XieXing
 * @createDate 2019年5月5日 上午9:22:49
 * @description 县（三级地名）DAO
 */
@Repository
public class MyCountyDao extends BaseDao<MyCounty>{

	/**
	 * @author XieXing
	 * @createDate 2019年5月5日 上午9:41:12
	 * @description 根据第二级地址获取第三级地址
	 * @param cid
	 * @return
	 */
	public List<MyCounty> getByCityId(Integer cid) {
		String hql = "from MyCounty where cityId.id = ?";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, cid);
		return query.list();
	}

}
