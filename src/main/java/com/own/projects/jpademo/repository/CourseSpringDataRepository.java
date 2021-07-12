package com.own.projects.jpademo.repository;

import java.util.List;
import com.own.projects.jpademo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="coursesFromRepository")
public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {

 List<Course> findByName(String name);
 List<Course> findByNameOrderByCreatedDateDesc(String name);
 List<Course> findByNameAndId(String name, Long Id);
 List<Course> countByName(String name);
 List<Course> deleteByName(String name);

 @Query("Select c from Course c")
 List<Course> getALlCourses();

  @Query(value = "Select * from Course c", nativeQuery = true)
  List<Course> getALlCoursesNative();


  @Query(name = "get_all_courses")
  List<Course> getALlCooursesNamedQuery();

}
