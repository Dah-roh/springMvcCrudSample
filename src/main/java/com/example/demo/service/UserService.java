package com.example.demo.service;

import com.example.demo.models.User;

import java.util.Optional;


public interface UserService{


   void saveUser (User user);
   Iterable<User> findUser ();
   Optional<User> findUserById(Long id);


}
