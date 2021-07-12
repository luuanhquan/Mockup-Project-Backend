package com.entity;

import com.entity.enumFolder.ActiveStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;


    @Column(name = "STATUS")
    private long status;

    public ActiveStatus getStatus() {
        return ActiveStatus.valueOf((int) status);
    }

    public void setStatus(ActiveStatus status) {
        this.status = status.value;
    }
    @ManyToOne
    @JoinColumn(name = "MANAGERID", referencedColumnName = "ID", nullable = false)
    private Users users;
    @OneToMany(mappedBy = "division")
    private Collection<DivisionProject> divisionProjectsList;
    @OneToMany(mappedBy = "division")
    private Collection<Users> usersList;






}
