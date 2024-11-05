package com.apostolis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apostolis.exception.UserNotFoundException;
import com.apostolis.model.User;
import com.apostolis.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    //Inject user repository
    @Autowired
    private UserRepository userRepository;

    //For posting the data |@RequestBody is the JSON body
    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return userRepository.save(newUser); //Return the data we posted
    }

    @GetMapping("/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id){
        return userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }

    @PutMapping("user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id){

        return userRepository.findById(id)
            .map(user -> {
                user.setUsername(newUser.getUsername());
                user.setName(newUser.getName());
                user.setEmail(newUser.getEmail());
                return userRepository.save(user);
            }).orElseThrow(()-> new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }

        userRepository.deleteById(id);
        return "User with id: " +id+" has been deleted successfully.";
    }

}
