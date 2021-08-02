package com.entity;

import com.entity.enums.UNIT_TYPE;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "REQUEST_TYPE", schema = "TEAM1", catalog = "")
public class RequestType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "UNIT")
    private long unit;
    public UNIT_TYPE getUnit() {
        return UNIT_TYPE.valueOf((int) unit);
    }

    public void setUnit(UNIT_TYPE status) {
        this.unit = status.value;
    }
    @OneToMany(mappedBy = "requestType")
    private Collection<LeaveRequests> leaveRequestList;

}
