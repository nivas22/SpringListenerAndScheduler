package com.nivas.task.listener;

import com.nivas.modal.LiveTransaction;
import com.nivas.modal.Transaction;
import com.nivas.service.ILiveTransactionService;
import com.nivas.service.ITaskPreferenceService;
import com.nivas.service.ITransactionService;
import com.nivas.task.OnPurgeTaskTriggerEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.nivas.task.TaskService.TRANSACTION_TASK;

@Component
public class PurgeTaskListener implements ApplicationListener<OnPurgeTaskTriggerEvent> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ITransactionService transactionService;

    @Autowired
    private ILiveTransactionService currentTransactionService;

    @Autowired
    private ITaskPreferenceService preferenceService;

    @Override
    public void onApplicationEvent(OnPurgeTaskTriggerEvent onPurgeTaskTriggerEvent) {
        this.purgeEvent(onPurgeTaskTriggerEvent);
    }

    private void purgeEvent(OnPurgeTaskTriggerEvent onPurgeTaskTriggerEvent) {
        logger.info("Purge started on;"+onPurgeTaskTriggerEvent.getTriggeredDate().toString());
        List<Transaction> transactions = transactionService.findTransactions(onPurgeTaskTriggerEvent.getTask().getPickedTime());
        logger.info("Total Transaction Count:"+transactions.size());

        //Duplication Removal using LinkedHashSet
        Set<Transaction> originalRecords = new LinkedHashSet<>(transactions);
        logger.info("After remove duplication using LinkedHashSet Transaction Count:" + originalRecords.size());

        //Duplication Removal Using Stream
        List<Transaction> collect = transactions.stream().distinct().collect(Collectors.toList());
        logger.info("After remove duplication using Stream Transaction Count:" + collect.size());

        //Save Live transaction into DB
        List<LiveTransaction> liveTransactions = new ArrayList<>();
        collect.forEach(transaction -> {
            LiveTransaction liveTransaction = new LiveTransaction();
            liveTransaction.setAmount(transaction.getAmount());
            liveTransaction.setCardNumber(transaction.getCardNumber());
            liveTransaction.setStatus(transaction.getStatus());
            liveTransaction.setDate(transaction.getDate());
            liveTransaction.setCreated(System.currentTimeMillis());
            liveTransactions.add(liveTransaction);

        });
        currentTransactionService.saveAll(liveTransactions);

        preferenceService.updateTask(TRANSACTION_TASK, onPurgeTaskTriggerEvent.getTriggeredDate());
    }
}
