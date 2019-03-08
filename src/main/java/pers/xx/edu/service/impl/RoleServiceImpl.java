package pers.xx.edu.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.entity.Role;
import pers.xx.edu.service.RoleService;

@Transactional
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

	@Resource(name = "roleDao")
	@Override
	protected void setBaseDao(BaseDao<Role> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	protected BaseDao<Role> getBaseDao() {
		return super.getBaseDao();
	}


}
