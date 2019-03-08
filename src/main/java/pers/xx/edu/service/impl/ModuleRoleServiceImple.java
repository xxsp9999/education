package pers.xx.edu.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.dao.ModuleRoleDao;
import pers.xx.edu.entity.ModuleRole;
import pers.xx.edu.service.ModuleRoleService;


@Transactional
@Service("moduleRoleService")
public class ModuleRoleServiceImple extends BaseServiceImpl<ModuleRole> implements ModuleRoleService {

	@Resource(name = "moduleRoleDao")
	@Override
	protected void setBaseDao(BaseDao<ModuleRole> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	protected ModuleRoleDao getBaseDao() {
		return (ModuleRoleDao) super.getBaseDao();
	}

	@Override
	public void deleteByRoleId(int roleId) {
		this.getBaseDao().deleteByRoleId(roleId);
	}

	@Override
	public List<ModuleRole> getByRoleId(int roleId) {
		return 	this.getBaseDao().getByRoleId(roleId);
	}



}
