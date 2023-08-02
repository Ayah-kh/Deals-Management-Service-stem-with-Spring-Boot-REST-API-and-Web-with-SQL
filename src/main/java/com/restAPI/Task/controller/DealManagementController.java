package com.restAPI.Task.controller;


import com.restAPI.Task.entity.Deal;
import com.restAPI.Task.repository.DealRepo;
import com.restAPI.Task.request.AddDealRequest;
import com.restAPI.Task.request.ChangeDealStatusRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class DealManagementController {

    @Autowired
    private DealRepo dealRepo;

    //Add new deal
    @RequestMapping(path = "/deals", method = RequestMethod.POST)
    public ResponseEntity<String> addDeal(@RequestBody AddDealRequest dealRequest) {
        Deal deal = new Deal();
        deal.setName(dealRequest.getName());
        deal.setDescription(dealRequest.getDescription());
        deal.setStatus(dealRequest.getStatus());
        deal.setAmount(dealRequest.getAmount());
        deal.setCurrency(dealRequest.getCurrency());
        dealRepo.save(deal);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //Ability to Change Deal Status (Active, In Active, Deleted, Expired
    @RequestMapping(path = "/deals/change", method = RequestMethod.PUT)
    public ResponseEntity<String> changeStatus(@RequestBody ChangeDealStatusRequest changeDealStatusRequest) {
        Optional<Deal> optionalDeal = dealRepo.findById(changeDealStatusRequest.getId());
        if (optionalDeal.isEmpty())
            return new ResponseEntity<>("User not found", HttpStatus.UNAUTHORIZED);
        Deal deal = optionalDeal.get();
        deal.setStatus(changeDealStatusRequest.getStatus());
        dealRepo.save(deal);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Ability to view all available deals
    @RequestMapping(path = "/deals/get-available", method = RequestMethod.GET)
    public ResponseEntity<List<Deal>> getAvailableDeals() {

        return new ResponseEntity<>(dealRepo.findByStatusNot("claimed"), HttpStatus.OK);
    }

    //view of all deals saved on the database
    @RequestMapping(path = "/deals", method = RequestMethod.GET)
    public ResponseEntity<List<Deal>> getAllDeals() {
        return new ResponseEntity<>(dealRepo.findAll(), HttpStatus.OK);
    }


}
