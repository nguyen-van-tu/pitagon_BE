package com.example.be.services;

import com.example.be.models.Student;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface StudentService {
    List<Student> findAll();

    Student findById(ObjectId id);

    Student save(Student student);

    void remove(ObjectId id);
}
