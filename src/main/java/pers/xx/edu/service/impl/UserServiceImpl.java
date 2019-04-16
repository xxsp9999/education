package pers.xx.edu.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.dao.UserDao;
import pers.xx.edu.entity.Instructor;
import pers.xx.edu.entity.Leader;
import pers.xx.edu.entity.Manager;
import pers.xx.edu.entity.Role;
import pers.xx.edu.entity.Student;
import pers.xx.edu.entity.Teacher;
import pers.xx.edu.entity.User;
import pers.xx.edu.enu.LoginType;
import pers.xx.edu.service.InstructorService;
import pers.xx.edu.service.LeaderService;
import pers.xx.edu.service.ManagerService;
import pers.xx.edu.service.RoleService;
import pers.xx.edu.service.StudentService;
import pers.xx.edu.service.TeacherService;
import pers.xx.edu.service.UserService;
import pers.xx.edu.utils.LoginInfo;
import pers.xx.edu.utils.LoginStatus;
import pers.xx.edu.utils.StringUtils;

@Transactional
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Autowired
	private StudentService studentService;

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private ManagerService managerService;

	@Autowired
	private LeaderService LeaderService;

	@Autowired
	private InstructorService InstructorService;

	@Autowired
	private RoleService roleService;

	@Resource(name = "userDao")
	protected void setBaseDao(BaseDao<User> baseDao) {
		super.setBaseDao(baseDao);
	}

	protected UserDao getBaseDao() {
		return (UserDao) super.getBaseDao();
	}

	@Override
	public User login(String code, String password) {
		return this.getBaseDao().login(code, password);
	}

	@Override
	public int isHaveUser(User user) {
		return this.getBaseDao().isHaveUser(user);
	}

	@Override
	public User getByLoginName(String loginName) {
		return this.getBaseDao().getByLoginName(loginName);
	}

	@Override
	public LoginStatus validateLogin(String number, String password, String validateCode, HttpServletRequest request) {
		LoginInfo loginInfo = new LoginInfo();
		String trueCode = (String) request.getSession().getAttribute("imgCode");// 获取验证码
		if (validateCode == null || !(validateCode.toLowerCase()).equals(trueCode.toLowerCase())) {// 验证码判断
			return new LoginStatus(false, "验证码错误！");
		}
		// 用户名或密码非空判断
		if (number == null || number.equals("") || password == null || password.equals("")) {
			return new LoginStatus(false, "用户名或密码不能为空！");
		}
		int count = 0;

		// 查询超级管理员表
		User user = login(number, StringUtils.StringToMd5(password));// 账号查询
		if (user != null) {
			request.getSession().setAttribute("userInfo", user);
			loginInfo.setUserId(user.getId());
			loginInfo.setUserName(user.getName());
			Role role = roleService.getById(LoginType.USER.getTypeId());
			loginInfo.setUserRole(role);
			request.getSession().setAttribute("loginInfo", loginInfo);
			count++;
			return new LoginStatus(true, "登陆成功", LoginType.USER.getTypeId());
		}

		//从教师表查找
		Teacher teacher = teacherService.login(number, StringUtils.StringToMd5(password));// 账号查询
		if (teacher != null) {
			request.getSession().setAttribute("teacherInfo", teacher);
			loginInfo.setUserId(teacher.getId());
			loginInfo.setUserName(teacher.getTeaName());
			Role role = roleService.getById(LoginType.TEACHER.getTypeId());
			loginInfo.setUserRole(role);
			request.getSession().setAttribute("loginInfo", loginInfo);
			count++;
			return new LoginStatus(true, "登陆成功", LoginType.TEACHER.getTypeId());
		}

		//从辅导员表查找
		Instructor instructor = InstructorService.login(number, StringUtils.StringToMd5(password));// 账号查询
		if (instructor != null) {
			request.getSession().setAttribute("instructorInfo", instructor);
			loginInfo.setUserId(instructor.getId());
			loginInfo.setUserName(instructor.getInstructorName());
			Role role = roleService.getById(LoginType.INSTRUCTOR.getTypeId());
			loginInfo.setUserRole(role);
			request.getSession().setAttribute("loginInfo", loginInfo);
			count++;
			return new LoginStatus(true, "登陆成功", LoginType.INSTRUCTOR.getTypeId());
		}

		//从领导表查找
		Leader leader = LeaderService.login(number, StringUtils.StringToMd5(password));// 账号查询
		if (leader != null) {
			request.getSession().setAttribute("leaderInfo", leader);
			loginInfo.setUserId(leader.getId());
			loginInfo.setUserName(leader.getLeaderName());
			Role role = roleService.getById(LoginType.LEADER.getTypeId());
			loginInfo.setUserRole(role);
			request.getSession().setAttribute("loginInfo", loginInfo);
			count++;
			return new LoginStatus(true, "登陆成功", LoginType.LEADER.getTypeId());
		}

		//从管理员表查找
		Manager manager = managerService.login(number, StringUtils.StringToMd5(password));// 账号查询
		if (manager != null) {
			request.getSession().setAttribute("managerInfo", manager);
			loginInfo.setUserId(manager.getId());
			loginInfo.setUserName(manager.getManagerName());
			Role role = roleService.getById(LoginType.MANAGER.getTypeId());
			loginInfo.setUserRole(role);
			request.getSession().setAttribute("loginInfo", loginInfo);
			count++;
			return new LoginStatus(true, "登陆成功", LoginType.MANAGER.getTypeId());
		}

		// 从学生表查找
		Student student = studentService.login(number, StringUtils.StringToMd5(password));// 账号查询
		if (student != null) {
			request.getSession().setAttribute("studentInfo", student);
			loginInfo.setUserId(student.getId());
			loginInfo.setUserName(student.getStuName());
			Role role = roleService.getById(LoginType.STUDENT.getTypeId());
			loginInfo.setUserRole(role);
			request.getSession().setAttribute("loginInfo", loginInfo);
			count++;
			return new LoginStatus(true, "登陆成功", LoginType.STUDENT.getTypeId());
		}
		
		if(count == 0) {
			return new LoginStatus(false, "用户名或密码错误");
		}
		return null;
	}

}
