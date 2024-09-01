package com.JPA.JPApractice.Controller;

import com.JPA.JPApractice.Dao.StudentDAOImpl;
import com.JPA.JPApractice.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private StudentDAOImpl ss;

    @Autowired
    public StudentController(StudentDAOImpl ss) {
        this.ss = ss;
    }

    @PostMapping("/add-student")
    public ResponseEntity<String> addStudent(@RequestBody Student std) {
        ss.save(std);
        return ResponseEntity.ok("Student added successfully with ID: " + std.getId());
    }

    @GetMapping("/show-all")
    public List<Student> show(){
        return ss.show();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Student student = ss.find(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/getbylastname")
        public List<Student> getStudentByLastname(@RequestParam String lastName){
        return ss.findbylastname(lastName);
        }
}
