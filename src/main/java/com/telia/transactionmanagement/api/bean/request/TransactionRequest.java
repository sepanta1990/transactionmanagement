package com.telia.transactionmanagement.api.bean.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TransactionRequest(@JsonProperty("account_id") String accountId, Integer amount) {
}
