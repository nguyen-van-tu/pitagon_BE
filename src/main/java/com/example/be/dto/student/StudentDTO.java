package com.example.be.dto.student;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class StudentDTO {
    private String _id;
    private String studentCode;
    private String name;
    private String gender;
    private String address;
    private String phoneNumber;
    private String dateOfBirth;


    public StudentDTO(String _id, String studentCode, String name, String gender, String address , String phoneNumber,String dateOfBirth) {
        this._id = _id;
        this.studentCode = studentCode;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }
}
