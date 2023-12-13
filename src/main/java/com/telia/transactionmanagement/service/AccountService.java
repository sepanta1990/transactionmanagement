package com.telia.transactionmanagement.service;

import com.telia.transactionmanagement.api.bean.request.AccountRequest;
import com.telia.transactionmanagement.api.bean.response.AccountDto;
import com.telia.transactionmanagement.entity.Account;
import com.telia.transactionmanagement.exception.AccountNotFoundException;
import com.telia.transactionmanagement.repository.AccountRepository;
import com.telia.transactionmanagement.service.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final Mapper mapper;

    @Autowired
    public AccountService(AccountRepository accountRepository, Mapper mapper) {
        this.accountRepository = accountRepository;
        this.mapper = mapper;
    }

    public AccountDto createAccount(AccountRequest request) {
        var acc = new Account();

        acc = accountRepository.save(acc);
        return mapper.toDto(acc);
    }

    public AccountDto getAccount(String accountId) {
        var acc = accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException("Account not found for ID: " + accountId));
        return mapper.toDto(acc);
    }
}
