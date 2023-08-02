package com.restAPI.Task.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddDealRequest {

    private String name;
    private String description;
    private String status;
    private Double amount;
    private String currency;
}
