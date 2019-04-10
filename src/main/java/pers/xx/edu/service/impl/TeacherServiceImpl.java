package pers.xx.edu.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.dao.TeacherDao;
import pers.xx.edu.entity.Teacher;
import pers.xx.edu.service.TeacherService;

/**
 * @author XieXing
 * @description 老师接口实现类
 * @create 2019年3月17日 下午6:40:01
 */
@Transactional
@Service("teacherService")
public class TeacherServiceImpl extends BaseServiceImpl<Teacher> implements TeacherService{
	@Resource(name = "teacherDao")
	@Override
	protected void setBaseDao(BaseDao<Teacher> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	protected TeacherDao getBaseDao() {
		return (TeacherDao) super.getBaseDao();
	}
}
