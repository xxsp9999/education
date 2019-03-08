package pers.xx.edu.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import pers.xx.edu.entity.ModuleRole;

@Repository
public class ModuleRoleDao extends BaseDao<ModuleRole>{
	
	
	/**
	 * @description 根据角色id删除某个角色的权限
	 * @param departmentId
	 */
	public void deleteByRoleId(int roleId){
		String hql = "delete from ModuleRole where role.id = :id";
		Query query = getSession().createQuery(hql);
		query.setParameter("id", roleId);
		query.executeUpdate();
	}

	/**
	 * @description 根据角色id获取角色模块的记录
	 * @param departmentId
	 */
	public List<ModuleRole> getByRoleId(int roleId) {
		String hql = "from ModuleRole where role.id = :id";
		Query query = getSession().createQuery(hql);
		query.setParameter("id", roleId);
		return query.list();
	}
}
