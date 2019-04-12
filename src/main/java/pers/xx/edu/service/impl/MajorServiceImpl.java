package pers.xx.edu.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.dao.MajorDao;
import pers.xx.edu.entity.Major;
import pers.xx.edu.service.MajorService;

/**
 * @author XieXing
 * @createDate 2019年4月9日 下午5:20:00
 * @description 专业接口实现类
 */
@Transactional
@Service("majorService")
public class MajorServiceImpl extends BaseServiceImpl<Major> implements MajorService{
	@Resource(name = "majorDao")
	@Override
	protected void setBaseDao(BaseDao<Major> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	protected MajorDao getBaseDao() {
		return (MajorDao) super.getBaseDao();
	}

	@Override
	public Major getMajorBymajorNumber(String majorNumber) {
		return getBaseDao().getMajorBymajorNumber(majorNumber);
	}

	@Override
	public List<Major> getMajorsByFacultyId(Integer facId) {
		return getBaseDao().getMajorsByFacultyId(facId);
	}

}
