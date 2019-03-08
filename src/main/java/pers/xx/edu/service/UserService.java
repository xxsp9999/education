package pers.xx.edu.service;

import pers.xx.edu.entity.User;

public interface UserService extends BaseService<User> {

	User login(String code,String password);
	
	int isHaveUser(User user);
	
	User getByLoginName(String loginName);
}
