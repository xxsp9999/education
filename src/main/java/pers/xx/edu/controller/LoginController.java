package pers.xx.edu.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.xx.edu.entity.Module;
import pers.xx.edu.entity.ModuleRole;
import pers.xx.edu.entity.Role;
import pers.xx.edu.service.ModuleRoleService;
import pers.xx.edu.service.UserService;
import pers.xx.edu.utils.LoginInfo;
import pers.xx.edu.utils.LoginStatus;
import pers.xx.edu.vo.ListMenu;

/**
 * @author XieXing
 * @description 登陆
 * @create 2019年2月25日 上午10:37:50
 */
@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModuleRoleService moduleRoleService;

	/**
	 * @author XieXing
	 * @create 2019年2月25日
	 * @description 用户登录验证
	 * @param out
	 * @param session
	 * @param number
	 * @param password
	 * @param request
	 * @param code
	 */
	@ResponseBody
	@RequestMapping(value = "/doLogin", produces = "application/json;charset=utf-8")
	public LoginStatus login(String number, String password, String code, HttpServletRequest request) {
		LoginStatus loginStatus = userService.validateLogin(number, password, code, request);
		return loginStatus;
	}

	/**
	 * @author XieXing
	 * @create 2019年2月25日
	 * @description 跳转到主页
	 * @param session
	 * @param request
	 * @param userType
	 * @return
	 */
	@RequestMapping("/main")
	public String toMain(HttpSession session, HttpServletRequest request) {
		LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
		if (loginInfo == null) {
			return "system/login";
		}
		//角色获取，可能存在多角色
		List<Role> roles = new ArrayList<>();
		roles.add(loginInfo.getUserRole());
		// 3.根据角色获取模块
		List<Module> modules = new ArrayList<Module>();
		if (roles != null && roles.size() > 0) {
			for (Role role : roles) {
				List<ModuleRole> moduleRoles = moduleRoleService.getByRoleId(role.getId());
				if (moduleRoles != null && moduleRoles.size() > 0) {
					for (ModuleRole moduleRole : moduleRoles) {
						// 去重
						if (!modules.contains(moduleRole.getModule())) {
							modules.add(moduleRole.getModule());
						}
					}
				}
			}
		}
		// 4.把模块区分为一级 二级菜单
		List<Module> module = new ArrayList<Module>();// 一级菜单
		List<Module> moduleSub = new ArrayList<Module>();// 二级菜单
		if (modules != null && modules.size() > 0) {
			for (Module mod : modules) {
				switch (Integer.valueOf(mod.getLevel())) {
				case 0:
					module.add(mod);
					break;
				case 1:
					moduleSub.add(mod);
					break;
				default:
					System.out.println("模块分配出错");
					break;
				}
			}
		}
		// 封装菜单
		List<ListMenu> menus = new ArrayList<ListMenu>();
		if (module != null) {
			for (Module mod : module) {
				// 封装子菜单
				List<Module> subMenu = new ArrayList<Module>();
				if (mod != null && moduleSub != null) {
					for (Module modSub : moduleSub) {
						if (modSub != null && modSub.getParent() != null && modSub.getParent().getId() == mod.getId()) {
							subMenu.add(modSub);// 添加到子菜单列表
							// moduleSub.remove(modSub);//移除已经添加的菜单，减少循环次数
						}
					}
				}
				ListMenu menu = new ListMenu(mod, subMenu);
				menus.add(menu);
			}
		}
		// 对父级模块进行排序
		Collections.sort(menus, new Comparator<ListMenu>() {
			/*
			 * int compare(ListMenu menu1, ListMenu menu2) 返回一个基本类型的整型， 返回负数表示：menu1 小于
			 * menu2， 返回0 表示：menu1和menu1相等， 返回正数表示：menu1大于menu1
			 */
			public int compare(ListMenu menu1, ListMenu menu2) {
				// 按照Person的年龄进行升序排列
				if (Integer.parseInt(menu1.getModule().getSort()) > Integer.parseInt(menu2.getModule().getSort())) {
					return 1;
				}
				if (Integer.parseInt(menu1.getModule().getSort()) > Integer.parseInt(menu2.getModule().getSort())) {
					return 0;
				}
				return -1;
			}
		});
		request.setAttribute("menus", menus);
		return "system/main";
	}

	/**
	 * @description 跳转到主页
	 * @author XieXing
	 * @createDate 2018-10-10 11:27:17
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/cmain")
	public String tocMain(HttpSession session, HttpServletRequest request, String userType) {
		return "/login";
	}

	/**
	 * @author XieXing
	 * @create 2019年2月26日
	 * @description 退出登陆
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "system/login";
	}
}
