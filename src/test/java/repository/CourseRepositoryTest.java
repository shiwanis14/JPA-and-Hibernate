package repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import com.own.projects.jpademo.JpademoApplication;
import com.own.projects.jpademo.entity.Course;
import com.own.projects.jpademo.entity.Review;
import com.own.projects.jpademo.repository.CourseRepository;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Subgraph;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

//launch the spring boot context with this class
@SpringBootTest(classes = JpademoApplication.class)
public class CourseRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseRepository repository;

	@Autowired
	EntityManager em;



	@Test
	public void contextLoads() {
		logger.info("Test running");
	}


	@Test
	public void findById_basic() {
		Course course = repository.findById(10001L);
		logger.info("findById_basic running");
		assertEquals("JPA in 50 Steps", course.getName());
	}


	@Test
	@DirtiesContext
	public void deleteById_basic() {
		repository.deleteById(10001L);
		Course course = repository.findById(10001L);
		assertNull(course);
	}


	@Test
	@DirtiesContext
	public void saveById_basic() {

		//get
		Course course = repository.findById(10001L);
		assertEquals("JPA in 50 Steps", course.getName());

		// update it
		course.setName("JPA in 50 Steps - Updated");
		repository.insertOrUpdateById(course);

		// check the value with uodated
		Course course1 = repository.findById(10001L);
		assertEquals("JPA in 50 Steps - Updated", course1.getName());
	}


	@Test
	@DirtiesContext
	public void playWithEntityManager() {
		repository.playWithEntityManager();
	}

	@Test
	@Transactional
	public void findById_firstLevelCacheDemo() {

		Course course = repository.findById(10001L);
		logger.info("First Course Retrieved {}", course);

		//no query runs here
		Course course1 = repository.findById(10001L);
		logger.info("First Course Retrieved again {}", course1);

		assertEquals("JPA in 50 Steps", course.getName());
		assertEquals("JPA in 50 Steps", course1.getName());
	}


//
////lazy fetch 1:Many
//	@Test
//	@Transactional
//	public void retrieveReviewsForCourse() {
//		Course course = repository.findById(10001L);
//		logger.info("{}", course.getReviews());
//	}
//
//	//eager fetch Many:1
//	@Test
//	@Transactional
//	public void retrieveCourseForReview() {
//		Review review = em.find(Review.class, 50001L);
//		logger.info("{}", review.getCourse());
//	}
//
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
