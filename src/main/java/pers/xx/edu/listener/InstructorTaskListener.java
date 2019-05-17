package pers.xx.edu.listener;


import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import pers.xx.edu.entity.Student;
import pers.xx.edu.filter.RequestFilter;
import pers.xx.edu.service.InstructorService;
import pers.xx.edu.utils.SpringContextUtil;

/**
 * @author XieXing
 * @createDate 2019年4月21日 上午9:58:48
 * @description 导员任务分配
 */
public class InstructorTaskListener implements TaskListener {

	private static final long serialVersionUID = 1L;

	/**
	 * 设置学生提交请假后的审批对象
	 */
	@Override
	public void notify(DelegateTask delegateTask) {
		HttpServletRequest request = RequestFilter.threadLocalRequest.get();
		Student student = (Student) request.getSession().getAttribute("student");
		int facultyId = student.getStuFaculty().getId();//获取学院Id
		int gradeId = student.getStuClass().getClaGrade().getId();//获取年级Id
		InstructorService instructorService = SpringContextUtil.getBean("instructorService");
		String instructorNumber = instructorService.getByFacultyIdAndGradeId(facultyId,gradeId);
		delegateTask.setAssignee(instructorNumber+"");//设置办理人导员的编号,不能设置为名字，谨防重名
	}
}
