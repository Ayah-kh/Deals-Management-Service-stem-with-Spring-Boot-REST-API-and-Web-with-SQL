package com.restAPI.Task.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class AddUserRequest {
    private String name;
    private String email;
    private String phone;
    private String status;
    private String gender;
    private LocalDate dateOfBirth;
    private String password;
}
