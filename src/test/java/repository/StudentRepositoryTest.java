package repository;

import com.own.projects.jpademo.JpademoApplication;
import com.own.projects.jpademo.entity.Address;
import com.own.projects.jpademo.entity.Passport;
import com.own.projects.jpademo.entity.Student;
import com.own.projects.jpademo.repository.StudentRepository;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

//launch the spring boot context with this class
@SpringBootTest(classes = JpademoApplication.class)
public class StudentRepositoryTest {

  @Autowired
  StudentRepository repository;
  @Autowired
  EntityManager em;
  private Logger logger = LoggerFactory.getLogger(this.getClass());
  private Student student;

  @Test
  public void contextLoads() {
    logger.info("Test running");
  }


  //eager vs lazy fetch
  @Test
  @Transactional
  public void getStudentAndPassport() {
    Student s = em.find(Student.class, 20001L);
    logger.info("student values: " + s);
    logger.info("student passport: " + s.getPassport());
  }


  //eager vs lazy fetch
  @Test
  @Transactional
  public void getPassportAndStudent() {
    Passport p = em.find(Passport.class, 40002L);
    logger.info("passport values: " + p);
    logger.info("student : " + p.getStudent());
  }


  @Test
  @Transactional
  public void retrieveStudentAndCourse() {
    Student student = em.find(Student.class, 20001L);
    logger.info("{}", student);
    logger.info("{}", student.getCourses());
  }


  @Test
  @Transactional
  public void saveAddesss() {
    student = em.find(Student.class, 20001L);
    student.setAddress(new Address("28", "Montessori", "Patna"));
    em.flush();
    logger.info("{}", student);
  }

//	@Test
//	@Transactional
//	@DirtiesContext
//	public void performance() {
//		//for (int i = 0; i < 20; i++)
//			//em.persist(new Course("Something" + i));
//		//em.flush();
//
//		//EntityGraph graph = em.getEntityGraph("graph.CourseAndStudents");
//
//		EntityGraph<Course> graph = em.createEntityGraph(Course.class);
//	    Subgraph<List<Student>> bookSubGraph = graph.addSubgraph("students");
//
//	    List<Course> courses = em.createQuery("Select c from Course c", Course.class)
//	        .setHint("javax.persistence.loadgraph", graph)
//	        .getResultList();
//	    for (Course course : courses) {
//	      System.out.println(course + " " + course.getStudents());
//	    }
//	}
//
//	@Test
//	@Transactional
//	@DirtiesContext
//	public void performance_without_hint() {
//	    List<Course> courses = em.createQuery("Select c from Course c", Course.class)
//	        //.setHint("javax.persistence.loadgraph", graph)
//	        .getResultList();
//	    for (Course course : courses) {
//	      System.out.println(course + " " + course.getStudents());
//	    }
//	}

}
