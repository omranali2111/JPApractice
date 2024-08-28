package com.JPA.JPApractice.Dao;


import com.JPA.JPApractice.Entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    // define field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // implement save method
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    @Transactional
    public List<Student> show(){
        TypedQuery<Student> query = entityManager.createQuery("FROM Student", Student.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Student find(int id){
        return entityManager.find(Student.class, id);
    }

}










