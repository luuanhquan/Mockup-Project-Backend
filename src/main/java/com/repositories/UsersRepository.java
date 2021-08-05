package com.repositories;

import com.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {


    @Query("select u from Users u where u.email = :email")
    Optional<Users> findByEmail(@Param("email") String email);


    @Query("select u from Users u where u.username = :username")
    Optional<Users> findByUsername(@Param("username") String userName);

//    @Query("from Users u where u.username = ?1")
//    Users findByUsername(String username);

}
