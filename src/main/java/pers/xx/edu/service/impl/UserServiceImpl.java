package pers.xx.edu.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.dao.UserDao;
import pers.xx.edu.entity.User;
import pers.xx.edu.service.UserService;

@Transactional
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Resource(name = "userDao")
	protected void setBaseDao(BaseDao<User> baseDao) {
		super.setBaseDao(baseDao);
	}

	protected UserDao getBaseDao() {
		return (UserDao) super.getBaseDao();
	}

	@Override
	public User login(String code, String password) {
		return this.getBaseDao().login(code,password);
	}

	@Override
	public int isHaveUser(User user) {
		return this.getBaseDao().isHaveUser(user);
	}

	@Override
	public User getByLoginName(String loginName) {
		return this.getBaseDao().getByLoginName(loginName);
	}


}
