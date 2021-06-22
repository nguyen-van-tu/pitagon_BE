package com.example.be.repositories;

import com.example.be.models.User;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
public interface UserRepository extends MongoRepository<User, ObjectId> {

    @Query(value = "{'isDeleted' : ?1 , $or: [ " +
            "{ 'firstName' : {$regex:?0,$options:'i' }}" +
            "{ 'firstNameAccented' : {$regex:?0,$options:'i' }}" +
            "{ 'username' : {$regex:?0,$options:'i' }} " +
            "{ 'midName' : {$regex:?0,$options:'i' }} " +
            "{ 'midNameAccented' : {$regex:?0,$options:'i' }} " +
            "{ 'lastName' : {$regex:?0,$options:'i' }} " +
            "{ 'lastNameAccented' : {$regex:?0,$options:'i' }} " +
            "{ 'email' : {$regex:?0,$options:'i' }}" +
            "{ 'address' : {$regex:?0,$options:'i' }}" +
            "{ 'addressAccented' : {$regex:?0,$options:'i' }}" +
            "{ 'phone' : {$regex:?0,$options:'i' }}" +
            "{ 'gender' : {$regex:?0,$options:'i' }}" +
            "]}")
    Page<User> findAll(Pageable pageable, String search, int isDeleted);

    User findByUserName(String username);

    User findByEmailAndUserNameIsNot(String email,String userName);
}
