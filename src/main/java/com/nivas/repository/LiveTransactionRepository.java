package com.nivas.repository;

import com.nivas.modal.LiveTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface LiveTransactionRepository extends JpaRepository<LiveTransaction, Long> {

    @Query("SELECT t FROM LiveTransaction t WHERE t.created > :pickedTime")
    List<LiveTransaction> findTransactions(@Param("pickedTime") Long pickedTime);


    @Query("SELECT t FROM LiveTransaction t WHERE t.amount = :amount and t.cardNumber = :cardNumber and t.date = :date and t.status = :status")
    LiveTransaction findTransaction(@Param("amount") Long amount, @Param("cardNumber") String cardNumber, @Param("date") Date date, @Param("status") String status);
}
