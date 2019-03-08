package pers.xx.edu.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import pers.xx.edu.entity.DepartmentRole;

@Repository
public class DepartmentRoleDao extends BaseDao<DepartmentRole>{

	
	/**
	 * @description 根据部门id删除某个部门的角色
	 * @param departmentId
	 */
	public void deleteByDepartmentId(int departmentId){
		String hql = "delete from DepartmentRole where department.id = :id";
		Query query = getSession().createQuery(hql);
		query.setParameter("id", departmentId);
		query.executeUpdate();
	}

	/**
	 * @description 根据部门id获取所有的部门角色记录
	 * @param departmentId
	 */
	@SuppressWarnings("unchecked")
	public List<DepartmentRole> getByDepartmentId(int departmentId) {
		String hql = "from DepartmentRole where department.id = :id";
		Query query = getSession().createQuery(hql);
		query.setParameter("id", departmentId);
		return query.list();
	}
}
