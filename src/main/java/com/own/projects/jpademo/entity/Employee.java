package com.own.projects.jpademo.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
//@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Employee {

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String name ;

  protected Employee() {
  }

  public Employee(String name) {
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

  @Override
  public String toString() {
    return "Employee{" +
        "name='" + name + '\'' +
        '}';
  }
}
