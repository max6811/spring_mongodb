package com.maxcmartinez.spring_mongodb.services;

import com.maxcmartinez.spring_mongodb.models.Student;
import com.maxcmartinez.spring_mongodb.repositories.StudentRepository;
import com.maxcmartinez.spring_mongodb.utils.exceptions.EntityNotFoundException;
import com.maxcmartinez.spring_mongodb.utils.responses.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    private static Logger logger = Logger.getLogger(StudentService.class.getName());

    public List<Student> getAll() {
        return repository.findAll();
    }

    public Student save(Student student) {
        repository.findStudentByEmail(student.getEmail()).ifPresent(student1 -> {
            logger.info(student1 + "Already exists.");
            throw new IllegalStateException("Error when try to save" + student1);
        });

        logger.info("student inserting in database");
        return repository.insert(student);
    }

    public Student update(Student student, String id) {
        Optional<Student> existingStudent = repository.findStudentByEmail(student.getEmail());

        if (existingStudent != null && !existingStudent.get().getId().equals(student.getId())) {
            logger.info("Student with id: " + id + "not found.");
            throw new EntityNotFoundException("STUDENT", id);
        }
        logger.info("student updated.");
        return repository.save(student);
    }

    public BasicResponse delete(String id) {
        BasicResponse basicResponse = new BasicResponse();

        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("STUDENT", id);
        }
        repository.deleteById(id);
        basicResponse.setMessage("Student with id: " + id + " was deleted.");
        return basicResponse;
    }
}
