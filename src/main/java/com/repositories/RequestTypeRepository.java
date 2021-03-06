package com.repositories;

import com.entity.RequestType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface RequestTypeRepository extends JpaRepository<RequestType, Integer> {
    @Query("from RequestType r where r.name=?1")
    public RequestType findByName(String name);
}


