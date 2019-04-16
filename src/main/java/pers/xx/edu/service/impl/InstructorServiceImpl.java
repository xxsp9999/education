package pers.xx.edu.service.impl;

import java.text.ParseException;
import java.util.Date;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.dao.InstructorDao;
import pers.xx.edu.entity.Instructor;
import pers.xx.edu.service.InstructorService;
import pers.xx.edu.utils.DateTimeUtils;
import pers.xx.edu.utils.StringUtils;
import pers.xx.edu.vo.InstructorVo;

/**
 * @author XieXing
 * @description 辅导员实现类
 * @create 2019年3月17日 下午6:44:50
 */
@Transactional
@Service
public class InstructorServiceImpl extends BaseServiceImpl<Instructor> implements InstructorService{
	
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
	public void edit(InstructorVo instructorVo, String instructorEntranceDate, String instructorBirth) {
		Instructor instructor = null;
		Integer id = instructorVo.getId();
		if (id != null) {
			instructor = getById(id);
			instructor.setInstructorPassword(StringUtils.StringToMd5("123456"));
		} else {
			instructor = new Instructor();
		}
		BeanUtils.copyProperties(instructorVo, instructor);
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
		saveOrUpdate(instructor);
	}

	@Override
	public Instructor login(String code, String password) {
		return this.getBaseDao().login(code,password);
	}
}
