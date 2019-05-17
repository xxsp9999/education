package pers.xx.edu.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import pers.xx.edu.entity.Leave;

/**
 * @author XieXing
 * @createDate 2019年4月18日 上午10:09:12
 * @description 请假DAO类
 */
@Repository
public class LeaveDao extends BaseDao<Leave>{
	
	/**
	 * @author XieXing
	 * @createDate 2019年4月24日 下午2:34:51
	 * @description 根据流程实例id获取请假单
	 * @param processInstanceId
	 * @return
	 */
	public Leave getByProcessInstanceId(String processInstanceId) {
		String hql = "from Leave where processInstanceId = ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, processInstanceId);
		@SuppressWarnings("rawtypes")
		List list = query.list();
		if(list!=null&&list.size()>0) {
			return (Leave) list.get(0);
		}
		return null;
	}

}
