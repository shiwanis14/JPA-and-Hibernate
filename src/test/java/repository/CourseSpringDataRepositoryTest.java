package repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;
import com.own.projects.jpademo.JpademoApplication;
import com.own.projects.jpademo.entity.Course;
import com.own.projects.jpademo.repository.CourseSpringDataRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest(classes = JpademoApplication.class)
public class CourseSpringDataRepositoryTest {

  @Autowired
  CourseSpringDataRepository repository;
  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Test
  public void findByIdSpringDataJPA() {
    Optional<Course> courseOptional = repository.findById(10001L);
    assertTrue(courseOptional.isPresent());
  }

  @Test
  public void findByIdSpringDataJPANotPresent() {
    Optional<Course> courseOptional = repository.findById(20001L);
    assertFalse(courseOptional.isPresent());
  }


  @Test
  public void add() {
    //create
    Course course = new Course("Taught by Shiwani");
    repository.save(course);

    //update
    course.setName("Taught by Shiwani new");
    repository.save(course);

    logger.info("Courses {}", repository.findAll());
    logger.info("Courses count {}", repository.count());

  }

  @Test
  public void sort() {
    logger.info("Courses {}", repository.findAll(Sort.by(Sort.Direction.DESC, "name")));
  }


  @Test
  public void paginate() {
    PageRequest pageRequest = PageRequest.of(0,3);
    Page<Course> firstPage = repository.findAll(pageRequest);
    //firstPage Page 1 of 3 containing com.own.projects.jpademo.entity.Course instances
    logger.info("firstPage {}", firstPage.getContent());


    //user clicks next
    Pageable secondPageabe = firstPage.getPageable();
    Page<Course> secondPage = repository.findAll(secondPageabe);
    logger.info("secondPage {}", secondPage.getContent());

    Pageable thirdPageabe = secondPage.getPageable();
    Page<Course> thirdPAge = repository.findAll(thirdPageabe);
    logger.info("secondPage {}", thirdPAge.getContent());
  }


  @Test
  public void findByNameCustomQuery() {
    List<Course> courses = repository.findByName("JPA in 50 Steps");
    logger.info("Courses {}", courses);

  }


}

