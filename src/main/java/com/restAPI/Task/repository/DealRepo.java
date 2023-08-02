package com.restAPI.Task.repository;

import com.restAPI.Task.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealRepo extends JpaRepository<Deal,Long> {
    List<Deal> findByStatusNot(String status);

}
