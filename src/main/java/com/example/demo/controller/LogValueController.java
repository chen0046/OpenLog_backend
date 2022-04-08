package com.example.demo.controller;
import com.example.demo.model.LogValue;
import com.example.demo.repository.LogValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController

public class LogValueController {

    @Autowired
    LogValueRepository logValueRepository;

    @PostMapping("/addValue")
    public ResponseEntity<String> createValue(@RequestBody LogValue logvalue) {
        try {
            logValueRepository.save(new LogValue(logvalue.getBlodsukker(),logvalue.getKulhydrat(),logvalue.getInsulin()));
            return new ResponseEntity<>("LogValue was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
