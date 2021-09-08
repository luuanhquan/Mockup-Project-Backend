package com.controllers;

import com.DTO.LeaveRequestDTO;
import com.entity.LeaveRequests;
import com.exception.InvalidArgumentException;
import com.service.LeaveRequestService;
import com.service.UsersService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/request")
public class RequestController {
    @Autowired
    private    LeaveRequestService leaveRequestService;


    @GetMapping("")
    public List<LeaveRequestDTO> getAllLeaveRequests(Authentication authentication) {

        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN,MANAGER,PM,MEMBER"))) {
           leaveRequestService.findRequestByUserRq(authentication.getName());
        }
      List<LeaveRequests> leaveRequests = leaveRequestService.findAll();
        List<LeaveRequestDTO> list = leaveRequests.stream().map(leaveRequestsitem -> new LeaveRequestDTO().loadFromEntity(leaveRequestsitem)).collect(Collectors.toList());

        return list;
    }

    // Find id
    @GetMapping("/find/{id}")
    public ResponseEntity<LeaveRequests> getRequestById(@PathVariable("id") Integer id) {
        LeaveRequests leaveRequests = leaveRequestService.findRequestById(id);
        return new ResponseEntity<>(leaveRequests, HttpStatus.OK);
    }


    // Create Request
    @PostMapping(value = "/add", produces = "application/json")

    public ResponseEntity<LeaveRequests> addNewRequest(@RequestBody LeaveRequestDTO leaveRequestDTO, Authentication authentication) throws Exception {
         LeaveRequests leaveRequests =  leaveRequestService.addNewRequest(leaveRequestDTO);
        if (!authentication.getName().equals(leaveRequests.getUserRequested()) && authentication.getAuthorities().contains(new SimpleGrantedAuthority("MEMBER"))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return new ResponseEntity<>(leaveRequests,HttpStatus.CREATED);

    }
    // Cancel Request
    @PatchMapping("/cancel/{id}")
    public ResponseEntity<LeaveRequests> cancel(@PathVariable("id") Integer id, Authentication authentication) {
        LeaveRequests leaveRequests = leaveRequestService.findRequestById(id);
        if (!authentication.getName().equals(leaveRequests.getUserRequested()) && authentication.getAuthorities().contains(new SimpleGrantedAuthority("MEMBER"))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(leaveRequestService.cancel(id));
    }
    //  Approved request
    @PutMapping(value = "/approved/{id}")
    public ResponseEntity<LeaveRequests> approved(@PathVariable("id") Integer id, Authentication authentication) {
        LeaveRequests leaveRequests = leaveRequestService.findRequestById(id);
        if (!authentication.getName().equals(leaveRequests.getUserApproved()) && authentication.getAuthorities().contains(new SimpleGrantedAuthority("PM, MANAGER"))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(leaveRequestService.approved(id));
    }
    //  Denied request
    @PutMapping(value = "/denied/{id}")
    public ResponseEntity<LeaveRequests> denied(@PathVariable("id") Integer id, Authentication authentication) {
        LeaveRequests leaveRequests = leaveRequestService.findRequestById(id);
        if (!authentication.getName().equals(leaveRequests.getUserApproved()) && authentication.getAuthorities().contains(new SimpleGrantedAuthority("PM, MANAGER"))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(leaveRequestService.denied(id));
    }
}
