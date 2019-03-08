package pers.xx.edu.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.entity.Module;
import pers.xx.edu.service.ModuleService;

@Service("moduleService")
@Transactional
public class ModuleServiceImpl extends BaseServiceImpl<Module> implements ModuleService{

	@Resource(name = "moduleDao")
	@Override
	protected void setBaseDao(BaseDao<Module> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	protected BaseDao<Module> getBaseDao() {
		return super.getBaseDao();
	}

}
