package com.repositories;

import com.entity.Division;
import com.entity.DivisionUser;
import com.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface DivisionUserRepository extends JpaRepository<DivisionUser, Integer> {

    @Query("select Division from DivisionUser where Users =:id")
    public Division findByUsers(@Param("id")int id);

    @Query("select Users from DivisionUser where Division=:div and isManager=true")
    public Users findByDivision(@Param("div") Division div);
}

