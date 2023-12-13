package com.telia.transactionmanagement.api.bean.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AccountDto(@JsonProperty("account_id") String accountId) {
}
