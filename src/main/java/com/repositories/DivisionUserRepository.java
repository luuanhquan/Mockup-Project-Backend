package com.repositories;

import com.entity.Division;
import com.entity.DivisionUser;
import com.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface DivisionUserRepository extends JpaRepository<DivisionUser, Integer> {

    @Query("select d.division from DivisionUser d where d.users = ?1")
    public List<Division> getDivisionByUsers(Users users);

    @Query("select d.users from DivisionUser d where d.division = ?1 and d.isManager=true")
//    (select cacs cot user from diviu join user on ..  where division.id= ?1.id and isManager= true)
    List<Users> getUsersByDivision(Division division);
}


