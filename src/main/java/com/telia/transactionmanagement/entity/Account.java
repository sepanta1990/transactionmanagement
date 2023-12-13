package com.telia.transactionmanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.Instant;
import java.util.UUID;

@Entity
public class Account {
    @Id
    private String accountId = UUID.randomUUID().toString();


    private Instant created = Instant.now();

    public String getAccountId() {
        return accountId;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }
}
