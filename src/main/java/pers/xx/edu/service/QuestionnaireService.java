package pers.xx.edu.service;

import pers.xx.edu.entity.Questionnaire;

/**
 * @author XieXing
 * @createDate 2019年5月28日 下午2:48:35
 * @description 问卷调查接口
 */
public interface QuestionnaireService extends BaseService<Questionnaire>{
	
	/**
	 * @author XieXing
	 * @createDate 2019年5月28日 下午3:30:28
	 * @description 根据学生id获取其调查问卷
	 * @param stuId
	 * @return
	 */
	Questionnaire getByStuId(Integer stuId);

}
