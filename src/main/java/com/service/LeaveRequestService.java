package com.service;
import com.dto.LeaveRequestDTO;
import com.entity.LeaveRequests;
import com.entity.enums.REQUEST_STATUS;
import com.repositories.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LeaveRequestService {

    @Autowired
    LeaveRequestRepository leaveRequestRepository;
    // lay all request
    public List<LeaveRequests> findAll() {
        return leaveRequestRepository.findAll();
    }
    //tim kiem id
    public LeaveRequests findRequestById(Integer id) {
        return leaveRequestRepository.findRequestById(id);
    }




    // tao moi request
    public LeaveRequests save(LeaveRequests leaveRequests) {
        return leaveRequestRepository.save(leaveRequests);
    }




}
