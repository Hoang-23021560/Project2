package com.javaweb.repository.impl;

import com.javaweb.repository.TransactionRepository;
import com.javaweb.repository.entity.TransactionTypeEntity;
import com.javaweb.repository.entity.TransactionsEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class TransactionRepositoryImpl implements TransactionRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<TransactionsEntity> findByIdCustomer(Long customerId) {

        String jpql = "FROM TransactionsEntity t WHERE t.customers.id = :customerId";

        return entityManager.createQuery(jpql, TransactionsEntity.class)
                .setParameter("customerId", customerId)
                .getResultList();
    }


    @Override
    public void createTransaction(TransactionsEntity transactionsEntity) {
        entityManager.persist(transactionsEntity);
    }

    @Override
    public TransactionTypeEntity findByTransactionTypeId(Long id) {
       return  entityManager.find(TransactionTypeEntity.class,id);
    }
}
