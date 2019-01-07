package com.nivas.spring.task;

import com.nivas.spring.modal.TaskPreference;
import com.nivas.spring.modal.Transaction;
import com.nivas.spring.service.ITaskPreferenceService;
import com.nivas.spring.service.ITransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    public static final String TRANSACTION_TASK = "GET_TRANSACTION";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ITaskPreferenceService preferenceService;

    @Autowired
    private ITransactionService transactionService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    //Reviewed Data
    @Scheduled(cron = "${task.cron.expression}")
    public void getData() {
        logger.info("Reviewed scheduler job is started...");
        Long startedTime = System.currentTimeMillis();
        TaskPreference task = preferenceService.findByTask(TRANSACTION_TASK);
        if (task == null) {
            preferenceService.save(new TaskPreference(TRANSACTION_TASK, 0L));
        } else {
            List<Transaction> transactions = transactionService.findTransactions(task.getPickedTime());
            if (transactions.size() > 0) {
                logger.info("Event published...");
                eventPublisher.publishEvent(new OnPurgeTaskTriggerEvent(task, startedTime));
            }
        }
    }
}
