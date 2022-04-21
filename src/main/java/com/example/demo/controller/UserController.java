package com.example.demo.controller;


import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController

public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/adduser")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        try {
            userRepository.save(new User(user.getUserName(), user.getEmail(), user.getPassword()));
            return new ResponseEntity<>("User was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteUsers")
    public ResponseEntity<String> deleteAllUser() {
        try {
            int numRows = userRepository.deleteAll();
            return new ResponseEntity<>("Deleted " + numRows + " openlog(s) successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete users.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/delUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id")  int userID) {
        try {
            int result = userRepository.deleteById(userID);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find user with id=" + userID, HttpStatus.OK);
            }
            return new ResponseEntity<>("user was deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete user.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
/*
    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllTutorials(@RequestParam(required = false) int userID) {
        try {
            List<User> tutorials = new ArrayList<User>();
            if (userID == 0)
                userRepository.findAll().forEach(userID::add);
            else
                userRepository.findByTitleContaining(userID).forEach(userID::add);
            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
*/
    @GetMapping("/findUser/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int userID) {
        User user = userRepository.findById(userID);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
/*
    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String userName) {
        try {
            List<User> users = new ArrayList<User>();
            if (userName == null)
                userRepository.findAll().forEach(users::add);
            else
                userRepository.findByTitleContaining(userName).forEach(users::add);
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
*/
    /*
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getTutorialById(@PathVariable("id") int userID) {
        User user = userRepository.findById(userID);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
*/

    /*
    @PutMapping("/update{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") int id ,@RequestBody User user) {
        userRepository.update(user);
        try {
            return new ResponseEntity<>("ddddd" + user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

*/

}
  /*
    @DeleteMapping("/tutorials/{id}")
  public ResponseEntity<String> deleteTutorial(@PathVariable("id") long id) {
    try {
      int result = tutorialRepository.deleteById(id);
      if (result == 0) {
        return new ResponseEntity<>("Cannot find Tutorial with id=" + id, HttpStatus.OK);
      }
      return new ResponseEntity<>("Tutorial was deleted successfully.", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Cannot delete tutorial.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/tutorials")
    public ResponseEntity<String> deleteAllTutorials() {
        try {
            int numRows = tutorialRepository.deleteAll();
            return new ResponseEntity<>("Deleted " + numRows + " Tutorial(s) successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete tutorials.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    */