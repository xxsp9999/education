package pers.xx.edu.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import pers.xx.edu.entity.Teacher;
import pers.xx.edu.filter.RequestFilter;
import pers.xx.edu.service.TeacherService;
import pers.xx.edu.utils.SpringContextUtil;

/**
 * @author XieXing
 * @createDate 2019年4月21日 上午10:00:14
 * @description 领导任务分配
 */
public class LeaderTaskListener implements TaskListener {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void notify(DelegateTask delegateTask) {
		HttpServletRequest request = RequestFilter.threadLocalRequest.get();
		int facultyId = (int) request.getSession().getAttribute("facultyId");//获取学院Id
		int majorId = (int) request.getSession().getAttribute("majorId");//获取专业id
		TeacherService teacherService = SpringContextUtil.getBean("teacherService");
		Map<String, Object> params = new HashMap<>();
		params.put("teaAdTitle.id = ?",5);//系主任审核
		params.put("teaFaculty.id = ?",facultyId);
		params.put("teaMajor.id = ?",majorId);
		Teacher teacher = teacherService.getCheckTeacher(params);//获取审核老师
		delegateTask.setAssignee(teacher.getTeaNumber());
	}

}
