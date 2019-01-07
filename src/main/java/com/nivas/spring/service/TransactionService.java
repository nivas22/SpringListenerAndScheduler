package com.nivas.spring.service;

import com.nivas.spring.modal.Transaction;
import com.nivas.spring.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    private TransactionRepository repository;

    @Override
    public Transaction save(Transaction transaction) {
        return repository.save(transaction);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return repository.findAll();
    }

    @Override
    public List<Transaction> findTransactions(Long pickedTime) {
        return repository.findTransactions(pickedTime);
    }

    @Override
    public Transaction findTransaction(Transaction transaction) {
        return repository.findTransaction(transaction.getAmount(), transaction.getCardNumber(),
                transaction.getDate(), transaction.getStatus());
    }

    @Override
    public void saveAll(List<Transaction> transactions) {
        transactions.forEach(transaction -> {
            Transaction checkTransaction = findTransaction(transaction);
            if (checkTransaction == null) {
                save(transaction);
            }
        });
    }
}
