package com.nivas.spring.service;

import com.nivas.spring.modal.Transaction;

import java.util.List;

public interface ITransactionService {

    Transaction save(Transaction transaction);

    void saveAll(List<Transaction> transactions);

    List<Transaction> getAllTransactions();

    List<Transaction> findTransactions(Long pickedTime);

    Transaction findTransaction(Transaction transaction);
}
