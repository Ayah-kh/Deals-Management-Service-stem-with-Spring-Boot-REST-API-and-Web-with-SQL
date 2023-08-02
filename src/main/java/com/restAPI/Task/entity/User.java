package com.restAPI.Task.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "server_date_time")
    private LocalDateTime serverDateTime;

    @CreationTimestamp
    @Column(name = "datetime_utc")
    private LocalDateTime dateTimeUtc;

    @UpdateTimestamp
    @Column(name = "update_datetime_utc")
    private LocalDateTime updateDateTimeUtc;

    @UpdateTimestamp
    @Column(name = "last_login_datetime_utc")
    private LocalDateTime lastLoginDateTimeUtc;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "status")
    private String status;

    @Column(name = "gender")
    private String gender;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "password")
    private String password;

    @Column(name = "photo",columnDefinition = "BLOB")
    @Lob
    private byte[] photo;
}
