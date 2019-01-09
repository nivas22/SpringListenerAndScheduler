package com.nivas.service;

import com.nivas.modal.LiveTransaction;
import com.nivas.modal.Transaction;
import com.nivas.repository.LiveTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public void save(List<com.nivas.avro.Transaction> transactionList) {
        transactionList.forEach(transaction -> {
            LiveTransaction data = new LiveTransaction();
            data.setAmount(transaction.getAmount());
            data.setCardNumber(transaction.getCardNumber().toString());
            data.setStatus(transaction.getStatus().toString());
            Integer date = transaction.getDate() * 1000;
            Date date1 = new Date(date);
            data.setDate(date1);
            data.setCreated(System.currentTimeMillis());
            save(data);
        });
    }

    @Override
    public void saveTransactions(List<Transaction> transactionList) {
        transactionList.forEach(transaction -> {
            LiveTransaction data = new LiveTransaction();
            data.setAmount(transaction.getAmount());
            data.setCardNumber(transaction.getCardNumber());
            data.setStatus(transaction.getStatus());
            data.setDate(transaction.getDate());
            data.setCreated(System.currentTimeMillis());
            save(data);
        });
    }
}
