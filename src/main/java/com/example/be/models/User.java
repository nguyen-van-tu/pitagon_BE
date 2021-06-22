package com.example.be.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection = "users")
@Getter
@Setter
public class User extends BaseObject{
    @Id
    private ObjectId _id;

    @Transient
    private String idString;

    public String getIdString() {
        return _id == null ? null : _id.toHexString();
    }

    public void setIdString(String id) {
        this.idString = id;
    }

    @Field(name = "UserName")
    @Indexed
    private String userName;

    @Field(name = "Password")
    private String password;

    @Field(name = "FirstName")
    @Indexed
    private String firstName;

    @Field(name = "FirstNameAccented")
    @Indexed
    private String firstNameAccented;

    @Field(name = "LastName")
    @Indexed
    private String lastName;

    @Field(name = "LastNameAccented")
    @Indexed
    private String lastNameAccented;

    @Field(name = "MidName")
    private String midName;

    @Field(name = "MidNameAccented")
    private String midNameAccented;

    @Field(name = "Gender")
    private Integer gender;

    @Field(name = "DateOfBirth")
    private String dateOfBirth;

    @Field(name = "Email")
    @Indexed
    private String email;

    @Field(name = "Address")
    private String address;

    @Field(name = "AddressAccented")
    private String addressAccented;

    @Field(name = "DisplayName")
    private String displayName;

    @Field(name = "Phone")
    private String phone;

    @Field(name = "Status")
    private int status;
}
