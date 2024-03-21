package com.example.lab5spring;


import com.example.lab5spring.entity._Grupy_;
import com.example.lab5spring.entity._Rate2_;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rating")
public class RatePostController {
    RatePostRepository repository;

    public RatePostController(RatePostRepository repository) {
        this.repository = repository;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<String> createRate(@RequestBody _Rate2_ rate) {
        try {
            _Rate2_ savedRate = repository.save(rate);
            return new ResponseEntity<>("Rate created with ID: " + savedRate.getId(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create Rate: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
