package com.DTO;

import com.entity.LeaveRequests;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class LeaveRequestDTO {
    private Integer id;
    private String userRequested;
    private String userApproved;
    private String dateRequested;
    private String dateApproved;
    private long duration;
    private String unit;
    private String type;
    private String status;
    private String dateCreated;

    public LeaveRequestDTO loadFromEntity(LeaveRequests requests) {
        this.id = requests.getId();
        this.userRequested = requests.getUserRequested().getUsername();
        this.dateRequested = formatDate(requests.getDateRequested());

        this.userApproved= requests.getUserApproved().getUsername();
        this.dateApproved=  formatDate(requests.getDateApproved());
        this.duration = requests.getDuration();
        this.unit = requests.getRequestType().getUnit().name();
        this.type = requests.getRequestType().getName();
        this.status = requests.getStatus().name();
        this.dateCreated = formatDate(requests.getDateCreated());
        return this;
    }

    private String formatDate(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }

}
