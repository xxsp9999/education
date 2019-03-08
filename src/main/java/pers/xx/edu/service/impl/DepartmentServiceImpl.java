package pers.xx.edu.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.entity.Department;
import pers.xx.edu.service.DepartmentService;

@Transactional
@Service("departmentService")
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements DepartmentService {

	@Resource(name = "departmentDao")
	@Override
	protected void setBaseDao(BaseDao<Department> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	protected BaseDao<Department> getBaseDao() {
		return super.getBaseDao();
	}


}
