package com.example.be.controllers;

import com.example.be.dto.student.StudentDTO;
import com.example.be.models.Student;
import com.example.be.services.StudentService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<StudentDTO>> getAllStudent() {
        List<Student> students = studentService.findAll();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for ( Student s: students) {
            studentDTOS.add(new StudentDTO(s.get_id().toString(), s.getStudentCode(),s.getName(),s.getGender(),
                    s.getAddress(),s.getPhoneNumber(),s.getDateOfBirth()));
        }
        return new ResponseEntity<>(studentDTOS, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable("id") String id) {
        Student s = studentService.findById(new ObjectId(id));
        StudentDTO studentDTO =  new StudentDTO(s.get_id().toString(), s.getStudentCode(),s.getName(),
                s.getGender(),s.getAddress(),s.getPhoneNumber(),s.getDateOfBirth());
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Student> createStudent(@RequestBody StudentDTO studentDTO) {
        Student student = new Student(studentDTO.getStudentCode(), studentDTO.getName(),
                studentDTO.getGender(), studentDTO.getAddress(), studentDTO.getPhoneNumber(),studentDTO.getDateOfBirth());
        studentService.save(student);
        return new ResponseEntity<Student>(student, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Student> deleteStudent(@PathVariable String id) {
        studentService.remove(new ObjectId(id));
        return new ResponseEntity<Student>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Student> updateStudent(@PathVariable String id, @RequestBody Student student) {
        student.set_id(new ObjectId(id));
        studentService.save(student);
        return new ResponseEntity<Student>(HttpStatus.OK);
    }


}