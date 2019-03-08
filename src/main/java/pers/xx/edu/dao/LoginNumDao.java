package pers.xx.edu.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import pers.xx.edu.entity.LoginNum;

@Repository
public class LoginNumDao extends BaseDao<LoginNum>{
	@Override
	public Session getSession() {
		return super.getSession();
	}
	
	public List find(String numberorname,Date begin,Date end){
		String hql="From LoginNum where 1=1 ";
		if(numberorname!=null&&!numberorname.equals("")){
			hql="and number like '%" + numberorname + "%'";
		}
		if(begin!=null&&end!=null){
			hql="and login_time in(begin,end) ";
		}
		Query query = getSession().createQuery(hql);
		List<LoginNum> list=query.list();
		return list;
		
	}
}
