package pers.xx.edu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import pers.xx.edu.entity.Department;
import pers.xx.edu.entity.DepartmentRole;
import pers.xx.edu.entity.LoginNum;
import pers.xx.edu.entity.Module;
import pers.xx.edu.entity.ModuleRole;
import pers.xx.edu.entity.Params;
import pers.xx.edu.entity.Role;
import pers.xx.edu.entity.User;
import pers.xx.edu.service.DepartmentRoleService;
import pers.xx.edu.service.DepartmentService;
import pers.xx.edu.service.LoginNumService;
import pers.xx.edu.service.ModuleRoleService;
import pers.xx.edu.service.ModuleService;
import pers.xx.edu.service.ParamsService;
import pers.xx.edu.service.RoleService;
import pers.xx.edu.service.UserService;
import pers.xx.edu.utils.JsonUtils;
import pers.xx.edu.utils.Page;
import pers.xx.edu.vo.DepartmentVo;
import pers.xx.edu.vo.ModuleVo;
import pers.xx.edu.vo.ParamsVo;
import pers.xx.edu.vo.RoleVo;
import pers.xx.edu.vo.TreeNode;

/**
 * @author XieXing
 * @description 权限管理Controller
 * @create 2019年3月15日 下午4:40:24
 */
@RequestMapping("/role")
@Controller
public class RoleController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private ModuleService moduleService;

	@Autowired
	private ModuleRoleService moduleRoleService;

	@Autowired
	private DepartmentRoleService departmentRoleService;

	@Autowired
	private ParamsService paramsService;

	@Autowired
	private UserService userService;

	@Autowired
	private LoginNumService loginNumService;

	private static Date currentDate = new Date();// 当前时间

	/**
	 * @description 跳往角色页面
	 * @author 白贵才
	 * @create 2017年10月23日12:08:05
	 * @return
	 */
	@RequestMapping("/role/toList")
	public String toRole() {
		return "/system/role/list";
	}

	/**
	 * 跳转至登录统计页面
	 * 
	 * @return
	 */
	@RequestMapping("/loginNum")
	public String toLoginList() {
		return "/system/login/loginList";
	}

	@RequestMapping("/ordertype/toList")
	public String toordertypeList() {
		return "/system/ordertype/list";
	}
	/**
	 * @description 跳往部门页面
	 * @author 白贵才
	 * @create 2017年10月23日15:18:02
	 * @return
	 */
	@RequestMapping("/department/toList")
	public String toDepartment(HttpServletRequest request) {
		List<Department> departmentList = departmentService.getList(null, null);
		JSONArray json = new JSONArray();// 格式化父级部门名称
		StringBuffer select = new StringBuffer();// 父级部门select
		if (departmentList != null && departmentList.size() > 0) {
			for (Department depart : departmentList) {
				JSONObject obj = new JSONObject();
				obj.put("id", depart.getId());
				obj.put("name", depart.getName());
				json.add(obj);
				select.append(
						depart.getId() + ":\'" + depart.getName() + "\',");
			}
		}
		request.setAttribute("departmentJSON", json);
		request.setAttribute("select", "{value:{0:'无',"
				+ select.toString().substring(0, select.toString().length() - 1)
				+ "}}");

		return "/system/department/list";
	}

	/**
	 * @description 跳往模块页面
	 * @author 白贵才
	 * @create 2017年10月23日12:08:05
	 * @return
	 */
	@RequestMapping("/module/toList")
	public String toModule(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("level = ?", "0");
		List<Module> moduleList = moduleService.getList(params, null);
		JSONArray json = new JSONArray();// 格式化父级菜单名称
		StringBuffer select = new StringBuffer();// 父级菜单select
		if (moduleList != null && moduleList.size() > 0) {
			for (Module modu : moduleList) {
				JSONObject obj = new JSONObject();
				obj.put("id", modu.getId());
				obj.put("name", modu.getName());
				json.add(obj);
				select.append(modu.getId() + ":\'" + modu.getName() + "\',");
			}
		}
		request.setAttribute("moduleJSON", json);
		request.setAttribute("select", "{value:{0:'无',"
				+ select.toString().substring(0, select.toString().length() - 1)
				+ "}}");

		return "/system/module/list";
	}

	/**
	 * @description 部门角色新增
	 * @author 白贵才
	 * @create 2017年10月23日12:08:05
	 * @return
	 */
	@RequestMapping("/role/save")
	public void RoleSave(int roleId, int departmentId) {

	}

	/**
	 * @description 所有角色获取
	 * @author 白贵才
	 * @create 2017年10月23日12:08:05
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/role/getList")
	public void RoleList(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "rows") int rows, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("page", page);
		params.put("rows", rows);
		Page<Role> pageBean = roleService.getPageList(params, null);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().print(pageBean.toJqGridString());
		response.getWriter().flush();
		response.getWriter().close();
	}

	/**
	 * @description 角色新增或者修改或者删除
	 * @author 白贵才
	 * @create 2017年10月23日12:08:05
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/role/edit", produces = "application/json;charset=utf-8")
	public String toRoleEdit(
			@RequestParam(value = "id", required = false) String id,
			String oper, RoleVo roleVo, HttpServletRequest request) {
		StringBuffer msg1 = new StringBuffer();
		StringBuffer msg2 = new StringBuffer();
		User currentUser = (User) request.getSession().getAttribute("user");
		Role role = new Role();
		if (id == null || id.equals("") || id.equals("_empty")) {
			// 增加
			BeanUtils.copyProperties(roleVo, role);
			role.setCreateUser(currentUser);
			role.setCreateDate(currentDate);
			roleService.save(role);
		} else {
			// 更新或删除
			if (oper != null && !oper.equals("")) {
				if (oper.equals("del")) {
					// 删除
					String ids[] = id.split(",");
					Map<String, Object> params = new HashMap<String, Object>();
					if (ids != null && ids.length > 0) {
						for (String i : ids) {
							params.put("role.id = ?", Integer.valueOf(i));
							List<ModuleRole> listMR = moduleRoleService
									.getList(params, null);
							List<DepartmentRole> listDR = departmentRoleService
									.getList(params, null);
							if ((listMR != null && listMR.size() > 0)
									|| (listDR != null && listDR.size() > 0)) {
								if (listMR != null && listMR.size() > 0) {
									msg1.append(
											listMR.get(0).getRole().getName()
													+ ",");
								}
								if (listDR != null && listDR.size() > 0) {
									msg2.append(
											listDR.get(0).getRole().getName()
													+ ",");
								}
							} else {
								role.setId(Integer.valueOf(i));
								roleService.delete(role);
							}
						}
					}
				} else {
					// 更新
					role = roleService.getById(Integer.valueOf(id));
					BeanUtils.copyProperties(roleVo, role);
					role.setUpdateDate(currentDate);
					role.setUpdateUser(currentUser);
					roleService.update(role);
				}
			}
		}
		if (!"".equals(msg1.toString()) || !"".equals(msg2.toString())) {
			StringBuffer returnMsg = new StringBuffer();
			if (!"".equals(msg1.toString())) {
				msg1.deleteCharAt(msg1.length() - 1);
				returnMsg.append("部分删除失败!【" + msg1.toString()
						+ "】等角色分配了模块,需要取消角色的模块对应,才能删除;");
			}
			if (!"".equals(msg2.toString())) {
				msg2.deleteCharAt(msg2.length() - 1);
				returnMsg.append("部分删除失败!【" + msg2.toString()
						+ "】等角色分配了部门,需要取消部门的对应角色,才能删除");
			}

			return "{\"status\":\"success\",\"msg\":\"" + returnMsg.toString()
					+ "\"}";
		} else {
			return "{\"status\":\"success\"}";
		}
	}

	/**
	 * @description 所有部门获取
	 * @author 白贵才
	 * @create 2017年10月23日15:18:49
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/department/getList")
	public void DepartmentList(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "rows") int rows, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("page", page);
		params.put("rows", rows);
		Page<Department> pageBean = departmentService.getPageList(params, null);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().print(pageBean.toJqGridString());
		response.getWriter().flush();
		response.getWriter().close();
	}

	/**
	 * @description 部门新增或者修改或者删除
	 * @author 白贵才
	 * @create 2017年10月23日15:19:56
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/department/edit", produces = "application/json;charset=utf-8")
	public String toDepartmentEdit(
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "parentDepartId", required = false) String parentDepartId,
			String oper, DepartmentVo departmentVo,
			HttpServletRequest request) {
		StringBuffer msg1 = new StringBuffer();
		StringBuffer msg2 = new StringBuffer();
		Department department = new Department();
		if (id == null || id.equals("") || id.equals("_empty")) {
			// 增加
			BeanUtils.copyProperties(departmentVo, department);
			if (parentDepartId != null && !parentDepartId.equals("")
					&& !parentDepartId.equals("0")) {
				Department parent = departmentService
						.getById(Integer.valueOf(parentDepartId));
				department.setCreateDate(currentDate);
				department.setParent(parent);
			}
			departmentService.save(department);
		} else {
			// 更新或删除
			if (oper != null && !oper.equals("")) {
				if (oper.equals("del")) {
					// 删除
					String ids[] = id.split(",");
					Map<String, Object> params = new HashMap<String, Object>();
					if (ids != null && ids.length > 0) {
						for (String i : ids) {
							params.put("department.id = ?", Integer.valueOf(i));
							List<DepartmentRole> listMR = departmentRoleService
									.getList(params, null);
							List<User> listU = userService.getList(params,
									null);
							if ((listMR != null && listMR.size() > 0)
									|| (listU != null && listU.size() > 0)) {
								if (listMR != null && listMR.size() > 0) {
									msg1.append(listMR.get(0).getDepartment()
											.getName() + ",");
								}
								if (listU != null && listU.size() > 0) {
									msg2.append(listU.get(0).getDepartment()
											.getName() + ",");
								}
							} else {
								department.setId(Integer.valueOf(i));
								departmentService.delete(department);
							}
						}
					}
				} else {
					// 更新
					department = departmentService.getById(Integer.valueOf(id));
					BeanUtils.copyProperties(departmentVo, department);
					if (parentDepartId != null && !parentDepartId.equals("")) {
						if (!parentDepartId.equals("0")) {
							Department parent = departmentService
									.getById(Integer.valueOf(parentDepartId));
							department.setParent(parent);
						} else {
							department.setParent(null);
						}
					}
					departmentService.update(department);
				}
			}
		}
		if (!"".equals(msg1.toString()) || !"".equals(msg2.toString())) {
			StringBuffer returnMsg = new StringBuffer();
			if (!"".equals(msg1.toString())) {
				msg1.deleteCharAt(msg1.length() - 1);
				returnMsg.append("部分删除失败!【" + msg1.toString()
						+ "】等部门分配了角色,需要取消部门的角色对应,才能删除;");
			}
			if (!"".equals(msg2.toString())) {
				msg2.deleteCharAt(msg2.length() - 1);
				returnMsg.append("部分删除失败!【" + msg2.toString()
						+ "】等部门分配了用户,需要取消用户的对应部门,才能删除");
			}

			return "{\"status\":\"success\",\"msg\":\"" + returnMsg.toString()
					+ "\"}";
		} else {
			return "{\"status\":\"success\"}";
		}
	}

	/**
	 * @description 所有模块获取
	 * @author 白贵才
	 * @create 2017年10月23日15:18:49
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/module/getList")
	public void ModuleList(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "rows") int rows, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("page", page);
		params.put("rows", rows);
		// params.put("level != ?", "0");
		Page<Module> pageBean = moduleService.getPageList(params, null);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().print(pageBean.toJqGridString());
		response.getWriter().flush();
		response.getWriter().close();
	}

	/**
	 * @description 模块新增或者修改或者删除
	 * @author 白贵才
	 * @create 2017年10月23日15:19:56
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/module/edit", produces = "application/json;charset=utf-8")
	public String toDepartmentEdit(
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "parentModuleId", required = false) String parentModuleId,
			String oper, ModuleVo moduleVo, HttpServletRequest request) {
		StringBuffer msg1 = new StringBuffer();
		StringBuffer msg2 = new StringBuffer();
		User currentUser = (User) request.getSession().getAttribute("user");
		Module module = new Module();
		if (id == null || id.equals("") || id.equals("_empty")) {
			// 增加
			BeanUtils.copyProperties(moduleVo, module);
			module.setCreateUser(currentUser);
			module.setCreateDate(currentDate);
			if (parentModuleId != null && !parentModuleId.equals("")
					&& !parentModuleId.equals("0")) {
				Module parent = moduleService
						.getById(Integer.valueOf(parentModuleId));

				module.setParent(parent);
				module.setLevel("1");
			} else {
				module.setLevel("0");
			}
			moduleService.save(module);
		} else {
			// 更新或删除
			if (oper != null && !oper.equals("")) {
				if (oper.equals("del")) {
					// 删除
					String ids[] = id.split(",");
					Map<String, Object> params = new HashMap<String, Object>();
					if (ids != null && ids.length > 0) {
						for (String i : ids) {

							params.put("module.id = ?", Integer.valueOf(i));
							List<ModuleRole> list = moduleRoleService
									.getList(params, null);

							params.clear();
							params.put("parent.id = ?", Integer.valueOf(i));
							List<Module> childList = moduleService
									.getList(params, null);
							params.clear();
							// 有对应关系不能删除
							if ((list != null && list.size() > 0)
									|| (childList != null
											&& childList.size() > 0)) {
								if (list != null && list.size() > 0) {
									msg1.append(
											list.get(0).getModule().getName()
													+ ",");
								}
								if (childList != null && childList.size() > 0) {
									msg2.append(
											childList.get(0).getName() + ",");
								}
							} else {
								module.setId(Integer.valueOf(i));
								moduleService.deleteById("wms_module",
										module.getId());
							}

						}
					}
				} else {
					// 更新
					module = moduleService.getById(Integer.valueOf(id));
					BeanUtils.copyProperties(moduleVo, module);
					module.setUpdateDate(currentDate);
					module.setUpdateUser(currentUser);
					if (parentModuleId != null && !parentModuleId.equals("")) {
						if (!parentModuleId.equals("0")) {
							Module parent = moduleService
									.getById(Integer.valueOf(parentModuleId));
							module.setParent(parent);
							module.setLevel("1");
						} else {
							module.setParent(null);
							module.setLevel("0");
						}
					}
					moduleService.update(module);
				}
			}
		}
		if (!"".equals(msg1.toString()) || !"".equals(msg2.toString())) {
			StringBuffer returnMsg = new StringBuffer();
			if (!"".equals(msg1.toString())) {
				msg1.deleteCharAt(msg1.length() - 1);
				returnMsg.append("部分删除失败!【" + msg1.toString()
						+ "】模块分配了角色,需要取消对应角色的模块,才能删除;");
			}
			if (!"".equals(msg2.toString())) {
				msg2.deleteCharAt(msg2.length() - 1);
				returnMsg.append(
						"部分删除失败!【" + msg2.toString() + "】模块有子模块,需要删除子模块,才能删除");
			}

			return "{\"status\":\"success\",\"msg\":\"" + returnMsg.toString()
					+ "\"}";
		} else {
			return "{\"status\":\"success\"}";
		}
	}

	/**
	 * @description 跳转至角色模块分配页面,并生成树菜单
	 * @author 白贵才
	 * @createDate 2017年10月24日14:12:11
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/addModuleRole")
	public String toRMTreeview(int id, HttpServletRequest request) {
		Role role = roleService.getById(id);
		request.setAttribute("role", role);
		String[] level = {"0", "1"};
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("level in (:level)", level);
		List<Module> module = moduleService.getList(params, null);// 一级二级菜单

		// 获取该角色的所有可以访问的模块
		Map<String, Object> params2 = new HashMap<String, Object>();
		params2.put("role.id = ?", role.getId());
		List<ModuleRole> roleList = moduleRoleService.getList(params2, null);

		JSONArray json = new JSONArray();
		if (module != null) {
			for (Module mod : module) {
				JSONObject object = new JSONObject();
				boolean flag = false;
				if (roleList != null && roleList.size() > 0) {
					for (ModuleRole moduleRole : roleList) {
						if (mod.equals(moduleRole.getModule())) {
							object.put("checked", "true");
							flag = true;
							break;
						}
					}
				}
				if (flag == false) {
					object.put("checked", "");
				}
				object.put("id", mod.getId());
				if ("0".equals(mod.getLevel())) {
					object.put("isParent", "true");
				} else {
					object.put("isParent", "");
				}
				object.put("name", mod.getName());
				object.put("nocheck", "");
				object.put("open", "");
				object.put("pId",
						mod.getParent() == null ? "" : mod.getParent().getId());
				object.put("type", "");
				json.add(object);
			}
		}
		request.setAttribute("json", json);
		return "settings/role/addModuleRole";
	}

	/**
	 * @description 获取所有1、2级模块组成树菜单
	 * @author 白贵才
	 * @createDate 2017年10月25日16:05:53
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/role/json")
	public void toJSON(HttpServletResponse response) throws IOException {
		/*
		 * JSONObject json1 = new JSONObject(); JSONObject json2 = new
		 * JSONObject(); JSONObject json3 = new JSONObject(); JSONObject json4 =
		 * new JSONObject(); JSONObject json5 = new JSONObject();
		 * json5.put("name", "baiguicai"); json5.put("type", "item");
		 * json4.put("person", json5); json3.put("children", json4);
		 * json2.put("name", "admin"); json2.put("type", "folder");
		 * json2.put("additionalParameters",json3); json1.put("01", json2);
		 * json1.put("02", json2);
		 */
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("level = ?", "0");
		params.put("level in ('1','2')", "1");
		List<Module> module = moduleService.getList(params, null);// 一级二级菜单

		// 封装菜单
		// List<ListMenu> menus = new ArrayList<ListMenu>();

		JSONArray json = new JSONArray();
		if (module != null) {
			// int i = 1;
			for (Module mod : module) {
				JSONObject object = new JSONObject();
				object.put("checked", "");
				object.put("id", mod.getId());
				object.put("isParent", "");
				object.put("name", mod.getName());
				object.put("nocheck", "");
				object.put("open", "");
				object.put("pId",
						mod.getParent() == null ? "" : mod.getParent().getId());
				object.put("type", "");
				json.add(object);
			}
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().print(JsonUtils.toJson(json));
		response.getWriter().flush();
		response.getWriter().close();
	}

	/**
	 * @description 模块角色权限分配
	 * @author 白贵才
	 * @createDate 2017年10月25日15:56:35
	 * @param treeData
	 * @param roleId
	 * @param request
	 * @param out
	 */
	@RequestMapping("/add")
	public void addModuleRole(String treeData, int roleId,
			HttpServletRequest request, PrintWriter out) {
		User currentUser = (User) request.getSession().getAttribute("user");
		List<TreeNode> nodes = JSONArray.parseArray(treeData, TreeNode.class);
		// 先删除这个角色的所有权限,再添加权限
		moduleRoleService.deleteByRoleId(roleId);
		Role role = roleService.getById(roleId);
		for (TreeNode node : nodes) {
			ModuleRole moduleRole = new ModuleRole();
			int moduleId = Integer.parseInt(node.getId());
			Module module = moduleService.getById(moduleId);

			moduleRole.setModule(module);
			moduleRole.setRole(role);
			moduleRole.setRemark("");
			moduleRole.setCreateDate(currentDate);
			moduleRole.setCreateUser(currentUser);
			moduleRoleService.save(moduleRole);
		}
		out.print("{\"status\":\"success\"}");
		out.flush();
		out.close();
	}

	/**
	 * @description 跳转至部门模块分配角色页面,并生成树菜单
	 * @author 白贵才
	 * @createDate 2017年10月31日14:26:28
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/department/addDepartmentRole")
	public String toDRTreeview(int id, HttpServletRequest request) {
		Department department = departmentService.getById(id);
		request.setAttribute("department", department);

		// 获取该部门的所有角色
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("department.id = ?", department.getId());
		List<DepartmentRole> departmentRoleList = departmentRoleService
				.getList(params, null);

		// 获取所有的角色
		List<Role> roleList = roleService.getList(null, null);

		JSONArray json = new JSONArray();
		if (roleList != null) {
			for (Role role : roleList) {
				JSONObject object = new JSONObject();
				boolean flag = false;
				if (roleList != null && roleList.size() > 0) {
					for (DepartmentRole departmentRole : departmentRoleList) {
						if (role.equals(departmentRole.getRole())) {
							object.put("checked", "true");
							flag = true;
							break;
						}
					}
				}
				if (flag == false) {
					object.put("checked", "");
				}
				object.put("id", role.getId());
				object.put("isParent", "");
				object.put("name", role.getName());
				object.put("nocheck", "");
				object.put("open", "");
				object.put("pId", "");
				object.put("type", "");
				json.add(object);
			}
		}
		request.setAttribute("json", json);
		return "/system/department/addDepartmentRole";
	}

	/**
	 * @description 部门的角色分配
	 * @author 白贵才
	 * @createDate 2017年10月31日14:36:47
	 * @param treeData
	 * @param roleId
	 * @param request
	 * @param out
	 */
	@RequestMapping("/department/add")
	public void addDepartRole(String treeData, int departmentId,
			HttpServletRequest request, PrintWriter out) {
		User currentUser = (User) request.getSession().getAttribute("user");
		List<TreeNode> nodes = JSONArray.parseArray(treeData, TreeNode.class);
		// 先删除这个角色的所有权限,再添加权限
		departmentRoleService.deleteByDepartmentId(departmentId);
		Department department = departmentService.getById(departmentId);
		for (TreeNode node : nodes) {
			DepartmentRole departmentRole = new DepartmentRole();
			int roleId = Integer.parseInt(node.getId());
			Role role = roleService.getById(roleId);

			departmentRole.setDepartment(department);
			departmentRole.setRole(role);
			departmentRole.setRemark("");
			departmentRole.setCreateDate(currentDate);
			departmentRole.setCreateUser(currentUser);
			departmentRoleService.save(departmentRole);
		}
		out.print("{\"status\":\"success\"}");
		out.flush();
		out.close();
	}

	/**
	 * @description 获取所有的部门的名称
	 * @author 白贵才
	 * @createDate 2017年11月24日18:31:05
	 * @param treeData
	 * @param roleId
	 * @param request
	 * @param out
	 */
	@RequestMapping("/department/getDepartName")
	public void getDepartName(HttpServletRequest request, PrintWriter out) {
		List<Department> departments = departmentService.getList(null, null);
		out.print(JsonUtils.toJson(departments));
		out.flush();
		out.close();
	}

	/**
	 * @description 跳往参数管理页面
	 * @author 白贵才2017年11月1日16:14:062017年10月23日12:08:05
	 * @return
	 */
	@RequestMapping("/params/toList")
	public String toParams() {
		return "/system/params/list";
	}

	/**
	 * @description 所有参数获取
	 * @author 白贵才
	 * @create 2017年11月1日16:15:56
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/params/getList")
	public void ParamsList(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "rows") int rows, HttpServletRequest request,
			HttpSession session, HttpServletResponse response)
			throws IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, String> orderBy = new HashMap<String, String>();
		params.put("page", page);
		params.put("rows", rows);
		orderBy.put("order by code", "ASC");
		Page<Params> pageBean = paramsService.getPageList(params, orderBy);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id = ?", 24);
		// Params p=paramsService.getByParams(param);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().print(pageBean.toJqGridString());
		response.getWriter().flush();
		response.getWriter().close();
	}

	/**
	 * @description 参数新增删除更新
	 * @author 白贵才
	 * @create 2017年11月1日14:23:49
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/params/edit", produces = "application/json;charset=utf-8")
	public String toParamsEdit(
			@RequestParam(value = "id", required = false) String id,
			ParamsVo paramsVo, String oper, HttpServletRequest request) {
		User currentUser = (User) request.getSession().getAttribute("user");
		Params params = new Params();
		if (id == null || id.equals("") || id.equals("_empty")) {
			// 增加
			BeanUtils.copyProperties(paramsVo, params);
			params.setCreateDate(currentDate);
			params.setCreateUser(currentUser);
			paramsService.save(params);
		} else {
			// 更新或删除
			if (oper != null && !oper.equals("")) {
				if (oper.equals("del")) {
					// 删除
					String ids[] = id.split(",");
					if (ids != null && ids.length > 0) {
						for (String i : ids) {
							if (!i.equals("24")) {
								params.setId(Integer.valueOf(i));
								paramsService.delete(params);
							}
						}
					}
				} else {
					// 更新
					params = paramsService.getById(Integer.valueOf(id));
					BeanUtils.copyProperties(paramsVo, params);
					params.setUpdateDate(currentDate);
					params.setUpdateUser(currentUser);
					paramsService.update(params);
				}
			}
		}

		return "{\"status\":\"success\"}";
	}
	/**
	 * @description 获取登录统计列表
	 * @author 白贵才
	 * @create 2017年11月1日14:23:49
	 * @return
	 */
	@RequestMapping("/getLoginList")
	public void getloginnum(PrintWriter out,
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int rows,
			HttpServletRequest request, HttpServletResponse response,
			String stringvalue, String beginTime, String endTime)
			throws ParseException {
		Date begin = null;
		Date end = null;
		if (beginTime != null && !beginTime.equals("")) {
			begin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(beginTime);
		}
		if (endTime != null && !endTime.equals("")) {
			end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("page", page);
		params.put("rows", rows);
		if (begin != null && end != null) {
			params.put("loginTime >= ?", begin);
			params.put("loginTime < ?", end);
		}
		if (stringvalue != null && !stringvalue.equals("")) {
			params.put("number = ?", stringvalue);
		}
		Page<LoginNum> pageBean = loginNumService.getPageList(params, null);
		if (pageBean.getRecords() == 0) {
			params.remove("number = ?");
			// 或查询
			params.put("name = ?", stringvalue);
			pageBean = loginNumService.getPageList(params, null);
		}
		out.print(pageBean.toJqGridString());
		out.flush();
		out.close();
	}

	@RequestMapping(value = "/getParentModule", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String getParentModule(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		List<Module> list = moduleService.getList(params, null);
		List<String> Pmodule = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getLevel().equals("0"))
				Pmodule.add(list.get(i).getId() + ":" + list.get(i).getName());
		}
		return JsonUtils.toStandardJson(Pmodule);
	}

}
