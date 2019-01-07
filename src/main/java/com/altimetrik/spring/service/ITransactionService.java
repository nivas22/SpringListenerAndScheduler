package com.altimetrik.spring.service;

import com.altimetrik.spring.modal.Transaction;

import java.util.List;

public interface ITransactionService {

    Transaction save(Transaction transaction);

    List<Transaction> getAllTransactions();

    List<Transaction> findTransactions(Long pickedTime);
}
