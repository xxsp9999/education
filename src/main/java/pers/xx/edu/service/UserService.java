package pers.xx.edu.service;

import javax.servlet.http.HttpServletRequest;

import pers.xx.edu.entity.User;
import pers.xx.edu.utils.LoginStatus;

public interface UserService extends BaseService<User> {

	User login(String code, String password);

	int isHaveUser(User user);

	User getByLoginName(String loginName);

	/**
	 * @author XieXing
	 * @createDate 2019年4月15日 上午9:52:19
	 * @description 登陆验证
	 * @param number
	 * @param password
	 * @param request
	 * @param validateCode
	 * @return
	 */
	LoginStatus validateLogin(String number, String password, String validateCode, HttpServletRequest request);
}
