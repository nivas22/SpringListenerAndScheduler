package com.altimetrik.spring.task.listener;

import com.altimetrik.spring.modal.Transaction;
import com.altimetrik.spring.service.ITaskPreferenceService;
import com.altimetrik.spring.service.ITransactionService;
import com.altimetrik.spring.task.OnPurgeTaskTriggerEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static com.altimetrik.spring.task.TaskService.TRANSACTION_TASK;

@Component
public class PurgeTaskListener implements ApplicationListener<OnPurgeTaskTriggerEvent> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private
    ITransactionService transactionService;

    @Autowired
    private
    ITaskPreferenceService preferenceService;

    @Override
    public void onApplicationEvent(OnPurgeTaskTriggerEvent onPurgeTaskTriggerEvent) {
        this.purgeEvent(onPurgeTaskTriggerEvent);
    }

    private void purgeEvent(OnPurgeTaskTriggerEvent onPurgeTaskTriggerEvent) {
        logger.info("Purge started on;"+onPurgeTaskTriggerEvent.getTriggeredDate().toString());
        List<Transaction> transactions = transactionService.findTransactions(onPurgeTaskTriggerEvent.getTask().getPickedTime());
        logger.info("Total Transaction Count:"+transactions.size());
        Set<Transaction> originalRecords = new LinkedHashSet<>(transactions);
        logger.info("After remove duplication Transaction Count:"+originalRecords.size());
        preferenceService.updateTask(TRANSACTION_TASK, onPurgeTaskTriggerEvent.getTriggeredDate());
    }
}
