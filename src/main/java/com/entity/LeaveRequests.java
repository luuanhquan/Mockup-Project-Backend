package com.entity;

import com.entity.enumFolder.RequestStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
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
    private int id;
    @Basic
    @Column(name = "DATE_REQUESTED")
    private Date dateRequested;
    @Basic
    @Column(name = "DATE_APPROVED")
    private Date dateApproved;


    @Basic
    @Column(name = "STATUS")
    private long status;

    public RequestStatus getStatus() {
        return RequestStatus.valueOf((int) status);
    }

    public void setStatus(RequestStatus status) {
        this.status = status.value;
    }

    @Basic
    @Column(name = "DURATION")
    private long duration;
    @Column(name = "DATE_CREATED")
    private Date dateCreated;
    @ManyToOne
    @JoinColumn(name = "USER_REQUESTED", referencedColumnName = "ID", nullable = false)
    private Users userRequested;
    @ManyToOne
    @JoinColumn(name = "USER_APPROVED", referencedColumnName = "ID", nullable = false)
    private Users userApproved;
    @ManyToOne
    @JoinColumn(name = "TYPE", referencedColumnName = "ID", nullable = false)
    private RequestType requestType;

}
