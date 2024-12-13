package com.product.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="tokens")
public class Token extends BaseModel{

        private String value;
        @ManyToOne
        private User user;
        private long expiryAt;
}
