package com.nivas.spring.service;

import com.nivas.spring.modal.LiveTransaction;
import com.nivas.spring.repository.LiveTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LiveTransactionService implements ILiveTransactionService {

    @Autowired
    private LiveTransactionRepository repository;

    @Override
    public LiveTransaction save(LiveTransaction transaction) {
        return repository.save(transaction);
    }

    @Override
    public List<LiveTransaction> getAllTransactions() {
        return repository.findAll();
    }

    @Override
    public List<LiveTransaction> findTransactions(Long pickedTime) {
        return repository.findTransactions(pickedTime);
    }

    @Override
    public LiveTransaction findTransaction(LiveTransaction transaction) {
        return repository.findTransaction(transaction.getAmount(), transaction.getCardNumber(),
                transaction.getDate(), transaction.getStatus());
    }

    @Override
    public void saveAll(List<LiveTransaction> transactions) {
        transactions.forEach(transaction -> {
            LiveTransaction checkTransaction = findTransaction(transaction);
            if (checkTransaction == null) {
                save(transaction);
            }
        });
    }
}
