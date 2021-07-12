package repository;

import java.util.List;
import com.own.projects.jpademo.JpademoApplication;
import com.own.projects.jpademo.entity.Course;
import com.own.projects.jpademo.entity.Student;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.Query;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//launch the spring boot context with this class
@SpringBootTest(classes = JpademoApplication.class)
public class JPQLTest {

  @Autowired
  EntityManager em;
  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Test
  public void contextLoads() {
    logger.info("Test running");
  }


  @Test
  public void findById_basic() {
//		List result = em.createQuery("Select c from Course c").getResultList();
    List result = em.createNamedQuery("get_all_courses").getResultList();
    logger.info("result is {}", result);
  }

  @Test
  public void findById_typed() {
    TypedQuery<Course> result = em
        .createQuery("Select c from Course c where c.name like 'JPA%'", Course.class);
    List m = result.getResultList();
    logger.info("result is {}", m);
  }

  @Test
  //select * from course where id not in(select course_id from student_course)
  public void courseWithoutStudents() {
    TypedQuery<Student> result = em
        .createQuery("Select c from Student c where c.passport.number like '%1234%'",
            Student.class);
    List<Student> m = result.getResultList();
    logger.info("result is {}", m);
  }


  @Test
  public void joinsDemo() {
    Query query = em.createQuery("Select c,s from Course c join c.students s");
    List<Object[]> m = query.getResultList();
    logger.info("result size {}", m.size());
    for(Object[] o : m) { //each element is an array 1st is course and 2nd is students
			logger.info("course is {}",o[0]);
			logger.info("student is {}",o[1]);
		}
  }
  @Test
  public void leftjoinsDemo() {
    Query query = em.createQuery("Select c,s from Course c left join c.students s");
    List<Object[]> m = query.getResultList();
    logger.info("result size {}", m.size());
    for(Object[] o : m) { //each element is an array 1st is course and 2nd is students
			logger.info("course is {}",o[0]);
			logger.info("student is {}",o[1]);
		}
  }

}
