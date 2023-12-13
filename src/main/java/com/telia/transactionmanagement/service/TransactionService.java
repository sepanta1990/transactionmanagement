package com.telia.transactionmanagement.service;

import com.telia.transactionmanagement.api.bean.request.TransactionRequest;
import com.telia.transactionmanagement.api.bean.response.TransactionDto;
import com.telia.transactionmanagement.entity.Transaction;
import com.telia.transactionmanagement.exception.AccountNotFoundException;
import com.telia.transactionmanagement.exception.TransactionNotFoundException;
import com.telia.transactionmanagement.repository.AccountRepository;
import com.telia.transactionmanagement.repository.TransactionRepository;
import com.telia.transactionmanagement.service.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final Mapper mapper;
    private final AccountRepository accountRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, Mapper mapper,
                              AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.mapper = mapper;
        this.accountRepository = accountRepository;
    }

    public List<TransactionDto> getTransactions() {
        return transactionRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());

    }

    public TransactionDto createTransaction(TransactionRequest request) {
        var acc = accountRepository.findById(request.accountId()).orElseThrow(() -> new AccountNotFoundException("Account not found for ID: " + request.accountId()));

        var tx = new Transaction(acc, request.amount());
        tx = transactionRepository.save(tx);

        return mapper.toDto(tx);
    }

    public TransactionDto getTransaction(String transactionId) {
        var tx = transactionRepository.findById(transactionId).orElseThrow(() -> new TransactionNotFoundException("Transaction not found for ID: " + transactionId));
        return mapper.toDto(tx);
    }
}
