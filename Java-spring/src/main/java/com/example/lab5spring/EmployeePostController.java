package com.example.lab5spring;

import com.example.lab5spring.entity._Teacher2_;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
public class EmployeePostController {
    EmployeePostRepository repository;

    public EmployeePostController(EmployeePostRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<_Teacher2_> createTeacher(@RequestBody _Teacher2_ teacher2) {
        _Teacher2_ createdTeacher = repository.save(teacher2);
        return new ResponseEntity<>(createdTeacher, HttpStatus.CREATED);
    }
}
