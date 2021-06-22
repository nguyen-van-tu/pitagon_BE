package com.example.be.repositories;

import com.example.be.models.Student;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student, ObjectId> {
    Student findBy_id(ObjectId _id);
}
