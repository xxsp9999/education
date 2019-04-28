package pers.xx.edu.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import pers.xx.edu.entity.Instructor;

/**
 * @author XieXing
 * @description 辅导员DAO
 * @create 2019年3月17日 下午6:25:23
 */
@Repository
public class InstructorDao extends BaseDao<Instructor>{
	
	/**
	 * @author XieXing
	 * @createDate 2019年4月15日 上午9:30:15
	 * @description 辅导员登陆
	 * @param code
	 * @param userPassword
	 * @return
	 */
	public Instructor login(String code, String userPassword) {
		String hql = "from Instructor where instructorNumber = ? and instructorPassword = ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, code);
		query.setString(1, userPassword);
		if(query.list().size()==0){
			return null;
		}
		return (Instructor) query.list().get(0);
	}
	
	/**
	 * @author XieXing
	 * @createDate 2019年4月22日 下午4:15:14
	 * @description 根据学院和年级获取导员Id
	 * @param facultyId
	 * @param gradeId
	 * @return
	 */
	public String getByFacultyIdAndGradeId(Integer facultyId,Integer gradeId) {
		String hql = "select ins.instructorNumber from Instructor as ins,InsGrade as ig where ins.instructorFaculty = ? and ins.id = ig.igInstructor and ig.id = ?";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, facultyId);
		query.setInteger(1, gradeId);
		if(query.list().size()==0){
			return null;
		}
		return query.list().get(0)+"";
	}

	/**
	 * @author XieXing
	 * @createDate 2019年4月22日 下午4:46:43
	 * @description 根据导员编号获取导员信息
	 * @param number
	 * @return
	 */
	public Instructor getByInstructorNumber(String number) {
		String hql = "from Instructor where instructorNumber = ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, number);
		if(query.list().size()==0){
			return null;
		}
		return (Instructor)query.list().get(0);
	}
}
