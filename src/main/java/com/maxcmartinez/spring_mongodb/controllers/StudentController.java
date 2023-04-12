package com.maxcmartinez.spring_mongodb.controllers;

import com.maxcmartinez.spring_mongodb.models.Student;
import com.maxcmartinez.spring_mongodb.services.StudentService;
import com.maxcmartinez.spring_mongodb.utils.responses.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    private static Logger logger = Logger.getLogger(StudentService.class.getName());

    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        return ResponseEntity.ok(studentService.getAll());
    }

    @PostMapping
    public ResponseEntity<Student> save(@RequestBody Student student) {
        logger.info(student + " Body request");
        return ResponseEntity.ok(studentService.save(student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable String id, @RequestBody Student student) {
        logger.info("Student ID: " + id);
        return ResponseEntity.ok(studentService.update(student, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BasicResponse> delete(@PathVariable String id){
        logger.info("Student ID: " + id + " for deleting.");
        return ResponseEntity.ok(studentService.delete(id));
    }
}
