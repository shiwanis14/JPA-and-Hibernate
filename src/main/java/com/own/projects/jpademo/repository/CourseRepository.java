package com.own.projects.jpademo.repository;

import java.util.List;
import com.own.projects.jpademo.entity.Course;
import com.own.projects.jpademo.entity.Review;
import com.own.projects.jpademo.entity.ReviewRating;
import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;


@Repository
@Transactional
public class CourseRepository {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private EntityManager em;

  public Course findById(Long id)
  {
    return em.find(Course.class,id);
  }

  public void deleteById(Long id)
  {
    Course toDelete= em.find(Course.class,id);
    em.remove(toDelete);
  }

  public void insertOrUpdateById(Course course)
  {
    if(course.getId()==null)
      em.persist(course);
    else
      em.merge(course);

  }


  public void playWithEntityManager()
  {
    Course course= new Course("random");
    em.persist(course);
    Course course2= new Course("random2");
    em.persist(course2);



    em.flush();


//    em.clear();
//    em.detach(course2);
    course.setName("NOW UPDATED");
    course2.setName("NOW UPDATED2");

    em.flush();

  }


  public void addReviewsForAcourse()
  {
    Course course = findById(10003L);
    logger.info("Reviews are {} "+course.getReviews());
    Review r1  = new Review(ReviewRating.FIVE, "Great");
    Review r2  = new Review(ReviewRating.FOUR, "Good");

    //bidirectional relatonship so need to add on both sides
    course.addReviews(r1);
    r1.setCourse(course);
    course.addReviews(r2);
    r2.setCourse(course);
    //courses are not changing, reviews are
    em.persist(r1);
    em.persist(r2);
  }


  public void addReviewsForAcourseGeneric(Long courseId, List<Review> reviews)
  {
    Course course = findById(courseId);
    logger.info("Reviews are {} "+course.getReviews());

    //bidirectional relatonship so need to add on both sides
    for(Review r: reviews) {
      r.setCourse(course);
      course.addReviews(r);
      //courses are not changing, reviews are
      em.persist(r);
    }
  }

}
