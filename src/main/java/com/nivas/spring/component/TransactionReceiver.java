package com.nivas.spring.component;

import com.nivas.spring.controller.TransactionController;
import com.nivas.spring.modal.Transaction;
import com.nivas.spring.modal.TransactionDto;
import com.nivas.spring.service.ITransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class TransactionReceiver {

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd-MM-yyyy hh:mm");

    @Autowired
    private ITransactionService transactionService;

    @JmsListener(destination = "TransactionQueue", containerFactory = "connectionFactory")
    public void receiveMessage(TransactionDto transactionDto) throws ParseException {
        logger.info("Received <" + transactionDto.toString() + ">");
        Transaction transaction = new Transaction();
        transaction.setCardNumber(transactionDto.getCardNumber());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setStatus(transactionDto.getStatus());
        transaction.setDate(FORMAT.parse(transactionDto.getDate()));
        transaction.setCreated(System.currentTimeMillis());
        transactionService.save(transaction);
    }
}
