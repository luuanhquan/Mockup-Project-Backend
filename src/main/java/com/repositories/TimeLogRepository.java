package com.repositories;

import com.entity.TimeLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TimeLogRepository extends JpaRepository<TimeLog, Integer> {
}

