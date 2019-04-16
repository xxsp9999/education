package pers.xx.edu.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
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
import pers.xx.edu.entity.User;
import pers.xx.edu.service.DepartmentService;
import pers.xx.edu.service.UserService;
import pers.xx.edu.utils.ImageUtils;
import pers.xx.edu.utils.Page;
import pers.xx.edu.utils.StringUtils;
import pers.xx.edu.vo.UserVo;

/**
 *
 * @description 用户管理类
 * @author XieXing
 * @create 2018-3-24上午9:50:05
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private DepartmentService departmentService;
	/**
	 * @description 跳转到员工管理列表
	 * @return
	 */
	@RequestMapping("/toList")
	//@RequiresRoles("vip")
	//@RequiresPermissions("user:list")
	public String toList(HttpServletRequest request){

		List<Department> departmentList = departmentService.getList(null, null);
		JSONArray json = new JSONArray();//格式化父级部门名称
		StringBuffer select = new StringBuffer();//父级部门select
		if(departmentList != null && departmentList.size() > 0){
			for (Department depart : departmentList) {
				JSONObject obj = new JSONObject();
				obj.put("id", depart.getId());
				obj.put("name", depart.getName());
				json.add(obj);
				select.append(depart.getId()+":\'"+depart.getName()+"\',");
			}
		}
		request.setAttribute("departmentJSON", json);
		request.setAttribute("select", "{value:{0:'无',"+select.toString().substring(0, select.toString().length()-1)+"}}");

		return "system/user/list";
	}

	/**
	 * @description 生成验证码
	 */
	@RequestMapping("/code")
	public void getCode(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Object[] objs = ImageUtils.createImage();
		request.getSession().removeAttribute("imgCode");
		request.getSession().setAttribute("imgCode", objs[0]);
		BufferedImage img = (BufferedImage) objs[1];
		response.setContentType("image/png");
		OutputStream os = response.getOutputStream();
		ImageIO.write(img, "png", os);
		os.close();
	}


	/**
	 * @description 用户管理
	 * @return
	 */
	@RequestMapping("/getList")
	public void userManage(HttpServletRequest request,@RequestParam(defaultValue = "1")int page,String content,
			@RequestParam(defaultValue = "10") int rows,
			PrintWriter out,
			@RequestParam(defaultValue="",required = false)String sidx,
			@RequestParam(defaultValue="",required = false)String sord){
		Page<User> pageBean = new Page<User>();
		Map<String,Object> params = new HashMap<String,Object>();
		Map<String,String> orderBy = new HashMap<String,String>();
		orderBy.put("order by", "companyName");
		params.put("page", page);
		params.put("rows", rows);
		if(content!=null && !"".equals(content)){
			params.put("(name like :content or phone like :content or phone like :content or companyName like :content)", "%" + content + "%");
		}
		pageBean = userService.getPageList(params, orderBy);

		/**
		 * @description 由于屏蔽了 密码等特殊字段，所以需要重新构造数据
		 */
		Page<UserVo> pageUserVo = new Page<>();
		pageUserVo.setPage(pageBean.getPage());
		pageUserVo.setRecords(pageBean.getRecords());
		pageUserVo.setTotal(pageBean.getTotal());

		List<UserVo> userVoList = new ArrayList<>();
		for (User user : pageBean.getRows()) {
			UserVo userVo = new UserVo();
			userVo.setNumber(user.getNumber());
			userVo.setName(user.getName());
			userVo.setPhone(user.getPhone());
			if(user.getDepartment() != null){
				userVo.setIdentityId(user.getDepartment().getId());
				userVo.setIdentity(user.getDepartment().getName());
			}
			userVo.setState(user.getState());
			userVo.setId(user.getId());
			userVoList.add(userVo);
		}
		pageUserVo.setRows(userVoList);
		out.print(pageUserVo.toJqGridString());
		out.flush();
		out.close();
	}

	/**
	 * 跳转到修改密码界面
	 * @return
	 */
	@RequestMapping("/toEditPass")
	public String toEditPass(){
		return "/person/editKey";
	}

	/**
	 * @description 修改密码
	 * @author XieXing
	 * @param id
	 * @param password
	 * @param out
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/editPassword")
	public void String (int id,String oldPsw,String pswConfirm,PrintWriter out,HttpSession session) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		User user = userService.getById(id);
		if(user.getPassword().equals(StringUtils.StringToMd5(oldPsw))){
			user.setPassword(StringUtils.StringToMd5(pswConfirm));
			userService.update(user);
			out.print("1");
		}else{
			out.print("error");//原密码不正确
		}
		out.flush();
		out.close();
	}


	/**
	 * @description 重置部分用户的密码，改为原始手机号
	 * @author XieXing
	 * @param ids
	 * @param out
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/resetKey")
	public void resetKey(String ids,PrintWriter out){
		String[] strId = ids.split(",");
		for(String id:strId){
			User user = userService.getById(Integer.valueOf(id));
			user.setPassword(StringUtils.StringToMd5("123456"));
			userService.update(user);
		}
		out.print("1");
		out.flush();
		out.close();
	}


	/**
	 * @description 更改权限
	 * @author XieXing
	 * @param ids
	 * @param out
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/setDepart")
	public void setDepart(String ids,String departId,PrintWriter out){
		/*Department department = departmentService.getDepartById(Integer.valueOf(departId));
		String[] id = ids.split(",");
		for(String i:id){
			User user = userService.getById(Integer.valueOf(i));
			user.setDepartment(department);
			userService.update(user);
		}
		out.print("1");
		out.flush();
		out.close();*/
	}

	/**
	 * @description 保存
	 * @param out
	 * @param user
	 */
	@RequestMapping("/userUpdate")
	public void userUpdate(PrintWriter out,HttpSession session,User userTemp,String userId){
		User user = userService.getById(Integer.valueOf(userId));
		user.setName(userTemp.getName());
		user.setNumber(userTemp.getNumber());
		user.setPhone(userTemp.getPhone());
		userService.saveOrUpdate(user);
		User currentUser = (User) session.getAttribute("user");
		if(currentUser!=null){
			if(currentUser.getId()==Integer.valueOf(userId)){
				session.removeAttribute("user");
				session.setAttribute("user", user);
			}
		}

		out.print("1");
		out.flush();
		out.close();
	}

	public void getUser(PrintWriter out,HttpSession session,String chars){
	}



