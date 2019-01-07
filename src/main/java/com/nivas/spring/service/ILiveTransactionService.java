package com.nivas.spring.service;

import com.nivas.spring.modal.LiveTransaction;

import java.util.List;

public interface ILiveTransactionService {

    LiveTransaction save(LiveTransaction liveTransaction);

    void saveAll(List<LiveTransaction> liveTransactions);

    List<LiveTransaction> getAllTransactions();

    List<LiveTransaction> findTransactions(Long pickedTime);

    LiveTransaction findTransaction(LiveTransaction liveTransaction);
}
