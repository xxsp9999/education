package pers.xx.edu.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import pers.xx.edu.entity.User;
import pers.xx.edu.utils.LoginStatus;
import pers.xx.edu.utils.ResponseInfo;

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
	
	
	/**
	 * @author XieXing
	 * @createDate 2019年4月16日 下午3:50:16
	 * @description 修改密码
	 * @param session
	 * @param password
	 * @param newPassword
	 * @return
	 */
	ResponseInfo resetPasswords(HttpSession session,String password, String newPassword);
}
