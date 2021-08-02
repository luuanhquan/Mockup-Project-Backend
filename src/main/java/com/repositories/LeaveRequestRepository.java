package com.repositories;

import com.entity.LeaveRequests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;


@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequests, Integer> {
    //  lấy danh sách các request
    @Query("select count(lr.id) from LeaveRequests lr")
    long getAllRequest();

    // Phan trang
    @Query(value = "SELECT lr FROM LeaveRequests lr")
    List<LeaveRequests> getListRequest(Pageable pageable);

// Manager phê duyệt;

    @Query("select lr from LeaveRequests lr where lr.duration >=4")
    List<LeaveRequests> listManageApprove(long duration);
// Pm phê duyêt

    @Query("select lr from LeaveRequests lr where lr.duration <4")
    List<LeaveRequests> listPmApprove(long duration);



    LeaveRequests findRequestById(Integer id);

}

