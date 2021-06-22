package com.example.be.models;

import lombok.Data;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Getter
@Document(collection = "student")
public class Student {
    @Id
    private ObjectId _id;
    private String studentCode;
    private String name;
    private String gender;
    private String address;
    private String phoneNumber;
    private String dateOfBirth;


    public Student(String studentCode, String name, String gender, String address, String phoneNumber , String dateOfBirth) {
        this.studentCode = studentCode;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;

    }

    public Student() {

    }
}
