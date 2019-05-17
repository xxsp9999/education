package pers.xx.edu.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.dao.CourseDao;
import pers.xx.edu.entity.Course;
import pers.xx.edu.service.CourseService;

/**
 * @author XieXing
 * @createDate 2019年5月2日 上午10:37:09
 * @description 课程接口实现类
 */
@Transactional
@Service("courseService")
public class CourseServiceImpl extends BaseServiceImpl<Course> implements CourseService {
	@Resource(name = "courseDao")
	@Override
	protected void setBaseDao(BaseDao<Course> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	protected CourseDao getBaseDao() {
		return (CourseDao) super.getBaseDao();
	}
	
}
