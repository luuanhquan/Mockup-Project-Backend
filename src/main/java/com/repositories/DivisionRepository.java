package com.repositories;

import com.DTO.DivisionList;
import com.DTO.PM;
import com.entity.Division;
import com.enums.USER_ROLE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface DivisionRepository extends JpaRepository<Division, Integer> {

    @Query("select new com.DTO.DivisionList(d.id, d.name) from Division d where d.status=1" )
    List<DivisionList> getDivision();
    @Query("select new com.DTO.PM(u.id, u.username,u.firstname) from Division d join DivisionUser du on d.id=du.division.id join Users u on u.id = du.users.id where d.id=?1 and u.role=2 and u.status=1")
    List<PM> getPM(int id);
}

