package pers.xx.edu.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.dao.TeaCourseDao;
import pers.xx.edu.entity.TeaCourse;
import pers.xx.edu.service.TeaCourseService;
import pers.xx.edu.vo.SelectedCourseVo;

/**
 * @author XieXing
 * @createDate 2019年5月14日 下午2:40:13
 * @description 老师-课程实现类
 */
@Transactional
@Service("teaCourseService")
public class TeaCourseServiceImpl extends BaseServiceImpl<TeaCourse> implements TeaCourseService {
	@Resource(name = "teaCourseDao")
	@Override
	protected void setBaseDao(BaseDao<TeaCourse> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	protected TeaCourseDao getBaseDao() {
		return (TeaCourseDao) super.getBaseDao();
	}

	@Override
	public TeaCourse isScheduled(Integer courseId, Integer teacherId, String tcSeason) {
		return this.getBaseDao().isScheduled(courseId, teacherId, tcSeason);
	}

	@Override
	public List<SelectedCourseVo> getByTeacherId(Integer teacherId) {
		return this.getBaseDao().getByTeacherId(teacherId);
	}

}
