package pers.xx.edu.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.dao.StuCourseDao;
import pers.xx.edu.entity.StuCourse;
import pers.xx.edu.service.StuCourseService;

/**
 * @author XieXing
 * @createDate 2019年5月14日 下午2:44:00
 * @description 
 */
@Transactional
@Service("stuCourseService")
public class StuCourseServiceImpl extends BaseServiceImpl<StuCourse> implements StuCourseService{
	@Resource(name = "stuCourseDao")
	@Override
	protected void setBaseDao(BaseDao<StuCourse> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	protected StuCourseDao getBaseDao() {
		return (StuCourseDao) super.getBaseDao();
	}

	@Override
	public StuCourse getByStuIdAndTeaCourseId(Integer stuId,Integer teaCourseId) {
		return this.getBaseDao().getByTeaCourseId(stuId,teaCourseId);
	}

	@Override
	public void deleteByTeaCourseIds(Integer id) {
		this.getBaseDao().deleteByTeaCourseIds(id);
	}
}
