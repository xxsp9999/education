package pers.xx.edu.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import pers.xx.edu.entity.Company;

/**
 * @author XieXing
 * @createDate 2019年5月11日 下午4:12:00
 * @description 公司DAO
 */
@Repository
public class CompanyDao extends BaseDao<Company>{
	/**
	 * @author XieXing
	 * @createDate 2019年5月25日 下午7:47:41
	 * @description 公司账号登陆
	 * @param code
	 * @param userPassword
	 * @return
	 */
	public Company login(String code, String userPassword) {
		String hql = "from Company where companyNumber = ? and companyPassword = ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, code);
		query.setString(1, userPassword);
		if(query.list().size()==0){
			return null;
		}
		return (Company) query.list().get(0);
	}
}
