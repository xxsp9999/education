package pers.xx.edu.service.impl;

import java.text.ParseException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.dao.InstructorDao;
import pers.xx.edu.entity.Faculty;
import pers.xx.edu.entity.Instructor;
import pers.xx.edu.service.FacultyService;
import pers.xx.edu.service.InstructorService;
import pers.xx.edu.utils.DateTimeUtils;
import pers.xx.edu.utils.StringUtils;
import pers.xx.edu.utils.UploadUtils;
import pers.xx.edu.vo.InstructorVo;

/**
 * @author XieXing
 * @description 辅导员实现类
 * @create 2019年3月17日 下午6:44:50
 */
@Transactional
@Service("instructorService")
public class InstructorServiceImpl extends BaseServiceImpl<Instructor> implements InstructorService {

	@Autowired
	private FacultyService facultyService;
	
	@Resource(name = "instructorDao")
	@Override
	protected void setBaseDao(BaseDao<Instructor> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	protected InstructorDao getBaseDao() {
		return (InstructorDao) super.getBaseDao();
	}

	/**
	 * @author XieXing
	 * @createDate 2019年4月12日 上午11:19:55
	 * @description 导员信息编辑
	 * @param instructorVo
	 * @param instructorEntranceDate
	 * @param instructorBirth
	 */
	@Override
	public void edit(InstructorVo instructorVo, Integer facultyId, String instructorEntranceDate,
			String instructorBirth, CommonsMultipartFile img, HttpSession session) {
		Instructor instructor = null;
		Integer id = instructorVo.getId();
		if (id != null) {
			instructor = getById(id);
			instructor.setInstructorPassword(StringUtils.StringToMd5("123456"));
		} else {
			instructor = new Instructor();
		}
		BeanUtils.copyProperties(instructorVo, instructor);
		if (facultyId != null) {
			Faculty faculty = facultyService.getById(facultyId);
			instructor.setInstructorFaculty(faculty);
		}
		try {
			Date enDate = DateTimeUtils.deal(instructorEntranceDate);
			instructor.setInstructorEntranceDate(enDate);
		} catch (ParseException e) {
			System.err.println("时间格式不正确！");
		}
		try {
			Date birthDate = DateTimeUtils.deal(instructorBirth);
			instructor.setInstructorBirth(birthDate);
		} catch (ParseException e) {
			System.err.println("时间格式不正确！");
		}
		if (img != null && !img.isEmpty()) {
			String savePath = UploadUtils.saveFile(img, session, "1");
			instructor.setInstructorImg(savePath);
		}
		saveOrUpdate(instructor);
	}

	@Override
	public Instructor login(String code, String password) {
		return this.getBaseDao().login(code, password);
	}

	@Override
	public String getByFacultyIdAndGradeId(Integer facultyId, Integer gradeId) {
		return this.getBaseDao().getByFacultyIdAndGradeId(facultyId, gradeId);
	}

	@Override
	public Instructor getByInstructorNumber(String number) {
		return this.getBaseDao().getByInstructorNumber(number);
	}
}
