package com.telia.transactionmanagement.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
public class Transaction {

    @Id
    private String transactionId = UUID.randomUUID().toString();

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    // Amount should be BigDecimal in practice. Using Integer because of the API's documentation
    private Integer amount;
    private Instant created = Instant.now();

    public Transaction() {
    }

    public Transaction(Account account, Integer amount) {
        this.account = account;
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }
}
