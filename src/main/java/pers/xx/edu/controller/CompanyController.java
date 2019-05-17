package pers.xx.edu.controller;

import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import pers.xx.edu.entity.Company;
import pers.xx.edu.entity.MyCity;
import pers.xx.edu.entity.MyCounty;
import pers.xx.edu.entity.MyProvince;
import pers.xx.edu.entity.Recruitment;
import pers.xx.edu.service.CompanyService;
import pers.xx.edu.service.MyCityService;
import pers.xx.edu.service.MyCountyService;
import pers.xx.edu.service.MyProvinceService;
import pers.xx.edu.service.RecruitmentService;
import pers.xx.edu.utils.DateTimeUtils;
import pers.xx.edu.utils.Page;
import pers.xx.edu.utils.StringUtils;
import pers.xx.edu.utils.UploadUtils;
import pers.xx.edu.vo.CompanyVo;
import pers.xx.edu.vo.CompanyVo2;
import pers.xx.edu.vo.RecruitmentVo;

/**
 * @author XieXing
 * @createDate 2019年5月11日 下午2:45:45
 * @description 公司controller
 */
@Controller
@RequestMapping("/company")
public class CompanyController {
	@Autowired
	private CompanyService companyService;

	@Autowired
	private RecruitmentService recruitmentService;
	
	@Autowired
	private MyProvinceService myProvinceService;
	
	@Autowired
	private MyCityService myCityService;
	
