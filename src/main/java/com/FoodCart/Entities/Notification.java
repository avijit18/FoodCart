package com.FoodCart.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private UserEntity customer;

    @ManyToOne
    private Restaurant restaurant;

    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    private Date sentAt;

    private boolean readStatus;
}
