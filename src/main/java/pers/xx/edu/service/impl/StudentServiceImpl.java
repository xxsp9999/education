package pers.xx.edu.service.impl;

import java.text.ParseException;
import java.util.Date;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.dao.StudentDao;
import pers.xx.edu.entity.Faculty;
import pers.xx.edu.entity.Major;
import pers.xx.edu.entity.Student;
import pers.xx.edu.service.FacultyService;
import pers.xx.edu.service.MajorService;
import pers.xx.edu.service.StudentService;
import pers.xx.edu.utils.DateTimeUtils;
import pers.xx.edu.utils.StringUtils;
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
	public void edit(StudentVo studentVo, String stuEntranceDate, String stuBirth, Integer facultyId, Integer majorId) {
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
		saveOrUpdate(student);
	}
}
