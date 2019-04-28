package pers.xx.edu.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.xx.edu.entity.User;
import pers.xx.edu.service.UserService;
import pers.xx.edu.utils.LoginInfo;
import pers.xx.edu.utils.PersonUtils;
import pers.xx.edu.utils.ResponseInfo;



@Controller
@RequestMapping("/person")
public class PersonalController {

	@Autowired
	private UserService userService;
	
	/**
	 * @author XieXing
	 * @create 2019年3月29日
	 * @description 跳转到个人中心公共页面
	 * @return
	 */
	@RequestMapping("/toPublic")
	public String toPublic() {
		return "personmanage/public";
	}
	
	/**
	 * @author XieXing
	 * @create 2019年3月18日
	 * @description 跳转到个人资料页面
	 * @return
	 */
	@RequestMapping("/toBiMain")
	public String toBiMain(HttpSession session, Map<String, Object> map) {
		LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
		Integer roleId = loginInfo.getUserRole().getId();
		String path = PersonUtils.getPah(roleId);
		return "personmanage/baseinfo/"+path;
	}
	
	@RequestMapping("/toBiAdd")
	public String toBiMainJsp(HttpSession session,Map<String, Object> map,String operate) {
		map.put("operate", operate);//查看标记
		return "personmanage/baseinfo/bimain";
	}
	/**
	 * @author XieXing
	 * @create 2019年3月18日
	 * @description 跳转个人信息修改页面
	 * @param map
	 * @param operate 操作标记
	 * @return
	 */
	@RequestMapping("/updateUser")
	public String updateUser(String number,String name,String phone,String staffJob, HttpSession session){
		User user = (User) session.getAttribute("user");//设置创建者
		user.setNumber(number);//设置账号
		user.setName(name);//设置姓名
		user.setPhone(phone);//设置电话
		user.setStaffJob(staffJob);
		userService.update(user);
		/*session.removeAttribute("user");
		session.setAttribute("user", user);*/
		return "personmanage/baseinfo/bimain";
	}
	
	/**
	 * @author XieXing
	 * @create 2019年3月18日
	 * @description 跳转修改密码页面
	 * @return
	 */
	@RequestMapping("/toPassword")
	public String toPassword(HttpSession session, Map<String, Object> map) {
		return "personmanage/passwordinfo/password";
	}
	/**
	 * @author XieXing
	 * @create 2019年3月18日
	 * @description 根据id修改密码
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="resetPasswords",produces="application/json;charset=utf-8")
	public ResponseInfo resetPasswords(HttpSession session,String password, String newPassword) {	
		return userService.resetPasswords(session, password, newPassword);
	}
	
}
