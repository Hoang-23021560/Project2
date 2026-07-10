package com.javaweb.repository.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "transactionType")
public class TransactionTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")// ten loai giao dich
    private String name;
    @Column(name = "code")
    private String code;
    @OneToMany(mappedBy = "transactionTypes",fetch = FetchType.LAZY)
    private List<TransactionsEntity> transactionTypes;

    public List<TransactionsEntity> getTransactionTypes() {
        return transactionTypes;
    }

    public void setTransactionTypes(List<TransactionsEntity> transactionTypes) {
        this.transactionTypes = transactionTypes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
