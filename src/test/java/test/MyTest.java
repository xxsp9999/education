package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import pers.xx.edu.entity.Course;
import pers.xx.edu.entity.EduClass;
import pers.xx.edu.entity.Faculty;
import pers.xx.edu.entity.Grade;
import pers.xx.edu.entity.InsGrade;
import pers.xx.edu.entity.Instructor;
import pers.xx.edu.entity.LeaderTitle;
import pers.xx.edu.entity.Major;
import pers.xx.edu.entity.Manager;
import pers.xx.edu.entity.MyCity;
import pers.xx.edu.entity.MyCounty;
import pers.xx.edu.entity.MyProvince;
import pers.xx.edu.entity.StuCourse;
import pers.xx.edu.entity.Student;
import pers.xx.edu.entity.TeaCourse;
import pers.xx.edu.entity.TeaTitle;
import pers.xx.edu.entity.Teacher;
import pers.xx.edu.service.CourseService;
import pers.xx.edu.service.EduClassService;
import pers.xx.edu.service.FacultyService;
import pers.xx.edu.service.GradeService;
import pers.xx.edu.service.InsGradeService;
import pers.xx.edu.service.InstructorService;
import pers.xx.edu.service.MajorService;
import pers.xx.edu.service.ManagerService;
import pers.xx.edu.service.MyCityService;
import pers.xx.edu.service.MyCountyService;
import pers.xx.edu.service.MyProvinceService;
import pers.xx.edu.service.StuCourseService;
import pers.xx.edu.service.StudentService;
import pers.xx.edu.service.TeaCourseService;
import pers.xx.edu.service.TeaTitleService;
import pers.xx.edu.service.TeacherService;
import pers.xx.edu.utils.Java2Word;
import pers.xx.edu.utils.RandomValue;
import pers.xx.edu.utils.StringUtils;
import pers.xx.edu.vo.EChartsVo2;
import pers.xx.edu.vo.InstructorVo2;
import pers.xx.edu.vo.ManagerVo2;
import pers.xx.edu.vo.SelectedCourseVo;
import pers.xx.edu.vo.StudentVo2;
import pers.xx.edu.vo.TeacherVo2;

/**
 * @author XieXing
 * @createDate 2019年5月1日 下午4:41:07
 * @description 单元测试，数据添加
 */
@RunWith(SpringJUnit4ClassRunner.class) // 指定单元测试类
@WebAppConfiguration // autowired WebApplicationContext
@ContextConfiguration(locations = { "classpath:spring-core.xml", "classpath:spring-mvc.xml",
		"classpath:spring-shiro.xml", "classpath:activiti-context.xml" }) // Spring的单元测试注解,指定配置文件所在位置,要将所有配置文件写全，否则会出现java.lang.IllegalStateException:
																			// Failed to load ApplicationContext 的异常
public class MyTest {

	@Autowired
	private FacultyService facultyService;

	@Autowired
	private MajorService majorService;

	@Autowired
	private GradeService gradeService;

	@Autowired
	private EduClassService eduClassService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private MyProvinceService myProvinceService;

	@Autowired
	private MyCityService myCityService;

	@Autowired
	private MyCountyService myCountyServie;

	@Autowired
	private TeaTitleService teaTitleService;

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private InstructorService instructorService;

	@Autowired
	private ManagerService managerService;

	@Autowired
	private InsGradeService insGradeService;

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private TeaCourseService teaCourseService;
	
	@Autowired
	private StuCourseService stuCourseService;
	
