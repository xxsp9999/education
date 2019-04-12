package pers.xx.edu.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.dao.TeaTitleDao;
import pers.xx.edu.entity.TeaTitle;
import pers.xx.edu.service.TeaTitleService;

/**
 * @author XieXing
 * @createDate 2019年4月12日 上午9:35:09
 * @description 教师职称实现类
 */
@Transactional
@Service("teaTitleService")
public class TeaTitleServiceImpl extends BaseServiceImpl<TeaTitle> implements TeaTitleService{
	@Resource(name = "teaTitleDao")
	@Override
	protected void setBaseDao(BaseDao<TeaTitle> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	protected TeaTitleDao getBaseDao() {
		return (TeaTitleDao) super.getBaseDao();
	}
}
