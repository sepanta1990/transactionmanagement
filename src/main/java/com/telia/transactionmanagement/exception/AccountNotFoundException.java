package com.telia.transactionmanagement.exception;

public class AccountNotFoundException extends RecordNotFoundException {
    public AccountNotFoundException(String message) {
        super(message);
    }
}
