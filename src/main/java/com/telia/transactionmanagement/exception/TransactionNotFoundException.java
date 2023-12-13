package com.telia.transactionmanagement.exception;

public class TransactionNotFoundException extends RecordNotFoundException {
    public TransactionNotFoundException(String message) {
        super(message);
    }
}
