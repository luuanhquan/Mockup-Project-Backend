package com.repositories;

import com.DTO.PM;
import com.DTO.UserDTOE;
import com.DTO.UserSimpleDTO;
import com.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
@Transactional
public interface UsersRepository extends JpaRepository<Users, Integer> {


    @Query("select u from Users u where u.email = :email")
    Optional<Users> findByEmail(@Param("email") String email);


    @Query("select u from Users u where u.username = :username")
    Users findByUsername(@Param("username") String userName);

    @Query("select u from Users u where u.username=?1 and u.password=?2")
    Users findByUsernameAndPassword(String username, String password);

    @Query("select u from Users u where u.email= :email and u.status=1")
    Users findUserByEmail(@Param("email") String email);

    @Modifying
    @Query("update Users u set u.password=?2 where u.id=?1")
    void changePass(int id, String password);

    @Query("select  new com.DTO.UserDTOE(u.id," +
            "u.role," +
            "u.username," +
            "u.gender," +
            "u.type," +
            "u.phone, " +
            "u.email," +
            "u.status," +
            "u.password," +
            "u.firstname," +
            "u.middlename," +
            "u.lastname," +
            "u.personalid," +
            "u.hometown," +
            "u.education," +
            "u.school," +
            "u.major," +
            "u.avatar," +
            "u.dayOffLastYear, u.dateCreated, u.birthday) from Users u")
    Page<UserDTOE> findAllDTO(Pageable pageable);

    @Query("select  new com.DTO.UserDTOE(u.id," +
            "u.role," +
            "u.username," +
            "u.gender," +
            "u.type," +
            "u.phone, " +
            "u.email," +
            "u.status," +
            "u.password," +
            "u.firstname," +
            "u.middlename," +
            "u.lastname," +
            "u.personalid," +
            "u.hometown," +
            "u.education," +
            "u.school," +
            "u.major," +
            "u.avatar," +
            "u.dayOffLastYear, u.dateCreated, u.birthday) from Users u where u.id= ?1")
    UserDTOE findUserById(Integer id);


    @Query("select  new com.DTO.UserDTOE(u.id," +
            "u.role," +
            "u.username," +
            "u.gender," +
            "u.type," +
            "u.phone, " +
            "u.email," +
            "u.status," +
            "u.password," +
            "u.firstname," +
            "u.middlename," +
            "u.lastname," +
            "u.personalid," +
            "u.hometown," +
            "u.education," +
            "u.school," +
            "u.major," +
            "u.avatar," +
            "u.dayOffLastYear, u.dateCreated, u.birthday) from Users u where u.username=?1 or u.role=?2 or u.status=?3")
    List<UserDTOE> seach(String seachByRole,int seachByName,long seachByStatus);



    @Query("select  new com.DTO.UserDTOE(u.id," +
            "u.role," +
            "u.username," +
            "u.gender," +
            "u.type," +
            "u.phone, " +
            "u.email," +
            "u.status," +
            "u.password," +
            "u.firstname," +
            "u.middlename," +
            "u.lastname," +
            "u.personalid," +
            "u.hometown," +
            "u.education," +
            "u.school," +
            "u.major," +
            "u.avatar," +
            "u.dayOffLastYear, u.dateCreated, u.birthday) from Users u")
    List<UserDTOE> findalls();

    @Query("select new com.DTO.PM(u.id,u.username, u.lastname) from Users u join DivisionUser du on du.users.id=u.id where du.division.id=?1 and u.role=2")
    List<PM> getPMByDivision(int id);

    @Query("select new com.DTO.UserSimpleDTO(u.id, u.username, u.email, u.phone, u.role) from Users u join ProjectUser pu on u.id=pu.users.id where pu.project.id=?1")
    List<UserSimpleDTO> findByProject(Integer id);
//    @Query("from Users u where u.username = ?1")
//    Users findByUsername(String username);


    @Query("select new com.DTO.UserSimpleDTO(u.id, u.username, u.email, u.phone, u.role) from Users u where u.status=1 and u.id not in (select u2.id from Users u2 join ProjectUser pu on pu.users.id=u2.id where pu.project.id = ?1) order by u.id")
    List<UserSimpleDTO> findUserToAdd(Integer id);

    @Query("select  new com.DTO.PM(u.id, u.username, u.lastname) from Users u join ProjectUser pu on u.id = pu.users.id where pu.project.id=?1 and pu.ispm = true ")
    PM findPM(Integer id);

    @Query(value = "select u.email from Users u where u.status=1 and FUNCTION('month',u.birthday) = function('month',current_date ) AND FUNCTION('day',u.birthday) = function('day',current_date )")
    List<String> findBirthday();
}
