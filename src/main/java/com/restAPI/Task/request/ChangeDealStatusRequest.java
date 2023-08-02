package com.restAPI.Task.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeDealStatusRequest {
    private Long id;
    private String status;
}
