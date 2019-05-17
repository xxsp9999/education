package pers.xx.edu.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.dao.StudentDao;
import pers.xx.edu.entity.EduClass;
import pers.xx.edu.entity.Faculty;
import pers.xx.edu.entity.Major;
import pers.xx.edu.entity.MyCity;
import pers.xx.edu.entity.MyCounty;
import pers.xx.edu.entity.MyProvince;
import pers.xx.edu.entity.Student;
import pers.xx.edu.service.EduClassService;
import pers.xx.edu.service.FacultyService;
import pers.xx.edu.service.MajorService;
import pers.xx.edu.service.MyCityService;
import pers.xx.edu.service.MyCountyService;
import pers.xx.edu.service.MyProvinceService;
import pers.xx.edu.service.StudentService;
import pers.xx.edu.utils.DateTimeUtils;
import pers.xx.edu.utils.StringUtils;
import pers.xx.edu.utils.UploadUtils;
import pers.xx.edu.vo.EChartsVo;
import pers.xx.edu.vo.EChartsVo2;
import pers.xx.edu.vo.StudentVo;

/**
 * @author XieXing
 * @description 学生接口实现类
 * @create 2019年3月17日 下午6:35:06
 */
@Transactional
@Service("studentService")
public class StudentServiceImpl extends BaseServiceImpl<Student> implements StudentService {
	@Autowired
	private FacultyService facultyService;

	@Autowired
	private MajorService majorService;

	@Autowired
	private EduClassService eduClassService;
	
	@Autowired
	private MyProvinceService myProvinceService;
	
	@Autowired
	private MyCountyService myCountyService;
	
	@Autowired
	private MyCityService myCityService;

	@Resource(name = "studentDao")
	@Override
	protected void setBaseDao(BaseDao<Student> baseDao) {
		super.setBaseDao(baseDao);
	}

	protected StudentDao getBaseDao() {
		return (StudentDao) super.getBaseDao();
	}

	/**
	 * @author XieXing
	 * @createDate 2019年4月11日 下午3:32:42
	 * @description 编辑学生信息
	 * @param studentVo
	 * @param stuEntranceDate
	 * @param stuBirth
	 * @param facultyId
	 * @param majorId
	 * @return
	 */
	@Override
	public void edit(StudentVo studentVo, String stuEntranceDate, String stuBirth, Integer facultyId, Integer majorId,
			Integer stuClassId, Integer provinceId,Integer cityId,Integer countyId,CommonsMultipartFile img, HttpSession session) {
		Student student = null;
		Integer id = studentVo.getId();
		if (id != null) {
			student = getById(id);
			student.setStuPassword(StringUtils.StringToMd5("123456"));
		} else {
			student = new Student();
		}
		BeanUtils.copyProperties(studentVo, student);
		try {
			Date enDate = DateTimeUtils.deal(stuEntranceDate);
			student.setStuEntranceDate(enDate);
		} catch (ParseException e) {
			System.err.println("时间格式不正确！");
		}
		try {
			Date birthDate = DateTimeUtils.deal(stuBirth);
			student.setStuBirth(birthDate);
		} catch (ParseException e) {
			System.err.println("时间格式不正确！");
		}
		if (facultyId != null) {
			Faculty faculty = facultyService.getById(facultyId);
			student.setStuFaculty(faculty);
		}
		if (majorId != null) {
			Major major = majorService.getById(majorId);
			student.setStuMajor(major);
		}
		if (stuClassId != null) {
			EduClass eduClass = eduClassService.getById(stuClassId);
			student.setStuClass(eduClass);
		}
		if(provinceId!=null) {
			MyProvince stuProvince = myProvinceService.getById(provinceId);
			student.setStuProvince(stuProvince);
		}
		if(cityId!=null) {
			MyCity stuCity = myCityService.getById(cityId);
			student.setStuCity(stuCity);
		}
		if(countyId!=null) {
			MyCounty stuCounty = myCountyService.getById(countyId);
			student.setStuCounty(stuCounty);
		}
		if (img != null && !img.isEmpty()) {
			String savePath = UploadUtils.saveFile(img, session, "1");
			student.setStuImg(savePath);
		}
		saveOrUpdate(student);
	}

	@Override
	public Student login(String code, String password) {
		return this.getBaseDao().login(code, password);
	}

	@Override
	public Map<String, Object> getStudentAnalyseData(Integer facId,Integer majId,Integer stuClassId) {
		Map<String, Object> params = new LinkedHashMap<>();
		params.put("stuSex = ?", "男");
		if(facId!=null) {
			params.put("stuFaculty.id = ?", facId);
		}
		if(majId!=null) {
			params.put("stuMajor.id = ?", majId);
		}
		if(stuClassId!=null) {
			params.put("stuClass.id = ?", stuClassId);
		}
		Integer maleNum = getBaseDao().getCount(params);
		params.put("stuSex = ?", "女");
		Integer femaleNum = getBaseDao().getCount(params);
		List<EChartsVo> evs = new ArrayList<>();
		EChartsVo ev = new EChartsVo();
		ev.name="男";
		ev.value=maleNum;
		evs.add(ev);
		EChartsVo ev1 = new EChartsVo();
		ev1.name="女";
		ev1.value=femaleNum;
		evs.add(ev1);
		Map<String, Object> map = new HashMap<>();
		map.put("data",evs);
		return map;
	}

	@Override
	public List<EChartsVo2> getStudentMapData() {
		return this.getBaseDao().getStudentMapData();
	}
}
