package com.dtu.openlog.controller;
import com.dtu.openlog.dto.LogValue;
import com.dtu.openlog.dto.User;
import com.dtu.openlog.repository.LogValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController

public class LogValueController {

    @Autowired
    LogValueRepository logValueRepository;

    @PostMapping("/addValue")
    public ResponseEntity<String> createValue(@RequestBody LogValue logvalue) {
        try {
            System.out.println(logvalue);
            logValueRepository.save(new LogValue(logvalue.getBlodsukker(),logvalue.getKulhydrat(),logvalue.getInsulin(),logvalue.getUserID()));
            return new ResponseEntity<>("LogValue was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteAllLogValue")
    public ResponseEntity<String> deleteAllLogValue() {
        try {
            int numRows = logValueRepository.deleteAll();
            return new ResponseEntity<>("Deleted " + numRows + " logValues successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete logValues", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteByID/{userID}")
    public ResponseEntity<String> deleteByID(@PathVariable("userID") int userID){
        try {
            int result = logValueRepository.deleteById(userID);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find UserIDs with id=" + userID, HttpStatus.OK);
            }
            return new ResponseEntity<>("LogValues with userID: " + userID +  " was deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete userID.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
