package com.telia.transactionmanagement.controller;

import com.telia.transactionmanagement.api.bean.request.TransactionRequest;
import com.telia.transactionmanagement.api.bean.response.TransactionDto;
import com.telia.transactionmanagement.exception.TransactionNotFoundException;
import com.telia.transactionmanagement.service.TransactionService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {


    private final TransactionService transactionService;
    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDto createTransaction(@RequestBody TransactionRequest request) throws BadRequestException {
        if (ObjectUtils.isEmpty(request.accountId())) {
            throw new BadRequestException("Account ID is missing or has an incorrect type.");
        }
        if (request.amount() == null) {
            throw new BadRequestException("Amount is not valid");
        }
        return transactionService.createTransaction(request);
    }

    @GetMapping("/")
    public List<TransactionDto> getTransactions() {
        return transactionService.getTransactions();
    }

    @GetMapping("/{transaction_id}")
    public TransactionDto getTransaction(@PathVariable("transaction_id") String transactionId) throws BadRequestException {
        if (ObjectUtils.isEmpty(transactionId)) {
            throw new BadRequestException("Transaction ID is missing or has an incorrect type.");
        }
        return transactionService.getTransaction(transactionId);
    }

    @ExceptionHandler({AccountNotFoundException.class, TransactionNotFoundException.class, BadRequestException.class})
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
