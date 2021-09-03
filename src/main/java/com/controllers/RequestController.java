package com.controllers;


import com.DTO.LeaveRequestDTO;
import com.entity.LeaveRequests;
import com.enums.REQUEST_STATUS;
import com.service.LeaveRequestService;
import com.service.RequestTypeService;
import com.service.UsersService;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@RestController
public class RequestController {
    @Autowired
    private    LeaveRequestService leaveRequestService;
    @Autowired
    private RequestTypeService requestTypeService;
    @Autowired
    private UsersService usersService;
//    //search Request
//    @GetMapping(value = "/leaverequest/search")
//    public ResponseEntity<List<LeaveRequestDTO>> searchProduct(@RequestParam("page") Integer page,
//                                                               @RequestParam("size") Integer size,
//                                                               @RequestParam("keyword") String keyword) {
//        List<LeaveRequestDTO> list = leaveRequestService.searchRequestDisplay(keyword, page, size);
//        return new ResponseEntity<>(list, HttpStatus.OK);
//    }

    //Lay  all request
    @GetMapping(value = "/request")
    public ResponseEntity<List<LeaveRequestDTO>> getAllLeaveRequests(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                        @RequestParam(value = "size", defaultValue = "5") Integer size
    ) {
        PageRequest request = PageRequest.of(page - 1, size);
        List<LeaveRequests> leaveRequests;
        leaveRequests = leaveRequestService.findAll(request);
        List<LeaveRequestDTO> list = leaveRequests.stream().map(leaveRequestsitem -> new LeaveRequestDTO().loadFromEntity(leaveRequestsitem)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    // Find id
    @GetMapping("/request/find/{id}")
    public ResponseEntity<LeaveRequests> getRequestById(@PathVariable("id") Integer id) {
        LeaveRequests leaveRequests = leaveRequestService.findRequestById(id);
        return new ResponseEntity<>(leaveRequests, HttpStatus.OK);
    }

    /*
    @PostMapping(value = "/leaverequest/add", produces = "application/json")

    public ResponseEntity<LeaveRequests> addNewRequest(@RequestBody LeaveRequestDTO leaveRequestDTO, Authentication authentication) throws Exception {
        LeaveRequests leaveRequests = leaveRequestService.save(leaveRequestDTO);
//        boolean isMember = authentication.getAuthorities().contains(new SimpleGrantedAuthority("MEMBER"));
//        if (isMember && !authentication.getName().equals(leaveRequests.getUserRequested())) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
        return ResponseEntity.ok(leaveRequests);
    }
*/
    // Create Request
    @PostMapping(value = "request/add", produces = "application/json")

    public ResponseEntity<LeaveRequests> addNewRequest(@RequestBody LeaveRequestDTO leaveRequestDTO) throws ParseException {
        LeaveRequests leaveRequests = new LeaveRequests().loadFromDTO(leaveRequestDTO);
        leaveRequests.setDateCreated(new Date());
        leaveRequests.setRequestType(requestTypeService.findByName(leaveRequestDTO.getType()));
        leaveRequests.setUserRequested(usersService.getUserLogin());
        leaveRequests.setStatus(REQUEST_STATUS.PENDING);
        return new ResponseEntity<>(leaveRequestService.save(leaveRequests) ,HttpStatus.CREATED);

    }
    // Cancel Request
    @PatchMapping("/request/cancel/{id}")
    public ResponseEntity<LeaveRequests> cancel(@PathVariable("id") Integer id, Authentication authentication) {
        LeaveRequests leaveRequests = leaveRequestService.findRequestById(id);
        if (!authentication.getName().equals(leaveRequests.getUserRequested()) && authentication.getAuthorities().contains(new SimpleGrantedAuthority("MEMBER"))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(leaveRequestService.cancel(id));
    }
    //  Approved request
    @PutMapping(value = "/request/approved/{id}")
    public ResponseEntity<LeaveRequests> approved(@PathVariable("id") Integer id, Authentication authentication) {
        LeaveRequests leaveRequests = leaveRequestService.findRequestById(id);
        if (!authentication.getName().equals(leaveRequests.getUserRequested()) && authentication.getAuthorities().contains(new SimpleGrantedAuthority("PM, MANAGER"))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(leaveRequestService.approved(id));
    }
    //  Denied request
    @PutMapping(value = "/request/denied/{id}")
    public ResponseEntity<LeaveRequests> denied(@PathVariable("id") Integer id, Authentication authentication) {
        LeaveRequests leaveRequests = leaveRequestService.findRequestById(id);
        if (!authentication.getName().equals(leaveRequests.getUserRequested()) && authentication.getAuthorities().contains(new SimpleGrantedAuthority("PM, MANAGER"))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(leaveRequestService.denied(id));
    }
}
