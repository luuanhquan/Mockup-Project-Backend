package com.service;

import com.DTO.LeaveRequestDTO;
import com.DTO.RegisterUser;
import com.DTO.UserDTO;
import com.entity.LeaveRequests;
import com.entity.Users;
import com.enums.REQUEST_STATUS;
import com.repositories.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveRequestService {

    @Autowired
    LeaveRequestRepository leaveRequestRepository;
    @Autowired
    UsersService usersService;
    // lay all request
    public List<LeaveRequests> findAll() {

        return leaveRequestRepository.findAll();
    }



    ////////////

    //tim kiem id
    public LeaveRequests findRequestById(Integer id) {
        return leaveRequestRepository.findRequestById(id);
    }


    // tao moi request
    public LeaveRequests save(LeaveRequests leaveRequests) {
        return leaveRequestRepository.save(leaveRequests);
    }


    //    }



}
