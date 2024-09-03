package com.JPA.JPApractice.Controller;

import com.JPA.JPApractice.Dao.StudentDAOImpl;
import com.JPA.JPApractice.Dao.StudentNotFoundException;
import com.JPA.JPApractice.Entity.Student;
import com.JPA.JPApractice.Entity.StudentErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping()
    public ResponseEntity<String> addStudent(@RequestBody Student std) {
        ss.save(std);
        return ResponseEntity.ok("Student added successfully with ID: " + std.getId());
    }

    @GetMapping()
    public List<Student> show(){
        return ss.show();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        if (id < 0) {
            throw new StudentNotFoundException("Invalid student ID - " + id);
        }

        Student student = ss.find(id);

        if (student == null) {
            throw new StudentNotFoundException("Student id not found - " + id);
        }

        return ResponseEntity.ok(student);
    }


    @GetMapping("/getbylastname")
        public List<Student> getStudentByLastname(@RequestParam String lastName){
        return ss.findbylastname(lastName);
        }

    @PutMapping()
    public ResponseEntity<Student> update(@RequestBody Student newStudent){
     ss.update(newStudent);
        Student updatedStudent = ss.find(newStudent.getId());
        if (updatedStudent != null) {
            return ResponseEntity.ok(updatedStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping()
    public ResponseEntity<String> delete(@RequestParam int DStudent) {
        ss.delete(DStudent);
        Student DeletedStudent = ss.find(DStudent);
        if (DeletedStudent == null) {
            return ResponseEntity.ok("Student deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete the student.");
        }
    }
}
