package com.example.demo.controller;
import com.example.demo.model.LogValue;
import com.example.demo.repository.LogValueRepository;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController

public class LogValueController {

    @Autowired
    LogValueRepository logValueRepository;

    @PostMapping("/addValue")
    public ResponseEntity<String> createValue(@RequestBody LogValue logvalue) {
        try {
            logValueRepository.save(new LogValue(logvalue.getBlodsukker(),logvalue.getKulhydrat(),logvalue.getInsulin(),logvalue.getUserID()));
            return new ResponseEntity<>("LogValue was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteAllLogValue")
    public ResponseEntity<String> deleteAllUser() {
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
    @GetMapping("logvalue/{id}")
    public ResponseEntity<LogValue> getTutorialById(@PathVariable("id") long id) {
        LogValue logValue = logValueRepository.findByID(id);
        if (logValue != null){
            return new ResponseEntity<>(logValue,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        }

}
