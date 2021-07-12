package com.own.projects.jpademo.repository;

import com.own.projects.jpademo.entity.Passport;
import com.own.projects.jpademo.entity.Student;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class StudentRepository {

  @Autowired
  private EntityManager em;

  public Student findById(Long id)
  {
    return em.find(Student.class,id);
  }

  public void deleteById(Long id)
  {
    Student toDelete= em.find(Student.class,id);
    em.remove(toDelete);
  }

  public void insertOrUpdateById(Student student)
  {
    if(student.getId()==null)
      em.persist(student);
    else
      em.merge(student);

  }

  public void saveStudentWithPassport()
  {
    Passport p = new Passport("Z123456");
    em.persist(p);

    Student s = new Student("Shiwani");
    s.setPassport(p);
      em.persist(s);
  }


  public void playWithEntityManager()
  {
    Student student= new Student("random");
    em.persist(student);
    Student student2= new Student("random2");
    em.persist(student2);



    em.flush();


//    em.clear();
//    em.detach(student2);
    student.setName("NOW UPDATED");
    student2.setName("NOW UPDATED2");

    em.flush();

  }


}
