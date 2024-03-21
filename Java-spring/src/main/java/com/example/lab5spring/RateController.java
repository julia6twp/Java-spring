package com.example.lab5spring;


import com.example.lab5spring.entity._Rate_;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rating")
public class RateController {
    RateRepository rateRepository;

    @Autowired
    public RateController(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    @GetMapping
    public ResponseEntity<List<_Rate_>> getAllTeachers() {
        List<_Rate_> rates = rateRepository.findAll();
        if (rates.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(rates, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTeacher(@PathVariable int id) {
        try {
            rateRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
