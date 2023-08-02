package com.restAPI.Task.entity;


import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;
import javax.persistence.*;


import java.time.LocalDateTime;

@Entity
@Table(name = "deals")
@Getter
@Setter
public class Deal {

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

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "currency")
    private String currency;

}

