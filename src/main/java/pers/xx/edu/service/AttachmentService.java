package pers.xx.edu.service;

import java.util.List;

import pers.xx.edu.entity.Attachment;

/**
 * @author XieXing
 * @createDate 2019年5月17日 下午5:02:52
 * @description 课件文件信息操作接口
 */
public interface AttachmentService extends BaseService<Attachment> {
	/**
	 * @author XieXing
	 * @createDate 2019年5月18日 下午4:31:01
	 * @description 根据课件上传元数据id获取附件
	 * @param coursewareId
	 * @return
	 */
	List<Attachment> getByCoursewareId(Integer coursewareId);

}
