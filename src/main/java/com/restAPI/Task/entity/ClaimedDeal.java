package com.restAPI.Task.entity;


import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "claimed_deals")
public class ClaimedDeal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User userId;

    @ManyToOne
    private Deal dealId;

    @CreationTimestamp
    @Column(name = "server_date_time")
    private LocalDateTime serverDateTime;

    @Column(name = "datetime_utc")
    private LocalDateTime dateTimeUtc;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "currency")
    private String currency;

}

