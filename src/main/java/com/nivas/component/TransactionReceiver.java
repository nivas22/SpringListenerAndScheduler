package com.nivas.component;

import com.nivas.avro.TransactionListDto;
import com.nivas.controller.TransactionController;
import com.nivas.modal.Transaction;
import com.nivas.modal.TransactionDto;
import com.nivas.service.AvroSerialization;
import com.nivas.service.ILiveTransactionService;
import com.nivas.service.ITransactionService;
import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class TransactionReceiver {

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd-MM-yyyy hh:mm");

    @Autowired
    private ITransactionService transactionService;

    @Autowired
    private ILiveTransactionService liveTransactionService;

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


    @JmsListener(destination = "AvroSeQueue", containerFactory = "connectionFactory")
    public void receiveFromProducer(byte[] data) throws Exception {
        logger.info("Avro Deserialization started:" + new Date(System.currentTimeMillis()));
        TransactionListDto transactionListDto = AvroSerialization.deserialize(data);
        logger.info("Avro Deserialization completed:" + new Date(System.currentTimeMillis()));
        liveTransactionService.save(transactionListDto.getTransactionList());
        logger.info("Received <" + String.valueOf(transactionListDto.getTransactionList().size()) + ">");
    }

    @JmsListener(destination = "JavaSeQueue", containerFactory = "connectionFactory")
    public void receiveFromProducerJavaSe(byte[] data) {
        logger.info("Java Deserialization started:" + new Date(System.currentTimeMillis()));
        List<Transaction> transactionList = SerializationUtils.deserialize(data);
        logger.info("Java Deserialization completed:" + new Date(System.currentTimeMillis()));
        liveTransactionService.saveTransactions(transactionList);
        logger.info("Received <" + String.valueOf(transactionList.size()) + ">");
    }
}
