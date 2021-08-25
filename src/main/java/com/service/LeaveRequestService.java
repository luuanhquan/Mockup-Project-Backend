package com.service;

import com.DTO.LeaveRequestDTO;
import com.entity.LeaveRequests;
import com.enums.REQUEST_STATUS;
import com.enums.RESULT;
import com.exception.InvalidArgumentException;
import com.exception.MyException;
import com.repositories.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class LeaveRequestService {

    @Autowired
    LeaveRequestRepository leaveRequestRepository;
    @Autowired
    UsersService usersService;

    @Autowired
    RequestTypeService requestTypeService;
    //  Find All Request
    public List<LeaveRequests> findAll(Pageable pageable) {
//        PageRequest pageRequest;
//        pageRequest = PageRequest.of(page - 1, size);
        return leaveRequestRepository.findAll();
    }

//    public List<LeaveRequests> findAll() {
//        return leaveRequestRepository.findAll();
//    }

    //    Find One Request
    public LeaveRequests findRequestById(Integer id) {
        LeaveRequests leaveRequests = leaveRequestRepository.findRequestById(id);
        if (leaveRequests == null) {
            throw new MyException(RESULT.REQUEST_NOT_FOUND);
        }
        return leaveRequests;
    }
    //save
    /*
     public LeaveRequests addNewRequest(LeaveRequestDTO leaveRequestDTO)  throws Exception {
        LeaveRequests leaveRequests = new LeaveRequests().loadFromDTO(leaveRequestDTO);
        leaveRequests.setDateCreated(new Date());
        leaveRequests.setRequestType(requestTypeService.findByName(leaveRequestDTO.getType()));
        leaveRequests.setUserRequested(usersService.getUserLogin());
        leaveRequests.setStatus(REQUEST_STATUS.PENDING);
        return leaveRequests;
    }
    }

     */
    public LeaveRequests save(LeaveRequests leaveRequests) {
        return leaveRequestRepository.save(leaveRequests);
    }


    //    Approved Request
    public LeaveRequests approved(Integer id, Boolean appove) {
        LeaveRequests leaveRequests = findRequestById(id);
        leaveRequests.setUserApproved(usersService.getUserLogin());

        if (!leaveRequests.getStatus().equals(REQUEST_STATUS.PENDING)) {
            throw new MyException(RESULT.REQUEST_STATUS_ERROR);
        }
        leaveRequests.setStatus(appove ? REQUEST_STATUS.APPROVE : REQUEST_STATUS.DENIED);

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
    //seach
//    public List<LeaveRequestDTO> searchRequestDisplay(String keyword, Integer page, Integer size) {
//        if (Objects.isNull(page) || Objects.isNull(size)) {
//            throw new InvalidArgumentException("Page and size are required");
//        }
//        PageRequest pageRequest = PageRequest.of(page, size);
//        List<LeaveRequests> leaveRequests = leaveRequestRepository.findAllByNameContainingIgnoreCase(keyword, pageRequest);
//        return leaveRequests.stream().map(leaveRequestsitem -> new LeaveRequestDTO().loadFromEntity(leaveRequestsitem)).collect(Collectors.toList());
//    }

}
