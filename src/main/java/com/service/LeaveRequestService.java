package com.service;


import com.DTO.LeaveRequestDTO;
import com.entity.LeaveRequests;
import com.enums.REQUEST_STATUS;
import com.enums.RESULT;
import com.exception.MyException;
import com.repositories.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class LeaveRequestService {

    @Autowired
    LeaveRequestRepository leaveRequestRepository;
    @Autowired
    UsersService usersService;

    @Autowired
    RequestTypeService requestTypeService;
    //  Find All Request
    public List<LeaveRequests> findAll() {

        return leaveRequestRepository.findAll();
    }

    public LeaveRequests findRequestById(Integer id) {
        LeaveRequests leaveRequests = leaveRequestRepository.findRequestById(id);
        if (leaveRequests == null) {
            throw new MyException(RESULT.REQUEST_NOT_FOUND);
        }
        return leaveRequests;
    }

    public LeaveRequests findRequestByUserRq(String userRequested) {
        LeaveRequests leaveRequests = leaveRequestRepository.findRequestByUserRq(userRequested);
        if (leaveRequests == null) {
            throw new MyException(RESULT.REQUEST_NOT_USER);
        }
        return leaveRequests;

    }

    //save

    public LeaveRequests save(LeaveRequests leaveRequests) {
        return leaveRequestRepository.save(leaveRequests);
    }
    //add
    public LeaveRequests addNewRequest(LeaveRequestDTO leaveRequestDTO)  throws Exception {
        LeaveRequests leaveRequests = new LeaveRequests().loadFromDTO(leaveRequestDTO);
        leaveRequests.setDateCreated(new Date());
        leaveRequests.setRequestType(requestTypeService.findByName(leaveRequestDTO.getType()));
        leaveRequests.setUserRequested(usersService.getUserLogin());
        leaveRequests.setStatus(REQUEST_STATUS.PENDING);
        return leaveRequestRepository.save(leaveRequests);
    }

//    Approved Request
public LeaveRequests approved(Integer id) {
    LeaveRequests leaveRequests = findRequestById(id);
    if (!leaveRequests.getStatus().equals(REQUEST_STATUS.PENDING)) {
        throw new MyException(RESULT.REQUEST_STATUS_ERROR);
    }
    leaveRequests.setStatus(REQUEST_STATUS.APPROVE);
    return leaveRequestRepository.save(leaveRequests);
}
// Denied
    public LeaveRequests denied(Integer id) {
        LeaveRequests leaveRequests = findRequestById(id);
        if (!leaveRequests.getStatus().equals(REQUEST_STATUS.PENDING)) {
            throw new MyException(RESULT.REQUEST_STATUS_ERROR);
        }
        leaveRequests.setStatus(REQUEST_STATUS.DENIED);
        return leaveRequestRepository.save(leaveRequests);
    }
    //    Cancel Request
    public LeaveRequests cancel(Integer id) {
        LeaveRequests leaveRequests = findRequestById(id);
        if (!leaveRequests.getStatus().equals(REQUEST_STATUS.PENDING)) {
            throw new MyException(RESULT.REQUEST_STATUS_ERROR);
        }
        leaveRequests.setStatus(REQUEST_STATUS.CANCELED);
        return leaveRequestRepository.save(leaveRequests);
    }


}
