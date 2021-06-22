package com.example.be.services.impls;

import com.example.be.models.Student;
import com.example.be.repositories.StudentRepository;
import com.example.be.services.StudentService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    public StudentRepository repository;

    @Override
    public List<Student> findAll() {
        return repository.findAll();
    }

    @Override
    public Student findById(ObjectId id) {
        return repository.findBy_id(id);
    }

    @Override
    public Student save(Student student) {
        return repository.save(student);
    }

    @Override
    public void remove(ObjectId id) {
        repository.deleteById(id);
    }
}
