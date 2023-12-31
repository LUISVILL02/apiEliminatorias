package com.eliminatorias.apieliminatorias.repositories;

import com.eliminatorias.apieliminatorias.models.entities.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
}
