package pers.xx.edu.service;

import java.util.List;

import pers.xx.edu.entity.DepartmentRole;

public interface DepartmentRoleService extends BaseService<DepartmentRole> {
	

	public void deleteByDepartmentId(int departmentId);
	
	public List<DepartmentRole> getByDepartmentId(int departmentId);

}
