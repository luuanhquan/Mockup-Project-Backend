package com.entity;

import com.entity.enums.ACTIVE_STATUS;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "STATUS")
    private long status;

    public ACTIVE_STATUS getStatus() {
        return ACTIVE_STATUS.valueOf((int) status);
    }

    public void setStatus(ACTIVE_STATUS status) {
        this.status = status.value;
    }
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "MANAGERID", referencedColumnName = "ID", nullable = false)
    private Users users;
    @OneToMany(mappedBy = "division")
    private Collection<DivisionProject> divisionProjectsList;
    @OneToMany(mappedBy = "division")
    private Collection<Users> usersList;






}
