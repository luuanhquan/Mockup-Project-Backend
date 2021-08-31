package com.repositories;

import com.DTO.FileDTO;
import com.entity.FileIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface FileIssueRepository extends JpaRepository<FileIssue, Integer> {
    @Query("select new com.DTO.FileDTO(f.id, f.name, f.path) from FileIssue fi join Files f on f.id=fi.file.id where fi.issue.id=?1")
    List<FileDTO> findByIssueId(int id);
}

