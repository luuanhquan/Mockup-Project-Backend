package com.entity;

import com.DTO.LeaveRequestDTO;
import com.enums.REQUEST_STATUS;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "LEAVE_REQUESTS", schema = "TEAM1")
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

    public REQUEST_STATUS getStatus() {
        return REQUEST_STATUS.valueOf((int) status);
    }

    public void setStatus(REQUEST_STATUS status) {
        this.status = status.value;
    }

    public LeaveRequests loadFromDTO(LeaveRequestDTO dto) throws ParseException {
        this.dateRequested = this.getDate2(dto.getDateRequested());
        this.duration = dto.getDuration();
        return this;
    }


//    private Date getDate(String date) throws ParseException {
//        return new SimpleDateFormat("dd/MM/yyyy").parse(date);
//    }

    private Date getDate2(String date2) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(date2);
    }


}
