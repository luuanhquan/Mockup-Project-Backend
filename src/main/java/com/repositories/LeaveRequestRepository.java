package com.repositories;

import com.DTO.LeaveRequestDTO;
import com.entity.LeaveRequests;
import com.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequests, Integer> {


    LeaveRequests findRequestById(Integer id);

    @Query("select lr from LeaveRequests lr where lr.userRequested = :userRequested")
    LeaveRequests findRequestByUserRq(@Param("userRequested") String userRequested);

}

