package com.javaweb.repository;

import com.javaweb.repository.entity.TransactionTypeEntity;
import com.javaweb.repository.entity.TransactionsEntity;

import java.util.List;

public interface TransactionRepository {
    List<TransactionsEntity> findByIdCustomer(Long customerId);
    // lich su giao dich khach hang

    void createTransaction(TransactionsEntity transactionsEntity);
    TransactionTypeEntity findByTransactionTypeId(Long id);
}
