package pers.xx.edu.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.dao.UserDao;
import pers.xx.edu.entity.Company;
import pers.xx.edu.entity.Instructor;
import pers.xx.edu.entity.Leader;
import pers.xx.edu.entity.Manager;
import pers.xx.edu.entity.Role;
import pers.xx.edu.entity.Student;
import pers.xx.edu.entity.Teacher;
import pers.xx.edu.entity.User;
import pers.xx.edu.enu.LoginType;
import pers.xx.edu.service.CompanyService;
import pers.xx.edu.service.InstructorService;
import pers.xx.edu.service.LeaderService;
import pers.xx.edu.service.ManagerService;
import pers.xx.edu.service.RoleService;
import pers.xx.edu.service.StudentService;
import pers.xx.edu.service.TeacherService;
import pers.xx.edu.service.UserService;
import pers.xx.edu.utils.LoginInfo;
import pers.xx.edu.utils.LoginStatus;
import pers.xx.edu.utils.ResponseInfo;
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
	
	@Autowired
	private CompanyService companyService;
	

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
			request.getSession().setAttribute("user", user);
			loginInfo.setUserId(user.getId());
			loginInfo.setUserName(user.getName());
			Role role = roleService.getById(LoginType.USER.getTypeId());
			loginInfo.setUserRole(role);
			request.getSession().setAttribute("loginInfo", loginInfo);
			count++;
			return new LoginStatus(true, "登陆成功", LoginType.USER.getTypeId());
		}

		// 从教师表查找
		Teacher teacher = teacherService.login(number, StringUtils.StringToMd5(password));// 账号查询
		if (teacher != null) {
			request.getSession().setAttribute("teacher", teacher);
			loginInfo.setUserId(teacher.getId());
			loginInfo.setUserName(teacher.getTeaName());
			loginInfo.setImg(teacher.getTeaImg());
			Role role = roleService.getById(LoginType.TEACHER.getTypeId());
			loginInfo.setUserRole(role);
			request.getSession().setAttribute("loginInfo", loginInfo);
			count++;
			return new LoginStatus(true, "登陆成功", LoginType.TEACHER.getTypeId());
		}

		// 从辅导员表查找
		Instructor instructor = InstructorService.login(number, StringUtils.StringToMd5(password));// 账号查询
		if (instructor != null) {
			request.getSession().setAttribute("instructor", instructor);
			loginInfo.setUserId(instructor.getId());
			loginInfo.setUserName(instructor.getInstructorName());
			loginInfo.setImg(instructor.getInstructorImg());
			Role role = roleService.getById(LoginType.INSTRUCTOR.getTypeId());
			loginInfo.setUserRole(role);
			request.getSession().setAttribute("loginInfo", loginInfo);
			count++;
			return new LoginStatus(true, "登陆成功", LoginType.INSTRUCTOR.getTypeId());
		}

		// 从领导表查找
		Leader leader = LeaderService.login(number, StringUtils.StringToMd5(password));// 账号查询
		if (leader != null) {
			request.getSession().setAttribute("leader", leader);
			loginInfo.setUserId(leader.getId());
			loginInfo.setUserName(leader.getLeaderName());
			loginInfo.setImg(leader.getLeaderImg());
			Role role = roleService.getById(LoginType.LEADER.getTypeId());
			loginInfo.setUserRole(role);
			request.getSession().setAttribute("loginInfo", loginInfo);
			count++;
			return new LoginStatus(true, "登陆成功", LoginType.LEADER.getTypeId());
		}

		// 从管理员表查找
		Manager manager = managerService.login(number, StringUtils.StringToMd5(password));// 账号查询
		if (manager != null) {
			request.getSession().setAttribute("manager", manager);
			loginInfo.setUserId(manager.getId());
			loginInfo.setUserName(manager.getManagerName());
			loginInfo.setImg(manager.getManagerImg());
			Role role = roleService.getById(LoginType.MANAGER.getTypeId());
			loginInfo.setUserRole(role);
			request.getSession().setAttribute("loginInfo", loginInfo);
			count++;
			return new LoginStatus(true, "登陆成功", LoginType.MANAGER.getTypeId());
		}

		// 从学生表查找
		Student student = studentService.login(number, StringUtils.StringToMd5(password));// 账号查询
		if (student != null) {
			request.getSession().setAttribute("student", student);
			loginInfo.setUserId(student.getId());
			loginInfo.setUserName(student.getStuName());
			loginInfo.setImg(student.getStuImg());
			Role role = roleService.getById(LoginType.STUDENT.getTypeId());
			loginInfo.setUserRole(role);
			request.getSession().setAttribute("loginInfo", loginInfo);
			count++;
			return new LoginStatus(true, "登陆成功", LoginType.STUDENT.getTypeId());
		}
		
		//从公司账户查询
		Company company = companyService.login(number, StringUtils.StringToMd5(password));// 账号查询
		if (company != null) {
			request.getSession().setAttribute("company", company);
			loginInfo.setUserId(company.getId());
			loginInfo.setUserName(company.getCompanyName());
			loginInfo.setImg(company.getCompanyLogo());
			Role role = roleService.getById(LoginType.COMPANY.getTypeId());
			loginInfo.setUserRole(role);
			request.getSession().setAttribute("loginInfo", loginInfo);
			count++;
			return new LoginStatus(true, "登陆成功", LoginType.COMPANY.getTypeId());
		}

		if (count == 0) {
			return new LoginStatus(false, "用户名或密码错误");
		}
		return null;
	}

	@Override
	public ResponseInfo resetPasswords(HttpSession session, String password, String newPassword) {
		LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
		Integer roleId = loginInfo.getUserRole().getId();
		if (roleId == 1) {
			User user = (User) session.getAttribute("user");// 设置创建者
			if (StringUtils.StringToMd5(password).equals(user.getPassword())) {
				user.setPassword(StringUtils.StringToMd5(newPassword));// 重置密码
				update(user);
				return new ResponseInfo(true, "修改成功");
			} else
				return new ResponseInfo(false, "原密码输入错误！");
		}
		if (roleId == 2) {
			Student student = (Student) session.getAttribute("student");
			if (StringUtils.StringToMd5(password).equals(student.getStuPassword())) {
				student.setStuPassword(StringUtils.StringToMd5(newPassword));// 重置密码
				studentService.update(student);
				return new ResponseInfo(true, "修改成功");
			} else
				return new ResponseInfo(false, "原密码输入错误！");
		}
		if (roleId == 3) {
			Teacher teacher = (Teacher) session.getAttribute("teacher");
			if (StringUtils.StringToMd5(password).equals(teacher.getTeaPassword())) {
				teacher.setTeaPassword(StringUtils.StringToMd5(newPassword));// 重置密码
				teacherService.update(teacher);
				return new ResponseInfo(true, "修改成功");
			} else
				return new ResponseInfo(false, "原密码输入错误！");
		}
		if (roleId == 4) {
			Instructor instructor = (Instructor) session.getAttribute("instructor");
			if (StringUtils.StringToMd5(password).equals(instructor.getInstructorPassword())) {
				instructor.setInstructorPassword(StringUtils.StringToMd5(newPassword));// 重置密码
				InstructorService.update(instructor);
				return new ResponseInfo(true, "修改成功");
			} else
				return new ResponseInfo(false, "原密码输入错误！");
		}

		if (roleId == 5) {
			Leader leader = (Leader) session.getAttribute("leader");
			if (StringUtils.StringToMd5(password).equals(leader.getLeaderPassword())) {
				leader.setLeaderPassword(StringUtils.StringToMd5(newPassword));// 重置密码
				LeaderService.update(leader);
				return new ResponseInfo(true, "修改成功");
			} else
				return new ResponseInfo(false, "原密码输入错误！");
		}
		if (roleId == 6) {
			Manager manager = (Manager) session.getAttribute("manager");
			if (StringUtils.StringToMd5(password).equals(manager.getManagerPassword())) {
				manager.setManagerPassword(StringUtils.StringToMd5(newPassword));// 重置密码
				managerService.update(manager);
				return new ResponseInfo(true, "修改成功");
			} else
				return new ResponseInfo(false, "原密码输入错误！");
		}
		if (roleId == 7) {
			Company company = (Company) session.getAttribute("company");
			if (StringUtils.StringToMd5(password).equals(company.getCompanyPassword())) {
				company.setCompanyPassword(StringUtils.StringToMd5(newPassword));// 重置密码
				companyService.update(company);
				return new ResponseInfo(true, "修改成功");
			} else
				return new ResponseInfo(false, "原密码输入错误！");
		}

		return null;
	}

}
