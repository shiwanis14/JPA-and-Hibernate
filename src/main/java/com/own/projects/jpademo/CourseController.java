package com.own.projects.jpademo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.own.projects.jpademo.entity.Course;
import com.own.projects.jpademo.entity.FullTimeEmployee;
import com.own.projects.jpademo.entity.PartTimeEmployee;
import com.own.projects.jpademo.entity.Review;
import com.own.projects.jpademo.repository.CourseRepository;
import com.own.projects.jpademo.repository.EmployeeRepository;
import com.own.projects.jpademo.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("")
public class CourseController {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  CourseRepository courseRepository;

  @Autowired
  StudentRepository studentRepository;

  @Autowired
  EmployeeRepository employeeRepository;

  @GetMapping("/courses/{id}")
  public Course findCourse(@PathVariable Long id)
  {
    return courseRepository.findById(id);
  }

  @DeleteMapping("/courses/{id}")
  public void deleteCourse(@PathVariable Long id)
  {
     courseRepository.deleteById(id);
  }

  @PostMapping("/courses")
  public void deleteCourse(@RequestBody Course course)
  {
    courseRepository.insertOrUpdateById(course);
  }

  @GetMapping("/random")
  public void deleteCourse()
  {
    studentRepository.saveStudentWithPassport();
  }

/*  @GetMapping("/addReviews")
  public void addReviewsForAcourse()
  {
    Review r1  = new Review("5", "Great");
    Review r2  = new Review("4", "Good");
    List<Review> reviewList = Arrays.asList(r1, r2);
    courseRepository.addReviewsForAcourseGeneric(10002L,reviewList);
  }*/

  @GetMapping("/employee")
  public void addEmployee()
  {
    employeeRepository.insert(new FullTimeEmployee("Jack", new BigDecimal("100000")));
    employeeRepository.insert(new PartTimeEmployee("Jill", new BigDecimal("50")));
    logger.info("Full time employee: "+employeeRepository.retrieveAllFullTimeEmployees());
    logger.info("Part time employee: "+employeeRepository.retrieveAllPartTimeEmployees());
  }

}
