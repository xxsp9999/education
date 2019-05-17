package pers.xx.edu.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.dao.EduClassDao;
import pers.xx.edu.entity.EduClass;
import pers.xx.edu.entity.Grade;
import pers.xx.edu.entity.Teacher;
import pers.xx.edu.service.EduClassService;
import pers.xx.edu.service.GradeService;
import pers.xx.edu.service.TeacherService;
import pers.xx.edu.vo.EduClassVo;

/**
 * @author XieXing
 * @createDate 2019年4月19日 下午4:16:26
 * @description 班级接口实现类
 */
@Transactional
@Service("eduClassService")
public class EduClassServiceImpl extends BaseServiceImpl<EduClass> implements EduClassService{
	@Autowired
	private GradeService gradeService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Resource(name = "eduClassDao")
	@Override
	protected void setBaseDao(BaseDao<EduClass> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	protected EduClassDao getBaseDao() {
		return (EduClassDao) super.getBaseDao();
	}

	@Override
	public void edit(EduClassVo eduClassVo, Integer gradeId, Integer teaId) {
		Integer id = eduClassVo.getId();
		EduClass eduClass = null;
		if(id==null) {
			eduClass = new EduClass();
		}
		else {
			eduClass = getById(id);
		}
		BeanUtils.copyProperties(eduClassVo, eduClass);
		if(gradeId!=null) {
			Grade grade = gradeService.getById(gradeId);
			eduClass.setClaGrade(grade);
		}
		if(teaId!=null) {
			Teacher teacher = teacherService.getById(teaId);
			eduClass.setClaHeadteacher(teacher);
		}
		saveOrUpdate(eduClass);
	}

	@Override
	public List<EduClass> getByLikeContent(String str) {
		return this.getBaseDao().getByLikeContent(str);
	}

	@Override
	public List<EduClass> getByFacId(Integer facId) {
		return this.getBaseDao().getByFacId(facId);
	}

	@Override
	public List<EduClass> getByMajId(Integer majId) {
		return this.getBaseDao().getByMajId(majId);
	}
}
