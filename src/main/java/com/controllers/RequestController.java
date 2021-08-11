package com.controllers;


import com.DTO.LeaveRequestDTO;
import com.entity.LeaveRequests;
import com.enums.REQUEST_STATUS;
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
@RequestMapping("/leaverequest")
public class RequestController {
    private final LeaveRequestService leaveRequestService;
    //them request
    @Autowired
    UsersService usersService;
    @Autowired
    RequestTypeService requestTypeService;

    @Autowired
    public RequestController(LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }


//xu ly appved

    //Lay  all request
    @GetMapping("/list")
    public List<LeaveRequestDTO> getAllLeaveRequests() {
        List<LeaveRequests> leaveRequests = leaveRequestService.findAll();
        List<LeaveRequestDTO> list = leaveRequests.stream().map(leaveRequestsitem -> new LeaveRequestDTO().loadFromEntity(leaveRequestsitem)).collect(Collectors.toList());
        return list;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<LeaveRequests> getRequestById(@PathVariable("id") Integer id) {
        LeaveRequests leaveRequests = leaveRequestService.findRequestById(id);
        return new ResponseEntity<>(leaveRequests, HttpStatus.OK);
    }

    @PutMapping(value = "/approve/{id}/{approve}", produces = "application/json")
    public ResponseEntity<LeaveRequests> approve(@PathVariable("id") Integer id, @PathVariable("approve") boolean appove) throws ParseException {
        LeaveRequests leaveRequest = leaveRequestService.findRequestById(id);
        if (leaveRequest.getDateApproved() != null
                || leaveRequest.getStatus() == REQUEST_STATUS.CANCELED)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        leaveRequest.setUserApproved(usersService.getUserLogin());
        leaveRequest.setDateApproved(new Date());
        leaveRequest.setStatus(appove ? REQUEST_STATUS.APPROVE : REQUEST_STATUS.DENIED);
        leaveRequestService.save(leaveRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<LeaveRequests> addNewRequest(@RequestBody LeaveRequestDTO leaveRequestDTO) throws ParseException {
        LeaveRequests leaveRequests = new LeaveRequests().loadFromDTO(leaveRequestDTO);
        leaveRequests.setDateCreated(new Date());
        leaveRequests.setRequestType(requestTypeService.findByName(leaveRequestDTO.getType()));
        leaveRequests.setUserRequested(usersService.getUserLogin());
        leaveRequests.setStatus(REQUEST_STATUS.PENDING);

        return new ResponseEntity<>(leaveRequestService.save(leaveRequests), HttpStatus.OK);
    }


}
