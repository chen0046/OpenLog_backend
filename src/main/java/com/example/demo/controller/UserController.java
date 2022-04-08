package com.example.demo.controller;


import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/deleteusers")
    public ResponseEntity<String> deleteAllUser() {
        try {
            int numRows = userRepository.deleteAll();
            return new ResponseEntity<>("Deleted " + numRows + " openlog(s) successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete tutorials.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



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