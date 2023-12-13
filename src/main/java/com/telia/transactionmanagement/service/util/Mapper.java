package com.telia.transactionmanagement.service.util;

import com.telia.transactionmanagement.api.bean.response.AccountDto;
import com.telia.transactionmanagement.api.bean.response.TransactionDto;
import com.telia.transactionmanagement.entity.Account;
import com.telia.transactionmanagement.entity.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Mapper {
    public TransactionDto toDto(Transaction tx) {
        return Optional.ofNullable(tx)
                .map(t -> new TransactionDto(tx.getTransactionId().toString(), tx.getAccount().getAccountId().toString(),
                        tx.getAmount(), tx.getCreated().toString()))
                .orElse(null);
    }

    public List<TransactionDto> toDto(List<Transaction> txList) {
        return txList.stream()
                .map(this::toDto)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public AccountDto toDto(Account acc) {
        return Optional.ofNullable(acc)
                .map(t -> new AccountDto(acc.getAccountId().toString()))
                .orElse(null);
    }
}
