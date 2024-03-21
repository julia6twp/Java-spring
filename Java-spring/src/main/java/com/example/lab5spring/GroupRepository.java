package com.example.lab5spring;

import com.example.lab5spring.entity._Grupy_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GroupRepository extends JpaRepository<_Grupy_,Integer> {

    @Query("select t from _Grupy_ t where t.id = ?1")
    _Grupy_ find_Grupy_By_id(int id);
}
