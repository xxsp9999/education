package pers.xx.edu.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.entity.Student;
import pers.xx.edu.service.StudentService;

/**
 * @author XieXing
 * @description 学生接口实现类
 * @create 2019年3月17日 下午6:35:06
 */
@Transactional
@Service("studentService")
public class StudentServiceImpl extends BaseServiceImpl<Student> implements StudentService{

	@Resource(name = "studentDao")
	@Override
	protected void setBaseDao(BaseDao<Student> baseDao) {
		super.setBaseDao(baseDao);
	}

	protected BaseDao<Student> getBaseDao() {
		return super.getBaseDao();
	}
}
