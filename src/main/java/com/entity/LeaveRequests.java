package com.entity;

import com.dto.LeaveRequestDTO;
import com.entity.enums.REQUEST_STATUS;
import com.entity.enums.UNIT_TYPE;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.service.LeaveRequestService;
import com.service.RequestTypeService;
import com.service.UsersService;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "LEAVE_REQUESTS", schema = "TEAM1", catalog = "")
public class LeaveRequests {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Basic
    @Column(name = "DATE_REQUESTED")
    private Date dateRequested;
    @Basic
    @Column(name = "DATE_APPROVED")
    private Date dateApproved;


    @Basic
    @Column(name = "STATUS")
    private long status;

    public REQUEST_STATUS getStatus() {
        return REQUEST_STATUS.valueOf((int) status);
    }

    public void setStatus(REQUEST_STATUS status) {
        this.status = status.value;
    }

    @Basic
    @Column(name = "DURATION")
    private long duration;
    @Column(name = "DATE_CREATED")
    private Date dateCreated;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "USER_REQUESTED", referencedColumnName = "ID", nullable = false)
    private Users userRequested;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "USER_APPROVED", referencedColumnName = "ID", nullable = false)
    private Users userApproved;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "TYPE", referencedColumnName = "ID", nullable = false)
    private RequestType requestType;


    public LeaveRequests loadFromDTO(LeaveRequestDTO dto) throws ParseException {
        this.dateRequested= this.getDate(dto.getDateRequested());
        this.duration=dto.getDuration();
        return this;
    }


    private Date getDate(String date) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse(date);
    }






}
