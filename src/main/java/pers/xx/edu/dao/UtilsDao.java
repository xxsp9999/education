package pers.xx.edu.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 
 * @description 工具Dao 
 * @author 白贵才
 * @create 2017-10-19下午3:36:08
 *
 */
@Repository
public class UtilsDao {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * @description 查询UUID是否已经在数据库
	 * @author 白贵才
	 * @createDate 2017年10月19日15:37:41 
	 * @param table
	 * @param column
	 * @return
	 */
	public boolean isExistUUID(String entity, String column, String value) {
		StringBuffer hql = new StringBuffer("from ").append(entity).append(" where ").append(column).append(" = '").append(value).append("'");
		int size = sessionFactory.getCurrentSession().createQuery(hql.toString()).list().size();
		if(size>0){
			return true;
		}else{
			return false;
		}
	}
	
	
}
