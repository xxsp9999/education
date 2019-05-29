package pers.xx.edu.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import pers.xx.edu.entity.Attachment;
/**
 * @author XieXing
 * @description 课件文件信息DAO类
 * @create 2019年3月4日
 */

@Repository
public class AttachmentDao extends BaseDao<Attachment>{

	/**
	 * @author XieXing
	 * @createDate 2019年5月18日 下午4:32:56
	 * @description 根据课件上传元数据id获取附件
	 * @param coursewareId
	 * @return
	 */
	public List<Attachment> getByCoursewareId(Integer coursewareId){
		String hql ="from Attachment where courseware = ?";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, coursewareId);
		return query.list();
	}
}
