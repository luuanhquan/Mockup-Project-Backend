package com.repositories;

import com.entity.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface DivisionRepository extends JpaRepository<Division, Integer> {

    @Override
    Optional<Division> findById(Integer integer);

    @Override
    List<Division> findAll();

    @Override
    void delete(Division division);

    @Override
    <S extends Division> S save(S s);
}

