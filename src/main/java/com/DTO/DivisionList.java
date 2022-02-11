package com.DTO;

import com.entity.Users;
import com.service.UsersService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class DivisionList {
    int id;
    String name;
    List<PM> PMList;

    public DivisionList(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
