package com.restAPI.Task.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeUserStatusRequest {
    private long id;
    private String status;
}
