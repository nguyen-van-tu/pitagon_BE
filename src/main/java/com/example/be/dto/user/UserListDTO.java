package com.example.be.dto.user;

import lombok.Data;
@Data
public class UserListDTO {
    private String idString;
    private String userName;
    private String displayName;
    private String firstName;
    private String midName;
    private String lastName;
    private Integer gender;
    private String dateOfBirth;
    private String email;
    private String address;
    private String phone;
    private Integer isDeleted;
    private Integer status;
}
