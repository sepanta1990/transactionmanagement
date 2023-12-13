package com.telia.transactionmanagement.api.bean.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TransactionDto(@JsonProperty("transaction_id") String transactionId,
                             @JsonProperty("account_id") String accountId, Integer amount,
                             @JsonProperty("created_at") String createdAt) {
}
