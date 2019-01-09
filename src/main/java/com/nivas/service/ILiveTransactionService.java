package com.nivas.service;

import com.nivas.modal.LiveTransaction;
import com.nivas.modal.Transaction;

import java.util.List;

public interface ILiveTransactionService {

    LiveTransaction save(LiveTransaction liveTransaction);

    void saveAll(List<LiveTransaction> liveTransactions);

    List<LiveTransaction> getAllTransactions();

    List<LiveTransaction> findTransactions(Long pickedTime);

    LiveTransaction findTransaction(LiveTransaction liveTransaction);

    void save(List<com.nivas.avro.Transaction> transactionList);

    void saveTransactions(List<Transaction> transactionList);
}
