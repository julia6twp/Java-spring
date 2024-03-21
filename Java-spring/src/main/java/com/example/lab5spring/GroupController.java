package com.example.lab5spring;

import com.example.lab5spring.entity._Grupy_;
import com.example.lab5spring.entity._Employee_;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group")
public class GroupController {
    GroupRepository groupRepository;
    EmployeeRepository r2;

    @Autowired
    public GroupController(GroupRepository groupRepository, EmployeeRepository t) {
        this.groupRepository = groupRepository;
        this.r2 = t;
    }

    @GetMapping
    public ResponseEntity<List<_Grupy_>> getAllTeachers() {
        List<_Grupy_> groups = groupRepository.findAll();
        if (groups.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<_Grupy_> createTeacher(@RequestBody _Grupy_ group) {
        _Grupy_ createdGroup = groupRepository.save(group);
        return new ResponseEntity<>(createdGroup, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTeacher(@PathVariable int id) {
        try {
            groupRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/employee")
    public ResponseEntity<List<_Employee_>> getTeachersfromgroup(@PathVariable int id){
        List<_Employee_> teachers = r2.get_Teachers_from(id);
        if (teachers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    @GetMapping("/{id}/fill")
    public ResponseEntity<Object> getfill(@PathVariable int id){
        _Grupy_ g = groupRepository.find_Grupy_By_id(id);
        if (g != null) {

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<_Employee_> teachers = r2.get_Teachers_from(id);
        if (teachers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        double fill = ((double) teachers.size() /(double)g.getPojemnść()) * 100;
        return ResponseEntity.ok(fill);
    }
}
