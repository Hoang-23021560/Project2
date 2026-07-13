package com.javaweb.service.impl;

import com.javaweb.model.TransactionRequest;
import com.javaweb.model.TransactionResponse;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.repository.entity.CustomerEntity;
import com.javaweb.repository.entity.TransactionTypeEntity;
import com.javaweb.repository.entity.TransactionsEntity;
import com.javaweb.service.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<TransactionResponse> findByCustomerId(Long cutomerId) {
        List<TransactionsEntity> transactions = transactionRepository.findByIdCustomer(cutomerId);
        List<TransactionResponse> responses = new ArrayList<>();
        for(TransactionsEntity transaction: transactions){
            TransactionResponse response = new TransactionResponse();
            response.setId(transaction.getId());
            response.setNote(transaction.getNote());
            response.setCreateDate(transaction.getCreatedDate());
            response.setTransactionType(transaction.getTransactionTypes().getName());
            responses.add(response);
        }
        return responses;
    }

    @Override
    @Transactional
    public void createTransaction(TransactionRequest request) {
        TransactionsEntity transaction = new TransactionsEntity();

        transaction.setNote(request.getNote());
        CustomerEntity customer = customerRepository.findById(request.getCustomerId());
        if (customer == null) {
            throw new RuntimeException("Khách hàng không tồn tại");
        }
        transaction.setCustomers(customer);
        TransactionTypeEntity transactionType = transactionRepository.findByTransactionTypeId(request.getTransactionTypeId());

        if (transactionType == null) {
            throw new RuntimeException("Loại giao dịch không tồn tại");
        }
        transaction.setTransactionTypes(transactionType);
        transactionRepository.createTransaction(transaction);
    }
}
