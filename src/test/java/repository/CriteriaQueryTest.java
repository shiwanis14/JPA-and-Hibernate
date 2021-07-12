package repository;

import java.util.List;
import com.own.projects.jpademo.JpademoApplication;
import com.own.projects.jpademo.entity.Course;
import com.own.projects.jpademo.entity.Student;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//launch the spring boot context with this class
@SpringBootTest(classes = JpademoApplication.class)
public class CriteriaQueryTest {

  @Autowired
  EntityManager em;
  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Test
  public void contextLoads() {
    logger.info("Test running");
  }


  @Test
  public void selectAllFromCourse() {
  //Use criteria builder to crate criteria to return object of type we need
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Course> cq = cb.createQuery(Course.class);

    //root for which the query is written (from)
    Root<Course> from = cq.from(Course.class);

  //create query with em and criteria query
    TypedQuery<Course> allCourses = em.createQuery(cq.select(from));
    List<Course> result = allCourses.getResultList();
    logger.info("result is {}", result);
  }


  @Test
  public void selectAllFromCourseWhereNameLIike() {
    //Use criteria builder to crate criteria to return object of type we need
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Course> cq = cb.createQuery(Course.class);

    //root for which the query is written (from)
    Root<Course> from = cq.from(Course.class);

    //Define predicates using criteria builder and it to criteria query
    Predicate like = cb.like(from.get("name"), "%Spring Boot%");
    cq.where(like);

    //create query with em and criteria query
    TypedQuery<Course> allCourses = em.createQuery(cq.select(from));
    List<Course> result = allCourses.getResultList();
    logger.info("result is {}", result);
  }


  @Test
  public void selectAllFromCourseWhithoutStudents() {
    //Use criteria builder to crate criteria to return object of type we need
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Course> cq = cb.createQuery(Course.class);

    //root for which the query is written (from)
    Root<Course> from = cq.from(Course.class);

    //Define predicates using criteria builder and it to criteria query
    Predicate isEmpty = cb.isEmpty(from.get("students"));
    cq.where(isEmpty);

    //create query with em and criteria query
    TypedQuery<Course> allCourses = em.createQuery(cq.select(from));
    List<Course> result = allCourses.getResultList();
    logger.info("result is {}", result);
  }




  @Test
  public void selectAllFromCourseJoins() {
    //Use criteria builder to crate criteria to return object of type we need
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Course> cq = cb.createQuery(Course.class);

    //root for which the query is written (from)
    Root<Course> from = cq.from(Course.class);

    //Define predicates using criteria builder and it to criteria query
    Join<Object, Object> students = from.join("students", JoinType.INNER);

    //create query with em and criteria query
    TypedQuery<Course> allCourses = em.createQuery(cq.select(from));
    List<Course> result = allCourses.getResultList();
    logger.info("result is {}", result);
  }



}
