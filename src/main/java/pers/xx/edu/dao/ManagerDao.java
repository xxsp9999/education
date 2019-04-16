package pers.xx.edu.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import pers.xx.edu.entity.Manager;

/**
 * @author XieXing
 * @description 管理员DAO
 * @create 2019年3月17日 下午6:28:07
 */
@Repository
public class ManagerDao extends BaseDao<Manager>{
	/**
	 * @author XieXing
	 * @createDate 2019年4月15日 上午9:27:37
	 * @description 管理员登陆
	 * @param code
	 * @param userPassword
	 * @return
	 */
	public Manager login(String code, String userPassword) {
		String hql = "from Manager where managerNumber = ? and managerPassword = ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, code);
		query.setString(1, userPassword);
		if(query.list().size()==0){
			return null;
		}
		return (Manager) query.list().get(0);
	}
	
}
