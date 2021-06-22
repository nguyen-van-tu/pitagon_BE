package com.example.be.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public abstract class BaseObject {
    @Field(name = "CreatedDate")
    private String createdDate;

    @Field(name = "CreatedUser")
    private String createdUser;

    @Field(name = "ModifiedDate")
    private String modifiedDate;

    @Field(name = "ModifiedUser")
    private String modifiedUser;

    @Field(name = "DeletedDate")
    private String deletedDate;

    @Field(name = "DeletedUser")
    private String deletedUser;

    @Field(name = "IsDeleted")
    private int isDeleted;
}
