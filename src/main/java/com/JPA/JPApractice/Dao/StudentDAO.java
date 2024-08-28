package com.JPA.JPApractice.Dao;


import com.JPA.JPApractice.Entity.Student;

import java.util.List;

public interface StudentDAO {

    void save(Student theStudent);
    List<Student> show();

    Student find(int id);
}