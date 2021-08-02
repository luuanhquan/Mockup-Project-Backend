package com.controllers;


import com.dto.LeaveRequestDTO;
import com.entity.LeaveRequests;
import com.entity.enums.REQUEST_STATUS;
import com.service.LeaveRequestService;
import com.service.RequestTypeService;
import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RestController
//@RequestMapping("${spring.data.rest.base-path}/LeaveRequests")
@RequestMapping( "/leaverequest")
public class LeaveRequestController {
    private final LeaveRequestService leaveRequestService;

    @Autowired
    public LeaveRequestController(LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }

    //Lay  all request
    @GetMapping("/list")
    public List<LeaveRequestDTO> getAllLeaveRequests() {
        List<LeaveRequests> leaveRequests = leaveRequestService.findAll();
        List<LeaveRequestDTO> list= leaveRequests.stream().map(leaveRequestsitem -> new LeaveRequestDTO().loadFromEntity(leaveRequestsitem)).collect(Collectors.toList());
        return list;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<LeaveRequests> getRequestById(@PathVariable("id") Integer id) {
        LeaveRequests leaveRequests = leaveRequestService.findRequestById(id);
        return new ResponseEntity<>(leaveRequests, HttpStatus.OK);
    }


//xu ly appved

    @PutMapping(value = "/approve/{id}/{approve}",produces ="application/json" )
    public ResponseEntity<LeaveRequests> approve(@PathVariable("id")Integer id, @PathVariable("approve")boolean appove) throws ParseException {
        LeaveRequests leaveRequest = leaveRequestService.findRequestById(id);
        if(leaveRequest.getDateApproved()!=null || leaveRequest.getStatus()==REQUEST_STATUS.CANCELED)
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        leaveRequest.setUserApproved(usersService.findByUsername("quan"));
        leaveRequest.setDateApproved(new Date());
        leaveRequest.setStatus(appove?REQUEST_STATUS.APPROVE:REQUEST_STATUS.DENIED);
        leaveRequestService.save(leaveRequest);
        return new ResponseEntity(HttpStatus.OK);
    }
    //them request
    @Autowired
    UsersService usersService;

    @Autowired
    RequestTypeService requestTypeService;
    @PostMapping(value = "/add" ,produces ="application/json" )
    public ResponseEntity<LeaveRequests> addNewRequest(@RequestBody LeaveRequestDTO leaveRequestDTO) throws ParseException {
        LeaveRequests leaveRequests= new LeaveRequests().loadFromDTO(leaveRequestDTO);
        leaveRequests.setDateCreated(new Date());
        leaveRequests.setRequestType(requestTypeService.findByName(leaveRequestDTO.getType()));
        leaveRequests.setUserRequested(usersService.findByUsername("quan"));
        leaveRequests.setStatus(REQUEST_STATUS.PENDING);

        return new ResponseEntity<>(leaveRequestService.save(leaveRequests), HttpStatus.OK);
    }



}