//	public void userChange(PrintWriter out,User user,String departId){
//
//		String userName = user.getUserName();
//
//
//
//		user.setUserName(userName);
//		user.setUserSex(userSex);
//		user.setUserAge(userAge);
//		user.setUserIDCard(userIDCard);
//		user.setUserAddress(userAddress);
//		user.setUserPhone(userPhone);
//
//		out.print("1");
//		out.flush();
//		out.close();
//
//
//	}

	/**
	 * @description 停用或启用账号
	 * @param request
	 * @param status
	 * @param userIds
	 */
	@RequestMapping("/logOffUser")
	public void logOffUser(HttpServletRequest request,String status,String userIds,PrintWriter out){
		String[] ids = userIds.split(",");
		for(int i =0;i<ids.length;i++){
			User user = userService.getById(Integer.valueOf(ids[i]));
			if("logOn".equals(status)){
				//启用
				user.setState(1);
			}else if("logOff".equals(status)){
				//停用
				user.setState(0);
			}
			userService.update(user);
		}
		out.print("1");
		out.flush();
		out.close();

	}

	/**
	 * @description 导出用户表
	 * @param county
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/outExcel")
	public void outExcel(String county,String depart,HttpServletResponse response) throws IOException{
		/*Map<String,Object> params = new HashMap<String,Object>();
		params.put("page", -1);
		params.put("rows", -1);
		params.put("sidx", "id");
		params.put("sord", "asc");
		if(!"0".equals(county)){
			params.put("county",county);
		}
		if(!"0".equals(depart)){
			params.put("depart",depart);
		}
		List<User> userList = userService.getAllList(params);

		String[] titles = {"序号","区县", "支局","乡镇","用户名","账号","部门","本月登录次数","总计登录次数","上次登录时间","状态"};

		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet sheet = null;
		if("0".equals(county)){
			sheet = wb.createSheet("所有账号");
		}else{
			sheet = wb.createSheet(county+"所有账号");
		}
		HSSFRow row=sheet.createRow(0);
		HSSFCellStyle stytle=wb.createCellStyle();
		stytle.setAlignment(HSSFCellStyle.ALIGN_LEFT);

		for(int i=0;i<titles.length;i++){
			HSSFCell cell=row.createCell(i);
		    cell.setCellValue(titles[i]);
		    cell.setCellStyle(stytle);
			sheet.autoSizeColumn(i);
		}

		for(int i=0;i<userList.size();i++){
			row=sheet.createRow(i+1);
			User user = userList.get(i);
			 row.createCell(0).setCellValue(user.getId());
			 row.createCell(1).setCellValue(user.getCounty());
			 row.createCell(2).setCellValue(user.getDistrict());
			 row.createCell(3).setCellValue(user.getTownship());
			 row.createCell(4).setCellValue(user.getUserName());
			 row.createCell(5).setCellValue(user.getUserPhone());
			 try{
				 row.createCell(6).setCellValue(user.getDepartment().getName());
			 }catch(Exception e){
				 row.createCell(6).setCellValue("部门异常");
			 }
			 row.createCell(7).setCellValue(user.getMonthLoginNum());
			 row.createCell(8).setCellValue(user.getTotalLoginNum());
			 if(user.getLastLoginDate()!=null){
				 row.createCell(9).setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(user.getLastLoginDate()));
			 }else{
				 row.createCell(9).setCellValue("未登录");
			 }
			 row.createCell(10).setCellValue(user.getStatus()==-1?"停用":"正常");
		}
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=user.xls");
		OutputStream output = response.getOutputStream();
		wb.write(output);
		output.flush();
		output.close();*/
	}


	/**
	 * @description 用户新增或者修改或者删除
	 * @author XieXing
	 * @create 2018年10月23日15:19:56
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/edit")
	public String toDepartmentEdit(@RequestParam(defaultValue="",required=false) String id, @RequestParam(value="identity",required=false) String identity,String oper, UserVo userVo, HttpServletRequest request){
		User user = new User();
		if(id == null || id.equals("") || id.equals("_empty")){
			//增加
			BeanUtils.copyProperties(userVo, user);
			if(identity != null && !identity.equals("") && !identity.equals("0")){
				Department department = departmentService.getById(Integer.valueOf(identity));

				user.setDepartment(department);
				user.setState(1);
			}else{
				user.setState(0);
			}
			userService.save(user);
		}else{
			//更新或删除
			if(oper != null && !oper.equals("")){
				if(oper.equals("del")){
					//删除
					String ids[] = id.split(",");
					if(ids!=null && ids.length>0){
						for(String i:ids){
							user.setId(Integer.valueOf(i));
							userService.delete(user);
						}
					}
				}else{
					//更新
					user = userService.getById(Integer.valueOf(id));
					BeanUtils.copyProperties(userVo, user);
					if(identity != null && !identity.equals("")){
						if(!identity.equals("0")){
							Department department = departmentService.getById(Integer.valueOf(identity));
							user.setDepartment(department);
							user.setState(1);
						}else{
							user.setDepartment(null);
							user.setState(0);
						}
					}
					userService.update(user);
				}
			}
		}
		return "{\"status\":\"success\"}";
	}

	/**
	 * @description 根据编号获取用户
	 * @createDate 2018年11月2日16:58:17
	 * @author XieXing
	 * @param num
	 * @param out
	 */
	@RequestMapping("/getUserByNum")
	public void getUserByNum(String num,PrintWriter out){
		if(num != null && !"".equals("")){
			//包含-则为(编号-姓名)格式
			if(num.contains("-")){
				num = num.substring(0,num.indexOf("-"));
			}

			Map<String,Object> params = new HashMap<String,Object>();
			params.put("number = ?", num);
			User user = userService.getByParams(params);
			out.println("{\"userId\":\""+user.getId()+"\"}");
		}
		out.flush();
		out.close();
	}

	/**
	 * 跳转到个人中心界面
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/toPersonalInfo")
	public String toPersonalInfo(HttpSession session,HttpServletRequest request){
		User user=(User) session.getAttribute("user");
		request.setAttribute("person", user);
		return "/person/personalInfo";
	}


	/**
	 * @description 跳转至用户新增页面
	 * @return
	 */
	@RequestMapping("/addUser")
	public String addUser(HttpServletRequest request){
		Map<String, String> orderBy = new HashMap<String, String>();
		orderBy.put("order by", "number");
		List<User> users = userService.getList(null, orderBy);
		//String number = "10000";
		User lastUser = users.get(users.size() - 1);
		if((users.size() - 1)>=0){

			if(lastUser != null){
				//number = lastUser.getNumber();
			}
		}

		request.setAttribute("number", 001);

		return "/system/user/addUser";
	}



	/**
	 * @description 跳转至用户修改页面
	 * @return
	 */
	@RequestMapping("/editUser")
	public String editUser(HttpServletRequest request){
		User user = new User();
		if(request.getParameter("id")==null){
			user = (User) request.getSession().getAttribute("user");
		}else{
			user = userService.getById(Integer.valueOf(request.getParameter("id")));
		}
		//System.out.println(user);
		request.setAttribute("user", user);
		List<Department> departments = departmentService.getList(null, null);
		request.setAttribute("departments",departments);
		return "/system/user/editUser";
	}

	/**
	 * 删除user
	 */
	@RequestMapping("/delete")
	public void delete(HttpServletRequest request,PrintWriter out,String userIds){
		String[] ids=userIds.split(",");
		if(ids.length!=0){
			for(String id:ids){
				userService.deleteById(Integer.parseInt(id));
			}
		}
		out.print("1");
		out.flush();
		out.close();
	}




	/**
	 * @description 保存
	 * @param out
	 * @param user
	 */
	@RequestMapping("/save")
	public void save(PrintWriter out,User user,String departId){
		//System.out.println(JSON.toJSONString(user));
		Department department = departmentService.getById(Integer.valueOf(departId));
		user.setDepartment(department);
		user.setPassword(StringUtils.StringToMd5("123456"));
		userService.save(user);
		out.print("1");
		out.flush();
		out.close();
	}

	/**
	 * @description 更新
	 * @param out
	 * @param user
	 */
	@RequestMapping("/update")
	public void update(PrintWriter out,Integer id,String departId,String phone,String name,String number,String companyName,HttpServletRequest request){
		User user = userService.getById(id);
		if(phone != null && !"".equals(phone)){
			user.setPhone(phone);
		}
		if(name != null && !"".equals(name)){
			user.setName(name);
		}
		if(number != null && !"".equals(number)){
			user.setNumber(number);
		}
		
		if(departId != null && !"".equals(departId)) {
			Department department = departmentService.getById(Integer.valueOf(departId));
			user.setDepartment(department);
		}
		userService.update(user);

		User sessionUser = (User) request.getSession().getAttribute("user");
		if(sessionUser != null && sessionUser.getId() == id){
			request.getSession().setAttribute("user", user);
		}
		out.print("1");
		out.flush();
		out.close();
	}
}


