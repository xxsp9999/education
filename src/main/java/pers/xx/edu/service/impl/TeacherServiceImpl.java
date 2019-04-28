package pers.xx.edu.service.impl;

import java.text.ParseException;
import java.util.Date;
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
import pers.xx.edu.dao.TeacherDao;
import pers.xx.edu.entity.Faculty;
import pers.xx.edu.entity.LeaderTitle;
import pers.xx.edu.entity.Major;
import pers.xx.edu.entity.TeaTitle;
import pers.xx.edu.entity.Teacher;
import pers.xx.edu.service.FacultyService;
import pers.xx.edu.service.LeaderTitleService;
import pers.xx.edu.service.MajorService;
import pers.xx.edu.service.TeaTitleService;
import pers.xx.edu.service.TeacherService;
import pers.xx.edu.utils.DateTimeUtils;
import pers.xx.edu.utils.StringUtils;
import pers.xx.edu.utils.UploadUtils;
import pers.xx.edu.vo.TeacherVo;

/**
 * @author XieXing
 * @description 老师接口实现类
 * @create 2019年3月17日 下午6:40:01
 */
@Transactional
@Service("teacherService")
public class TeacherServiceImpl extends BaseServiceImpl<Teacher> implements TeacherService {

	@Autowired
	private TeaTitleService teaTitleService;

	@Autowired
	private LeaderTitleService leaderTitleService;
	
	@Autowired
	private FacultyService facultyService;
	
	@Autowired
	private MajorService majorService;

	@Resource(name = "teacherDao")
	@Override
	protected void setBaseDao(BaseDao<Teacher> baseDao) {
		super.setBaseDao(baseDao);
	}

	/**
	 * @author XieXing
	 * @createDate 2019年4月12日 上午10:08:29
	 * @description 编辑教师信息实现类
	 * @param teacherVo
	 * @param teaEntranceDate
	 * @param teaBirth
	 * @param teaTitleId
	 */
	@Override
	protected TeacherDao getBaseDao() {
		return (TeacherDao) super.getBaseDao();
	}

	@Override
	public void edit(TeacherVo teacherVo, String teaEntranceDate, String teaBirth, Integer teaTitleId,
			Integer teaAdTitleId, Integer faculty, Integer major, CommonsMultipartFile img, HttpSession session) {
		Teacher teacher = null;
		Integer id = teacherVo.getId();
		if (id != null) {
			teacher = getById(id);
			teacher.setTeaPassword(StringUtils.StringToMd5("123456"));
		} else {
			teacher = new Teacher();
		}
		BeanUtils.copyProperties(teacherVo, teacher);
		try {
			Date enDate = DateTimeUtils.deal(teaEntranceDate);
			teacher.setTeaEntranceDate(enDate);
		} catch (ParseException e) {
			System.err.println("时间格式不正确！");
		}
		try {
			Date birthDate = DateTimeUtils.deal(teaBirth);
			teacher.setTeaBirth(birthDate);
		} catch (ParseException e) {
			System.err.println("时间格式不正确！");
		}
		if (teaTitleId != null) {
			TeaTitle teaTitle = teaTitleService.getById(teaTitleId);
			teacher.setTeaTitle(teaTitle);
		}
		if (teaAdTitleId != null) {// 设置职务
			LeaderTitle leaderTitle = leaderTitleService.getById(teaAdTitleId);
			teacher.setTeaAdTitle(leaderTitle);
		}
		if (faculty != null) {
			Faculty faculty2 = facultyService.getById(faculty);
			teacher.setTeaFaculty(faculty2);
		}
		if (major != null) {//设置专业
			Major major2 = majorService.getById(major);
			teacher.setTeaMajor(major2);
		}
		if (img != null && !img.isEmpty()) {
			String savePath = UploadUtils.saveFile(img, session, "1");
			teacher.setTeaImg(savePath);
		}
		saveOrUpdate(teacher);
	}

	@Override
	public Teacher login(String code, String password) {
		return this.getBaseDao().login(code, password);
	}

	@Override
	public Teacher getCheckTeacher(Map<String, Object> params) {
		List<Teacher> teachers = this.getBaseDao().getList(params,null);
		if(teachers!=null&&teachers.size()>0) {
			return teachers.get(0);
		}else {
			return null;
		}
	}
}
