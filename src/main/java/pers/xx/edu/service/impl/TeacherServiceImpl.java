package pers.xx.edu.service.impl;

import java.text.ParseException;
import java.util.Date;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.dao.TeacherDao;
import pers.xx.edu.entity.TeaTitle;
import pers.xx.edu.entity.Teacher;
import pers.xx.edu.service.TeaTitleService;
import pers.xx.edu.service.TeacherService;
import pers.xx.edu.utils.DateTimeUtils;
import pers.xx.edu.utils.StringUtils;
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
	public void edit(TeacherVo teacherVo, String teaEntranceDate, String teaBirth, Integer teaTitleId) {
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
		saveOrUpdate(teacher);
	}
}
