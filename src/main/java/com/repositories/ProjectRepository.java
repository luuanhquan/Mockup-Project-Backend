package com.repositories;

import com.DTO.ProjectDetailDTO;
import com.DTO.UserSimpleDTO;
import com.entity.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface ProjectRepository extends JpaRepository<Projects, Integer> {

    @Query ("select new com.DTO.ProjectDetailDTO(p.id," +
            "p.name," +
            "p.description," +
            "p.dateStated," +
            "p.dateEnded," +
            "p.status, p.issueList.size, p.projectUserList.size) from Projects p where p.status=1")
    List<ProjectDetailDTO> findAllActive();

}

