package com.restAPI.Task.repository;

import com.restAPI.Task.entity.ClaimedDeal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClaimedDealRepo extends JpaRepository<ClaimedDeal,Long> {

}
