package com.dtu.openlog.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.dtu.openlog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import com.dtu.openlog.dto.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    public User login(@RequestParam("userName") String userName, @RequestParam("password") String pwd) {
        if (userRepository.findByUserNameAndPwd(userName, pwd) != null) {
            String token = getJWTToken(userName);
            User user = new User();
            user.setUserName(userName);
            user.setToken(token);
            return user;
        } else return null;
        // check if the username and the password is the same in the database then return the token.
    }

    @PostMapping("/adduser")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        try {
            System.out.println(user);
            userRepository.save(new User(user.getUserName(), user.getEmail(), user.getPassword(), getJWTToken(user.getUserName())));
            return new ResponseEntity<>("User was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteUsers")
    public ResponseEntity<String> deleteAllUser() {
        try {
            int numRows = userRepository.deleteAll();
            return new ResponseEntity<>("Deleted " + numRows + " user(s) successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete users.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int userID) {
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

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateTutorial(@PathVariable("id") int id, @RequestBody User user) {
        User temp = userRepository.findById(id);
        if (temp != null) {
            temp.setUserName(user.getUserName());
            temp.setPassword(user.getPassword());
            temp.setEmail(user.getEmail());
            userRepository.update(temp);
            return new ResponseEntity<>("User was updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find User with id=" + id, HttpStatus.NOT_FOUND);
        }
    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }

    @GetMapping("/getAll")
    public List<User> findAllUsers() {
        return userRepository.findAll();
}
    @GetMapping("/user/{userID}")
    public ResponseEntity<User> findUserid(@PathVariable(value = "userID") long id) {
        Optional<User> user = Optional.ofNullable(userRepository.findById(id));
        if (user != null) {
            return new ResponseEntity(user, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
