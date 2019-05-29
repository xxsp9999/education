package pers.xx.edu.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import pers.xx.edu.dao.AttachmentDao;
import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.entity.Attachment;
import pers.xx.edu.service.AttachmentService;

/**
 * @author XieXing
 * @createDate 2019年5月17日 下午5:06:50
 * @description 课件文件实现类
 */
@Transactional
@Service("attachmentService")
public class AttachmentServiceImpl extends BaseServiceImpl<Attachment> implements AttachmentService{
	@Resource(name = "attachmentDao")
	@Override
	protected void setBaseDao(BaseDao<Attachment> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	protected AttachmentDao getBaseDao() {
		return (AttachmentDao) super.getBaseDao();
	}

	@Override
	public List<Attachment> getByCoursewareId(Integer coursewareId) {
		return this.getBaseDao().getByCoursewareId(coursewareId);
	}
}
