package com.javaweb.service;

import com.javaweb.model.TransactionRequest;
import com.javaweb.model.TransactionResponse;
import com.javaweb.repository.entity.TransactionsEntity;

import java.util.List;

public interface TransactionService {
    List<TransactionResponse> findByCustomerId(Long cutomerId);
    void createTransaction(TransactionRequest request);
}