	@Before
	public void start() {
		System.out.println("start------");
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月4日 下午4:55:19
	 * @description 添加班级数据
	 */
	//@Test /** 要用时再打开，防止不小心重新运行此项而添加多余的班级 */
	public void insertEduClass() {
		List<Grade> grades = gradeService.getAll();
		String[] cla = { "2015", "2016", "2017", "2018" };
		List<EduClass> eduClasses = new ArrayList<>();
		// 1、获取所有的学院
		List<Faculty> faList = facultyService.getAll();
		int t = 0;
		// 2、根据学院获取专业
		for (Faculty faculty : faList) {
			List<Major> majors = majorService.getMajorsByFacultyId(faculty.getId());
			if (majors == null) {// 以学院命名班级
				List<Teacher> teachers = teacherService.getByFacId(faculty.getId());
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 6; j++) {
						EduClass eduClass = new EduClass();
						eduClass.setClaGrade(grades.get(i));// 设置年级
						eduClass.setClaName(faculty.getFacName() + cla[i].substring(2) + (j + 1));// 设计班级名
						eduClass.setClaNumber(cla[i].substring(2) + (j + 1));
						eduClass.setClaFaculty(faculty);
						eduClass.setClaHeadteacher(teachers.get(RandomValue.getNum(0, teachers.size() - 1)));
						eduClasses.add(eduClass);
						t++;
						// eduClassService.saveByJUnit(eduClass);
					}
				}
			} else {
				for (Major major : majors) {// 以专业命名班级
					List<Teacher> teachers = teacherService.getByMajId(major.getId());
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 6; j++) {
							EduClass eduClass = new EduClass();
							eduClass.setClaGrade(grades.get(i));// 设置年级
							eduClass.setClaName(major.getMajorName() + cla[i].substring(2) + (j + 1));// 设计班级名
							eduClass.setClaNumber(cla[i].substring(2) + (j + 1));
							eduClass.setClaFaculty(faculty);
							eduClass.setClaMajor(major);
							eduClass.setClaHeadteacher(teachers.get(RandomValue.getNum(0, teachers.size() - 1)));
							eduClasses.add(eduClass);
							t++;
							// eduClassService.saveByJUnit(eduClass);
						}
					}
				}
			}
		}
		System.out.println("班级总数：" + t);
		eduClassService.batchSave(eduClasses);
		System.out.println("execute-----");
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月4日 下午4:56:26
	 * @description 添加数学数据
	 */
    //@Test
	public void insertStudent() {
		// 获取入学时间
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<Student> students = new ArrayList<>();
			// 获取省份
			List<MyProvince> myProvinces = myProvinceService.getAll();
			int pLen = myProvinces.size() - 1;
			int count = 0;// 记录学生数
			int index1, index2, index3;// 索引
			// 获取学院
			List<Faculty> faList = facultyService.getAll();
			// 根据学院获取专业
			for (Faculty faculty : faList) {
				List<Major> majors = majorService.getMajorsByFacultyId(faculty.getId());
				if (majors == null) {// 以学院命名班级
					List<EduClass> eduClasses = eduClassService.getByFacId(faculty.getId());
					for (EduClass ec : eduClasses) {
						String gr = 20 + ec.getClaNumber().substring(0, 2);
						for (int i = 0; i < 30; i++) {// 每班30人
							// 获取年级
							Student student = new Student();
							StudentVo2 studentVo2 = RandomValue.getValue();
							BeanUtils.copyProperties(studentVo2, student);
							student.setStuNumber(gr + 0 + (10000 + count));// 设置学号
							student.setStuPassword(StringUtils.StringToMd5("123456"));// 密码就是123456
							student.setStuFaculty(faculty);// 设置学院
							student.setStuClass(ec);// 设置班级
							student.setStuEntranceDate(sdf.parse(gr + "-09-01"));// 设置入学时间

							// 设置地址
							index1 = RandomValue.getNum(0, pLen);
							student.setStuProvince(myProvinces.get(index1));// 省份

							List<MyCity> myCities = myCityService.getByPid(myProvinces.get(index1).getId());//
							index2 = RandomValue.getNum(0, myCities.size() - 1);
							student.setStuCity(myCities.get(index2));// 第二级地名

							List<MyCounty> myCounties = myCountyServie.getByCityId(myCities.get(index2).getId());
							index3 = RandomValue.getNum(0, myCounties.size() - 1);
							student.setStuCounty(myCounties.get(index3));// 第三级地名

							students.add(student);
							count++;
						}
					}
				} else {// 以专业命名班级
					for (Major major : majors) {// 以专业命名班级
						List<EduClass> eduClasses = eduClassService.getByMajId(major.getId());
						for (EduClass ec : eduClasses) {
							String gr = 20 + ec.getClaNumber().substring(0, 2);
							for (int i = 0; i < 30; i++) {// 每班30人
								// 获取年级
								Student student = new Student();
								StudentVo2 studentVo2 = RandomValue.getValue();
								BeanUtils.copyProperties(studentVo2, student);
								student.setStuNumber(gr + 0 + (10000 + count));// 设置学号
								student.setStuPassword(StringUtils.StringToMd5("123456"));// 密码就是123456
								student.setStuFaculty(faculty);// 设置学院
								student.setStuMajor(major);
								student.setStuClass(ec);// 设置班级
								student.setStuEntranceDate(sdf.parse(gr + "-09-01"));// 设置入学时间

								// 设置地址
								index1 = RandomValue.getNum(0, pLen);
								student.setStuProvince(myProvinces.get(index1));// 省份

								List<MyCity> myCities = myCityService.getByPid(myProvinces.get(index1).getId());//
								index2 = RandomValue.getNum(0, myCities.size() - 1);
								student.setStuCity(myCities.get(index2));// 第二级地名

								List<MyCounty> myCounties = myCountyServie.getByCityId(myCities.get(index2).getId());
								index3 = RandomValue.getNum(0, myCounties.size() - 1);
								student.setStuCounty(myCounties.get(index3));// 第三级地名

								students.add(student);
								count++;
							}
						}
					}

				}
			}
			studentService.batchSave(students);
			System.out.println("共有学生：" + count + "人");
		} catch (ParseException e) {
			System.err.println("时间格式错误");
		}
	}

	//@Test
	public void insertTeacher() {
		List<Teacher> teachers = new ArrayList<>();
		List<TeaTitle> teaTitles = teaTitleService.getAll();
		LeaderTitle majorBoss = new LeaderTitle(5, "系主任", "005", "");
		LeaderTitle facultyBoss = new LeaderTitle(3, "院长", "003", "");
		// 1、获取所有的学院
		List<Faculty> faList = facultyService.getAll();
		int count = 0, index1;
		String number = "";
		// 2、根据学院获取专业
		for (Faculty faculty : faList) {
			List<Major> majors = majorService.getMajorsByFacultyId(faculty.getId());
			// 设置一名系主任
			if (majors == null) {// 以学院命名班级
				for (int i = 0; i < 10; i++) {
					Teacher teacher = new Teacher();
					TeacherVo2 teacherVo2 = RandomValue.getTeacherValue();
					BeanUtils.copyProperties(teacherVo2, teacher);
					number = "TE" + 2015 + (1000 + count);
					teacher.setTeaNumber(number);
					teacher.setTeaPassword(StringUtils.StringToMd5(number));
					index1 = RandomValue.getNum(0, 3);
					teacher.setTeaTitle(teaTitles.get(index1));
					if (i == 5)// 设置系主任
						teacher.setTeaAdTitle(facultyBoss);
					teacher.setTeaFaculty(faculty);
					teachers.add(teacher);
					count++;
				}
			} else {
				for (Major major : majors) {// 以专业命名班级
					for (int i = 0; i < 10; i++) {
						Teacher teacher = new Teacher();
						TeacherVo2 teacherVo2 = RandomValue.getTeacherValue();
						BeanUtils.copyProperties(teacherVo2, teacher);
						number = "TE" + 2015 + (1000 + count);
						teacher.setTeaNumber(number);
						teacher.setTeaPassword(StringUtils.StringToMd5(number));
						index1 = RandomValue.getNum(0, 3);
						teacher.setTeaTitle(teaTitles.get(index1));
						teacher.setTeaFaculty(faculty);
						teacher.setTeaMajor(major);
						if (i == 5)
							teacher.setTeaAdTitle(majorBoss);
						teachers.add(teacher);
						count++;
					}
				}
			}
		}
		System.out.println("共有老师：" + count + "人");
		teacherService.batchSave(teachers);
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月5日 下午3:13:14
	 * @description 辅导员信息
	 */
	//@Test
	public void insertInstructor() {
		List<Faculty> faList = facultyService.getAll();
		List<Grade> grades = gradeService.getAll();
		List<InsGrade> insGrades = new ArrayList<>();
		String number = "";
		int count = 0;
		for (Faculty faculty : faList) {
			for (int i = 0; i < 4; i++) {// 一个学院4名辅导员，每个导员一个年级
				Instructor instructor = new Instructor();
				InstructorVo2 instructorVo2 = RandomValue.getInstructorValue();
				BeanUtils.copyProperties(instructorVo2, instructor);
				number = "IN" + "2015" + (2000 + count);
				instructor.setInstructorNumber(number);
				instructor.setInstructorPassword(StringUtils.StringToMd5(number));
				instructor.setInstructorFaculty(faculty);
				instructorService.save(instructor);// 添加导员
				InsGrade insGrade = new InsGrade();//添加班级
				insGrade.setIgGrade(grades.get(i));
				insGrade.setIgInstructor(instructor);
				insGrades.add(insGrade);
				count++;
			}
		}
		System.out.println("共有导员：" + count + "人");
		insGradeService.batchSave(insGrades);
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月6日 下午3:08:34
	 * @description 插入导员--年级
	 */
	//@Test
	public void insertInsGrade() {
		List<Instructor> instructors = instructorService.getAll();
		List<Grade> grades = gradeService.getAll();
		int count = 0, j = 0;
		List<InsGrade> insGrades = new ArrayList<>();
		for (Instructor instructor : instructors) {
			for (j = 0; j < 4; j++) {
				InsGrade insGrade = new InsGrade();
				insGrade.setIgGrade(grades.get(j));
				insGrade.setIgInstructor(instructor);
				insGrades.add(insGrade);
				//insGradeService.save(insGrade);
			}
			if (j == 4) {
				j = 0;
			}
			count++;
		}
		System.out.println("有"+count+"个导员-班级");
		insGradeService.batchSave(insGrades);
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月5日 下午3:14:29
	 * @description 管理员信息
	 */
	// @Test
	public void insertManager() {
		List<Manager> managers = new ArrayList<>();
		int i = 0;
		String number = "";
		for (i = 0; i < 10; i++) {
			Manager manager = new Manager();
			ManagerVo2 managerVo2 = RandomValue.getManagerValue();
			BeanUtils.copyProperties(managerVo2, manager);
			number = "AD" + "2015" + (3000 + i);
			manager.setManagerNumber(number);
			manager.setManagerPassword(StringUtils.StringToMd5(number));
			managers.add(manager);	
		}
		System.out.println("共有管理员：" + i + "人");
		managerService.batchSave(managers);
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月5日 下午3:58:00
	 * @description 修改学生密码
	 */
	//@Test
	public void updatePassword() {
		List<Student> students = studentService.getAll();
		for(Student student:students) {
			student.setStuPassword(StringUtils.StringToMd5(student.getStuNumber()));
		}
	}
	
	//@Test
	public void testStuDao() {
		List<EChartsVo2> eChartsVo2s = studentService.getStudentMapData();
		System.out.println(eChartsVo2s);
		System.out.println("测试成功");
	}

	/**
	 * @author XieXing
	 * @createDate 2019年5月13日 上午11:13:00
	 * @description 批量插入课程
	 */
	//@Test
	public void addCourse() {
		String[] scores = {"1","1.5","2","0.5","2.5","3","3.5","4","5"}; 
		List<Course> courses = new ArrayList<>();
		String content = Java2Word.readWord("D:\\毕业设计\\毕业设计完成进度（重要）\\相关数据\\大学常见课程.doc");
		String[] strs = content.split("\\n");
		String ChName = "";
		String EnName = "";
		int count = 1000;
		int index1;
		for (String url : strs) {
			String str = Java2Word.getChinese(url);
			if (str!=null && str.length() > 0) {
				count++;
				Course course = new Course();
				str = str.substring(str.length() - 1, str.length());
				Integer index = url.indexOf(str);
				ChName = url.substring(0, index + 1);
				course.setcName(ChName);
				EnName = url.substring(index + 1);
				course.setcEnglishName(EnName);
				course.setcNo(count+"");
				index1 = RandomValue.getNum(0, 8);
				course.setcScore(scores[index1]);
				courses.add(course);
			}
		}
		courseService.batchSave(courses);
	}
	
	/**
	 * @author XieXing
	 * @createDate 2019年5月14日 下午3:29:04
	 * @description 插入老师课程数据 插入学生学生课程数据
	 */
	//@Test 
	public void addTeaCourse() {
		List<Teacher> teachers = teacherService.getAll();
		List<Course> courses = courseService.getAll();
		List<TeaCourse> teaCourses = new ArrayList<>();
		
	}
	//@Test
	public void testGetByTid() {
		List<StuCourse> stuCourses = new ArrayList<>();
		List<SelectedCourseVo> list = teaCourseService.getByTeacherId(1);
		System.out.println(list);
	}
	@After
	public void end() {
		System.out.println("end------");
	}
}
