package com.restAPI.Task.controller;

import com.restAPI.Task.entity.ClaimedDeal;
import com.restAPI.Task.entity.Deal;
import com.restAPI.Task.entity.User;
import com.restAPI.Task.repository.ClaimedDealRepo;
import com.restAPI.Task.repository.DealRepo;
import com.restAPI.Task.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ClaimedDealController {

    @Autowired
    private ClaimedDealRepo claimedDealRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private DealRepo dealRepo;

    //Ability to claim the selected deal.
    @RequestMapping(path = "/claimed-deals/users/{userId}/deals/{dealId}", method = RequestMethod.POST)
    public ResponseEntity<String> claimDeal(@PathVariable long userId, @PathVariable long dealId) {
        Optional<User> optionalUser = userRepo.findById(userId);
        Optional<Deal> optionalDeal = dealRepo.findById(dealId);

        if (optionalUser.isPresent() && optionalDeal.isPresent()) {
            User user = optionalUser.get();
            Deal deal = optionalDeal.get();

            if (deal.getStatus().equals("claimed"))
                return new ResponseEntity<>("this deal is already claimed",HttpStatus.CONFLICT);

            deal.setStatus("claimed");
            dealRepo.save(deal);

            ClaimedDeal claimedDeal = new ClaimedDeal();
            claimedDeal.setUserId(user);
            claimedDeal.setDealId(deal);
            claimedDeal.setAmount(deal.getAmount());
            claimedDeal.setCurrency(deal.getCurrency());

            claimedDealRepo.save(claimedDeal);

            return new ResponseEntity<>("Deal Claimed Successfully", HttpStatus.CREATED);
        } else
            return new ResponseEntity<>("user or deal not found", HttpStatus.UNAUTHORIZED);
    }

    //Show Count and Total amounts of claimed deals on user profile
    @RequestMapping(path = "/claimed-deals",method = RequestMethod.GET)
    public ResponseEntity<String> getNoOfClaimedDeals (){

        int size = claimedDealRepo.findAll().size();
        return new ResponseEntity<>("number of claimed deals: "+size,HttpStatus.OK);
    }
}
