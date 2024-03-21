package com.example.lab5spring;

import com.example.lab5spring.entity._Employee_;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public ResponseEntity<List<_Employee_>> getAllEmployee() {
        List<_Employee_> teachers = employeeRepository.findAll();
        if (teachers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<_Employee_> getUserById(@PathVariable int id) {
        _Employee_ user = employeeRepository.find_Teacher_By_id(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable int id) {
        try {
            employeeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/csv", produces = "text/csv")
    public ResponseEntity<byte[]> getEmployeeAsCSV(){
        List<_Employee_> teachers = employeeRepository.findAll();

        try {
            byte[] csvBytes = CsvGenerator.generateCsvFromTeachers(teachers);
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=emlpoyee.csv");

            return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType("text/csv")).body(csvBytes);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

}
