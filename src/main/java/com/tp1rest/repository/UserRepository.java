package com.tp1rest.repository;


import com.tp1rest.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findUserByEmail(String email);




}