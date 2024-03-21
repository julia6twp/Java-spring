package com.example.lab5spring;

import com.example.lab5spring.entity._Employee_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<_Employee_,Integer> {

    @Query("select t from _Employee_ t where t.id = ?1")
    _Employee_ find_Teacher_By_id(int id);

    @Query("select t from _Employee_ t where t.grupyByGrupa.id = ?1")
    List<_Employee_> get_Teachers_from(int id);
}
