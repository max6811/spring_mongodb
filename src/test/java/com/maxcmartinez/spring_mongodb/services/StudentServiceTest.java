package com.maxcmartinez.spring_mongodb.services;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.maxcmartinez.spring_mongodb.models.Address;
import com.maxcmartinez.spring_mongodb.models.Gender;
import com.maxcmartinez.spring_mongodb.models.Student;
import com.maxcmartinez.spring_mongodb.repositories.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private static Address ADDRESS = new Address("Bolivia", "La Paz", "");
    private static List<String> FAVOURITE_SUBJECTS = new ArrayList<>(Arrays.asList("Math", "Introduction to Programming"));
    private static LocalDateTime LOCAL_DATE_TIME = LocalDateTime.now();


    @Test
    public void testGetAllStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(
                "Reynaldo",
                "Caceres",
                "reynaldo@mail.com",
                Gender.MALE,
                ADDRESS,
                FAVOURITE_SUBJECTS,
                new BigDecimal(50),
                LOCAL_DATE_TIME));
        students.add(new Student(
                "Maria",
                "Ramirez",
                "maria@mail.com",
                Gender.FEMALE,
                ADDRESS,
                FAVOURITE_SUBJECTS,
                new BigDecimal(20),
                LOCAL_DATE_TIME));

        when(studentRepository.findAll()).thenReturn(students);

        List<Student> result = studentService.getAll();

        assertEquals(2, result.size());
        assertEquals("maria@mail.com", result.get(1).getEmail());
    }
}
