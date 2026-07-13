package com.javaweb.api;

import com.javaweb.model.TransactionRequest;
import com.javaweb.model.TransactionResponse;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.service.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionAPI {
    @Autowired
    private TransactionService transactionService;
    @GetMapping("/api/transaction/")
    @Transactional
    public List<TransactionResponse> getTransaction(Long customerId){
        List<TransactionResponse> result = transactionService.findByCustomerId(customerId);
        return result;
    }
    @PostMapping("/api/transaction/")
    @Transactional
    public void createTransaction(@RequestBody TransactionRequest request){
        transactionService.createTransaction(request);
    }

}
