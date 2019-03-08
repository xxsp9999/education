package pers.xx.edu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.dao.DepartmentRoleDao;
import pers.xx.edu.entity.DepartmentRole;
import pers.xx.edu.service.DepartmentRoleService;

@Transactional
@Service("departmentRoleService")
public class DepartmentRoleServiceImpl extends BaseServiceImpl<DepartmentRole> implements DepartmentRoleService {

	@Resource(name = "departmentRoleDao")
	@Override
	protected void setBaseDao(BaseDao<DepartmentRole> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	protected DepartmentRoleDao getBaseDao() {
		return (DepartmentRoleDao)super.getBaseDao();
	}

	@Override
	public void deleteByDepartmentId(int departmentId) {
		this.getBaseDao().deleteByDepartmentId(departmentId);
	}

	@Override
	public List<DepartmentRole> getByDepartmentId(int departmentId) {
		return getBaseDao().getByDepartmentId(departmentId);
	}


}
