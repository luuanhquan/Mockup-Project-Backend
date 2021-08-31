package com.repositories;

import com.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface UsersRepository extends JpaRepository<Users, Integer> {


    @Query("select u from Users u where u.email = :email")
    List<Users> findByEmail(@Param("email") String email);


    @Query("select u from Users u where u.username = :username")
    Users findByUsername(@Param("username") String userName);

    @Query("select u from Users u where u.username=?1 and u.password=?2")
    Users findByUsernameAndPassword(String username, String password);

    @Query("select u from Users u where u.email= :email")
    Users findUserByEmail(@Param("email") String email);

    @Modifying
    @Query("update Users u set u.password=?2 where u.id=?1")
    void changePass(int id, String password);

//    @Query("from Users u where u.username = ?1")
//    Users findByUsername(String username);

}
