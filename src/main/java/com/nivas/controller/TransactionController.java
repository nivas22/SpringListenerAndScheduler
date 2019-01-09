package com.nivas.controller;

import com.nivas.modal.Transaction;
import com.nivas.modal.TransactionDto;
import com.nivas.service.ITransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {

    public static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private ITransactionService transactionService;

    @Autowired
    private JmsTemplate jmsTemplate;


    @RequestMapping(value = "/transaction", method = RequestMethod.POST)
    public ResponseEntity<String> createTransaction(@RequestBody TransactionDto transactionDto) {
        logger.info("Receiving transaction : {}", transactionDto);
        jmsTemplate.convertAndSend("TransactionQueue", transactionDto);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/transaction", method = RequestMethod.GET)
    public List<Transaction> getTransactions() {
        return transactionService.getAllTransactions();
    }

}
