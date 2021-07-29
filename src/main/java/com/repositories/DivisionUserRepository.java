package com.repositories;

import com.entity.Division;
import com.entity.DivisionUser;
import com.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DivisionUserRepository extends JpaRepository<DivisionUser, Integer> {

    @Query("select d.Division from DivisionUser d where d.users = ?1")
    public List<Division> getDivisionByUsers(Users users);

    @Query("select d.users from DivisionUser d where d.Division = ?1 and d.isManager=true")
    public List<Users> getUsersByDivision(Division division);
}


