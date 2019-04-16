package pers.xx.edu.controller;

import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.xx.edu.entity.Role;
import pers.xx.edu.entity.User;
import pers.xx.edu.service.RoleService;
import pers.xx.edu.service.UserService;
import pers.xx.edu.utils.DateTimeUtils;
import pers.xx.edu.utils.Page;
import pers.xx.edu.utils.ResponseInfo;
import pers.xx.edu.utils.StringUtils;

/**
 * @author XieXing
 * @description 系统设置
 * @create 2019年3月1日 下午12:09:29
 */
@Controller
@RequestMapping("/settings")
public class SettingsController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	/**
	 * @author XieXing
	 * @create 2019年3月29日
	 * @description 跳转到系统设置公共页面
	 * @return
	 */
	@RequestMapping("/toPublic")
	public String toPublic() {
		return "settings/public";
	}
	/**
	 * @author XieXing
	 * @create 2019年3月1日
	 * @description 跳转到账户列表页面
	 * @return
	 */
	@RequestMapping("/toAcList")
	public String toAcListJsp() {
		return "settings/account/list";
	}
	
	/**
	 * @author RanYang
	 * @create 2019年3月6日
	 * @description 跳转到新增账户页面
	 * @return
	 */
	@RequestMapping("/toAcAdd")
	public String toAcAddJsp(String id, Map<String, Object> map, String operate) {
		if(id!=null && StringUtils.isNumeric(id)) {
			User user1 = userService.getById(Integer.valueOf(id));
			map.put("user1", user1);
		}
		map.put("operate", operate);//查看标记
		return "settings/account/add";
	}
	
	/**
	 * @author XieXing
	 * @create 2019年3月1日
	 * @description 跳转到账户列表页面
	 * @return
	 */
	@RequestMapping("/toRoList")
	public String toRoListJsp() {
		return "settings/role/list";
	}
	
	/**
	 * @author HuanZiyi
	 * @create 2019年3月8日
	 * @description 跳转到跳转到客户角色单位基本信息新增或修改或查看页面
	 * @param id 客户单位id
	 * @param map
	 * @param operate 操作标记
	 * @return
	 */
	@RequestMapping("/toRoAdd")
	public String toRoMainJsp(String id,Map<String, Object> map,String operate) {
		if(id!=null&&StringUtils.isNumeric(id)) {
			Role role = roleService.getById(Integer.valueOf(id));
			map.put("role", role);
		}
		map.put("operate", operate);//查看标记
		return "settings/role/add";
	}	
	/**
	 * @author HuanZiyi
	 * @create 2019年3月8日
	 * @description 获取客户角色信息
	 * @param page
	 * @param rows
	 * @param content
	 * @param start
	 * @param end
	 * @param request
	 * @param out
	 * @return
	 */
	@RequestMapping("/getRoList")
	public void getRoList(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int rows, String content,String start,String end,
			HttpServletRequest request, PrintWriter out) {
		Map<String, Object> params = new LinkedHashMap<>();// 值参数
		params.put("page", page);
		params.put("rows", rows);
		if(StringUtils.isNotEmpty(start)) {
			try{
				params.put("createDate >= ?", DateTimeUtils.deal(start));
			}catch(ParseException e) {
					System.err.println("时间格式不正确！");
			}
		}
		if(StringUtils.isNotEmpty(end)) {
			try{
				params.put("createDate < ?", DateTimeUtils.deal(end));
			}catch(ParseException e) {
				System.err.println("时间格式不正确！");
			}
		}
		if (StringUtils.isNotEmpty(content)) {//带外键的需要将外键参数放在前面，否则报错
			params.put(
					"(createUser.name like :content or name like :content)",
					"%" + content + "%");
		}
		Map<String, String> orderOrGroupBy = new HashMap<>();// 排序参数
		orderOrGroupBy.put("order by", "id desc");
		Page<Role> pageBean = new Page<>();
		pageBean = roleService.getPageListNew(params, orderOrGroupBy);
		out.print(pageBean.toJqGridString());
		out.flush();
		out.close();
	}
	/**
	 * @author HuanZiyi
	 * @create 2019年3月8日
	 * @description 保存客户角色信息
	 * @param id
	 * @param name
	 * @param code
	 * @param session 
	 * @return
	 */
	@RequestMapping("/saveRoles")
	public String saveRoles(Integer id,String name, String code,HttpSession session) {
		Role role;
		if(id != null){
			role = roleService.getById(id);
		}else{
			role = new Role();
		}
		User user = (User) session.getAttribute("user");//设置创建者
		role.setCreateUser(user);
		role.setCreateDate(new Date());
		role.setCode(code);
		role.setName(name);
		roleService.saveOrUpdate(role);
		return "settings/role/list";
	}
	/**
	 * @author HuanZiyi
	 * @create 2019年3月8日
	 * @description 删除客户角色信息
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="deleteRoles",produces="application/json;charset=utf-8")
	public ResponseInfo deleteRoles(String ids) {
		Integer[] idss = StringUtils.getListInteger(ids);
		roleService.deleteByIds(idss);
		return new ResponseInfo(true,"删除成功！");
	}
}
