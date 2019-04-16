package pers.xx.edu.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import pers.xx.edu.entity.Leader;

/**
 * @author XieXing
 * @description 领导DAO
 * @create 2019年3月17日 下午6:27:29
 */
@Repository
public class LeaderDao extends BaseDao<Leader>{

	/**
	 * @author XieXing
	 * @createDate 2019年4月15日 上午9:28:47
	 * @description 管理员登陆
	 * @param code
	 * @param userPassword
	 * @return
	 */
	public Leader login(String code, String userPassword) {
		String hql = "from Leader where leaderNumber = ? and leaderPassword = ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, code);
		query.setString(1, userPassword);
		if(query.list().size()==0){
			return null;
		}
		return (Leader) query.list().get(0);
	}
}