	@Autowired
	private MyCountyService myCountyService;
	/**
	 * @author XieXing
	 * @createDate 2019年5月11日 下午3:05:08
	 * @description 跳转到公司信息列表页面
	 * @return
	 */
	@RequestMapping("/toCompanyList")
	public String toCompanyList() {
		return "company/list";
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月11日 下午3:06:05
	 * @description 跳转到公司信息页面
	 * @return
	 */
	@RequestMapping("/toCompanyAdd")
	public String toCompanyAdd(Integer id,String operate,Map<String, Object> map) {
		if(id!=null) {
			Company company = companyService.getById(id);
			map.put("company",company);
		}
		map.put("operate",operate);
		return "company/add";
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月11日 下午3:06:33
	 * @description 跳转到招聘信息列表页面
	 * @return
	 */
	@RequestMapping("/toRecruitmentList")
	public String toRecruitmentList() {
		return "recruitment/list";
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月11日 下午2:47:48
	 * @description 跳转到招聘信息新增页面
	 * @return
	 */
	@RequestMapping("/toRecruitmentAdd")
	public String toRecruitmentAdd(Integer id,String operate,Map<String, Object> map) {
		if(id!=null) {
			Recruitment recruitment = recruitmentService.getById(id);
			map.put("recruitment", recruitment);
		}
		map.put("operate",operate);
		return "recruitment/add";
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月12日 上午8:50:45
	 * @description 获取公司列表数据
	 * @param page
	 * @param rows
	 * @param content
	 * @param start
	 * @param end
	 * @param gradeId
	 * @param teaId
	 * @param request
	 * @param out
	 */
	@RequestMapping("/getCompanyList")
	public void getCompanyList(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int rows,
			String content, String start, String end, Integer gradeId, Integer teaId, HttpServletRequest request,
			PrintWriter out) {
		Map<String, Object> params = new LinkedHashMap<>();// 值参数
		params.put("page", page);
		params.put("rows", rows);
		if (StringUtils.isNotEmpty(content)) {
			params.put(
					"(companyNumber like :content or companyName like :content or companyTel like :content or companyEmail like :content or uscc like :content or organizationCode like :content or companyAddress like :content)",
					"%" + content + "%");
		}
		Map<String, String> orderOrGroupBy = new HashMap<>();// 排序参数
		orderOrGroupBy.put("order by", "id desc");
		Page<Company> pageBean = new Page<>();
		pageBean = companyService.getPageList(params, orderOrGroupBy);
		Page<CompanyVo2> pageBean2 = new Page<>();
		pageBean2.setPage(pageBean.getPage());
		pageBean2.setRecords(pageBean.getRecords());
		pageBean2.setTotal(pageBean.getTotal());
		List<CompanyVo2> companyVo2s = new ArrayList<>();
		for(Company company:pageBean.getRows()) {
			CompanyVo2 companyVo2 = new CompanyVo2();
			BeanUtils.copyProperties(company, companyVo2);
			companyVo2s.add(companyVo2);
		}
		pageBean2.setRows(companyVo2s);
		out.print(pageBean2.toJqGridString());
		out.flush();
		out.close();
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月12日 下午1:51:47
	 * @description 获取招聘信息数据
	 * @param page
	 * @param rows
	 * @param content
	 * @param start
	 * @param end
	 * @param gradeId
	 * @param teaId
	 * @param request
	 * @param out
	 */
	@RequestMapping("/getRecruitmentList")
	public void getRecruitmentList(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int rows, String content, String start, String end, Integer gradeId,
			Integer teaId, HttpServletRequest request, PrintWriter out) {
		Map<String, Object> params = new LinkedHashMap<>();// 值参数
		params.put("page", page);
		params.put("rows", rows);
		if (StringUtils.isNotEmpty(start)) {
			try {
				params.put("rcTime >= ?", pers.xx.edu.utils.DateTimeUtils.deal(start));
			} catch (ParseException e) {
				System.err.println("时间格式不正确！");
			}
		}
		if (StringUtils.isNotEmpty(end)) {
			try {
				params.put("rcTime < ?", DateTimeUtils.deal(end));
			} catch (ParseException e) {
				System.err.println("时间格式不正确！");
			}
		}
		if (StringUtils.isNotEmpty(content)) {
			params.put("(rcCompany.companyName like :content or rcJob like :content or rcRemark like :content)",
					"%" + content + "%");
		}
		Map<String, String> orderOrGroupBy = new HashMap<>();// 排序参数
		orderOrGroupBy.put("order by", "id desc");
		Page<Recruitment> pageBean = new Page<>();
		pageBean = recruitmentService.getPageList(params, orderOrGroupBy);
		Page<RecruitmentVo> pageBean2 = new Page<>();
		pageBean2.setPage(pageBean.getPage());
		pageBean2.setRecords(pageBean.getRecords());
		pageBean2.setTotal(pageBean.getTotal());
		List<RecruitmentVo> recruitmentVos = new ArrayList<>();
		for(Recruitment recruitment:pageBean.getRows()) {
			RecruitmentVo recruitmentVo = new RecruitmentVo();
			BeanUtils.copyProperties(recruitment, recruitmentVo);
			recruitmentVo.setCompanyName(recruitment.getRcCompany().getCompanyName());
			recruitmentVos.add(recruitmentVo);
		}
		pageBean2.setRows(recruitmentVos);
		out.print(pageBean2.toJqGridString());
		out.flush();
		out.close();
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月12日 下午4:36:21
	 * @description 编辑公司信息
	 * @param companyVo
	 * @param provinceId
	 * @param cityId
	 * @param countyId
	 * @param logoFile
	 * @param licenseFile
	 * @param session
	 * @return
	 */
	@RequestMapping("/companyEdit")
	public String companyEdit(CompanyVo companyVo,Integer provinceId,Integer cityId,Integer countyId, @RequestParam(value="logoFile",required=false)CommonsMultipartFile logoFile,@RequestParam(value="licenseFile",required=false)CommonsMultipartFile licenseFile,HttpSession session) {
		Company company = null;
		Integer id = companyVo.getId();
		if(id==null) {
			company = new Company();
			company.setCompanyPassword(StringUtils.StringToMd5("123456"));
		}else {
			company = companyService.getById(id);
		}
		BeanUtils.copyProperties(companyVo, company);
		if(provinceId!=null) {
			MyProvince myProvince = myProvinceService.getById(provinceId);
			company.setCompanyProvince(myProvince);
		}
		if(cityId!=null) {
			MyCity myCity = myCityService.getById(cityId);
			company.setCompanCity(myCity);
		}
		if(countyId!=null) {
			MyCounty myCounty = myCountyService.getById(countyId);
			company.setCompanyCounty(myCounty);
		}
		if (logoFile != null && !logoFile.isEmpty()) {
			String savePath = UploadUtils.saveFile(logoFile, session, "1");
			company.setCompanyLogo(savePath);
		}
		if (licenseFile != null && !licenseFile.isEmpty()) {
			String savePath = UploadUtils.saveFile(licenseFile, session, "1");
			company.setCompanyLicense(savePath);
		}
		companyService.saveOrUpdate(company);
		return "redirect:/company/toCompanyList";
	}
	
	
	/**
	 * @author XieXing
	 * @createDate 2019年5月12日 下午4:44:19
	 * @description 编辑招聘信息
	 * @param recruitment
	 * @param companyId
	 * @return
	 */
	@RequestMapping("/recruitmentEdit")
	public String recruitmentEdit(Recruitment recruitment,Integer companyId) {
		if(companyId!=null) {
			Company company = companyService.getById(companyId);
			recruitment.setRcCompany(company);
		}
		recruitment.setRcTime(new Date());
		recruitmentService.saveOrUpdate(recruitment);
		return "redirect:/company/toRecruitmentList";
	}
	
	
	/**
	 * @author XieXing
	 * @createDate 2019年5月13日 下午2:16:12
	 * @description 获取所有的公司
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAllCompanies")
	public List<Company> getAllCompanies(){
		return companyService.getAll();
	}
}
