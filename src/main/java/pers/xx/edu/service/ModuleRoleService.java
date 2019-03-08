package pers.xx.edu.service;

import java.util.List;

import pers.xx.edu.entity.ModuleRole;

public interface ModuleRoleService extends BaseService<ModuleRole> {

	void deleteByRoleId(int roleId);
	
	public List<ModuleRole> getByRoleId(int roleId);
}
