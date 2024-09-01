package com.FoodCart.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private @NonNull String token;

    @ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
    private @NonNull UserEntity user;

    private @NonNull Date expiryDate;

    public boolean isExpired() {
        return expiryDate.before(new Date());
    }
}
