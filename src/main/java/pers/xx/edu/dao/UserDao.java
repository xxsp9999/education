package pers.xx.edu.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import pers.xx.edu.entity.User;

/**
 * 
 * @description 用户的数据操作层 
 * @author 白贵才
 * @create 2017-9-14下午7:12:34
 *
 */
@Repository
public class UserDao extends BaseDao<User>{
	
	@Override
	public Session getSession() {
		return super.getSession();
	}

	/**
	 * @description 登录
	 * @author 白贵才
	 * @param userCode
	 * @param userPassword
	 * @return
	 */
	public User login(String userPhone, String userPassword) {
		String hql = "from User as u where u.number = ? and u.password = ? and state = 1 order by id";
		Query query = getSession().createQuery(hql);
		query.setString(0, userPhone);
		query.setString(1, userPassword);
		if(query.list().size()<=0){
			return null;
		}
		return (User) query.list().get(0);
	}
	
	/**
	 * @descripion 判断用户是否存在还是密码错误
	 * @param user
	 * @return
	 */
	public int isHaveUser(User user) {
		String hql = "from User as u where u.number = ? order by id";
		Query query = getSession().createQuery(hql);
		query.setString(0, user.getNumber());
		User user2 = (User) query.uniqueResult();
		if(user2==null){
			return 1;//返回1时为用户不存在
		}
		if(!(user.getPassword()).equals(user2.getPassword())){
			return 2;//返回2时为密码错误
		}
		if(user2.getState()==-1){
			return 3;//返回3时为账号停用
		}
		return 0;//正确
	}
	
	/**
	 * 根据用户名获取User对象
	 */
	public User getByLoginName(String loginName) {
		String hql = "from User u where u.number = :number";
		Query query = getSession().createQuery(hql);
		query.setParameter("number", loginName);
		List<User> list = query.list();
		return list.size() > 0 ? list.get(0) : null;
	}

}
