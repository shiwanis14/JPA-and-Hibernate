package com.own.projects.jpademo.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name="course")
@NamedQuery(name="get_all_courses", query = "Select c from Course c")
@NamedQuery(name="get_all_courses_join_fetch", query = "Select c from Course c JOIN FETCH c.students")
@Cacheable
//hibernate annotation for soft delete
@SQLDelete(sql = "update course set is_deleted = true where id = ? ")
@Where(clause= "is_deleted=false")
public class Course {

  private static Logger LOGGER = LoggerFactory.getLogger(Course.class);
  @Id
  @GeneratedValue
  private Long id;

  @Column(name="name", nullable = false)
  private String name ;

  @UpdateTimestamp
  private LocalDateTime lastUpdatedDate;

  @CreationTimestamp
  private LocalDateTime createdDate;

//  @Column(updatable = false)
  private Boolean is_deleted;

  @OneToMany(mappedBy = "course")
  private Set<Review> reviews= new HashSet<>();

  @ManyToMany(mappedBy = "courses")
  @JsonIgnore
  private Set<Student> students= new HashSet<>();

  protected Course() {
  }

  public Course(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDateTime getLastUpdatedDate() {
    return lastUpdatedDate;
  }

  public LocalDateTime getCreateDate() {
    return createdDate;
  }

  public Boolean getIs_deleted() {
    return is_deleted;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public Set<Review> getReviews() {
    return reviews;
  }

  public void setReviews(Set<Review> reviews) {
    this.reviews = reviews;
  }

  public void addReviews(Review review) {
    this.reviews.add(review);
  }

  public void removeReviews(Review review) {
    this.reviews.remove(review);
  }

  public void addCourse(Student student) {
    this.students.add(student);
  }

  public Set<Student> getStudents() {
    return students;
  }

  public void setStudents(Set<Student> students) {
    this.students = students;
  }

  //Update entity in cache so that other lines can pick
  @PreRemove
  private void preRemove()
  {
    LOGGER.info("Setting is_deleted to true");
    this.is_deleted=true;
  }

  @Override
  public String toString() {
    return "Course{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
}
