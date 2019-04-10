package pers.xx.edu.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.xx.edu.entity.Department;
import pers.xx.edu.entity.DepartmentRole;
import pers.xx.edu.entity.LoginNum;
import pers.xx.edu.entity.Module;
import pers.xx.edu.entity.ModuleRole;
import pers.xx.edu.entity.Role;
import pers.xx.edu.entity.User;
import pers.xx.edu.enu.LoginType;
import pers.xx.edu.service.DepartmentRoleService;
import pers.xx.edu.service.LoginNumService;
import pers.xx.edu.service.ModuleRoleService;
import pers.xx.edu.service.UserService;
import pers.xx.edu.support.CustomizedToken;
import pers.xx.edu.utils.LoginStatus;
import pers.xx.edu.utils.StringUtils;
import pers.xx.edu.vo.ListMenu;

/**
 * @author XieXing
 * @description 登陆
 * @create 2019年2月25日 上午10:37:50
 */
@Controller
public class LoginController {

	// 管理员登录用户类型枚举
	private static final String USER_LOGIN_TYPE = LoginType.USER.toString();

	@Autowired
	private UserService userService;

	@Autowired
	private DepartmentRoleService departmentRoleService;

	@Autowired
	private ModuleRoleService moduleRoleService;

	@Autowired
	private LoginNumService loginNumService;

	/**
	 * @author XieXing
	 * @create 2019年2月25日
	 * @description 用户登录
	 * @param out
	 * @param session
	 * @param number
	 * @param password
	 * @param request
	 * @param code
	 */
	@ResponseBody
	@RequestMapping(value = "/doLogin", produces = "application/json;charset=utf-8")
	public LoginStatus login(HttpSession session, String number,
			String password, HttpServletRequest request, String code) {
		User user = null;
		LoginNum loginnum = new LoginNum();
		String trueCode = (java.lang.String) request.getSession()
				.getAttribute("imgCode");// 获取验证码
		String remoteHost = request.getRemoteHost();
		if (remoteHost.equals("0:0:0:0:0:0:0:1")) {// ip获取
			remoteHost = "127.0.0.1";
		}
		if (code != null && trueCode != null
				&& (code.toLowerCase()).equals(trueCode.toLowerCase())) {// 验证码判断
			if (number != null && !number.equals("") && password != null
					&& !password.equals("")) {// 账号，用户名非空判断
				// 1、先从管理员表查询此用户
				user = userService.login(number,
						StringUtils.StringToMd5(password));// 账号查询
				if (user != null) {// 成功
					Date d = new Date();
					int logincount = user.getTotalLoginNum() + 1;
					user.setTotalLoginNum(logincount);
					userService.update(user);
					loginnum.setName(user.getName());
					loginnum.setNumber(user.getNumber());
					loginnum.setTotalLoginNum(logincount);
					loginnum.setIpAddress(remoteHost);
					if (user.getDepartment().getName() != null) {
						loginnum.setDepartment(user.getDepartment().getName());
					} else {
						loginnum.setDepartment("");
					}
					loginnum.setLoginTime(d);
					loginNumService.saveOrUpdate(loginnum);
					//session.setAttribute("user", user);
					request.getSession().setAttribute("user", user);
					return new LoginStatus(true, "登陆成功", 1);
				} else { // 查找失败
					return new LoginStatus(false, "用户名或密码错误");
				}
			} else {
				return new LoginStatus(false, "用户名或密码不能为空！");
			}
		} else {
			return new LoginStatus(false, "验证码错误！");
		}
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
	public String toMain(HttpSession session, HttpServletRequest request,
			String userType) {

		User user = (User) session.getAttribute("user");

		if (user == null) {
			return "system/login";
		}
		Subject subject = SecurityUtils.getSubject();

		CustomizedToken token = new CustomizedToken(user.getNumber(),
				user.getPassword().toString(), USER_LOGIN_TYPE);
		try {
			token.setRememberMe(true);
			subject.login(token);
		} catch (UnknownAccountException uae) {
			throw new UnknownAccountException("账号不存在");
		} catch (IncorrectCredentialsException ice) {
			throw new IncorrectCredentialsException("密码错误");
		} catch (LockedAccountException lae) {
			throw new LockedAccountException("账号被锁定");
		} catch (ExcessiveAttemptsException eae) {
			throw new ExcessiveAttemptsException("登录次数过多");
		} catch (AuthenticationException ae) {
			throw new AuthenticationException("没有权限访问");
		}
		if (subject.isAuthenticated()) {
			/**
			 * @description 根据部门 -> 角色 -> 模块 去查找
			 * @author 白贵才
			 * @createDate 2017年10月31日15:31:57
			 */

			// 1.先获取部门
			Department department = user.getDepartment();
			if (department == null) {
				request.setAttribute("errorMsg", "身份验证失败,该账号不属于任何一个部门！");
				return "/login";
			}

			// 2.根据部门获取角色
			List<DepartmentRole> departmentRoles = departmentRoleService
					.getByDepartmentId(department.getId());
			List<Role> roles = new ArrayList<Role>();
			if (departmentRoles != null && departmentRoles.size() > 0) {
				for (DepartmentRole departmentRole : departmentRoles) {
					// 去重
					if (!roles.contains(departmentRole.getRole())) {
						roles.add(departmentRole.getRole());
					}
				}
			}

			// 3.根据角色获取模块
			List<Module> modules = new ArrayList<Module>();
			if (roles != null && roles.size() > 0) {
				for (Role role : roles) {
					List<ModuleRole> moduleRoles = moduleRoleService
							.getByRoleId(role.getId());
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
						case 0 :
							module.add(mod);
							break;
						case 1 :
							moduleSub.add(mod);
							break;
						default :
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
							if (modSub != null && modSub.getParent() != null
									&& modSub.getParent().getId() == mod
											.getId()) {
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
				 * int compare(ListMenu menu1, ListMenu menu2) 返回一个基本类型的整型，
				 * 返回负数表示：menu1 小于 menu2， 返回0 表示：menu1和menu1相等，
				 * 返回正数表示：menu1大于menu1
				 */
				public int compare(ListMenu menu1, ListMenu menu2) {
					// 按照Person的年龄进行升序排列
					if (Integer.parseInt(menu1.getModule().getSort()) > Integer
							.parseInt(menu2.getModule().getSort())) {
						return 1;
					}
					if (Integer.parseInt(menu1.getModule().getSort()) > Integer
							.parseInt(menu2.getModule().getSort())) {
						return 0;
					}
					return -1;
				}
			});

			request.setAttribute("menus", menus);
			request.setAttribute("user", user);
			return "system/main";
		}

		return "system/main";
	}

	/**
	 * @description 跳转到主页
	 * @author lichanghua
	 * @createDate 2017-10-10 11:27:17
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/cmain")
	public String tocMain(HttpSession session, HttpServletRequest request,
			String userType) {
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
