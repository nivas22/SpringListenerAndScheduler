package com.nivas.spring.repository;

import com.nivas.spring.modal.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.created > :pickedTime")
    List<Transaction> findTransactions(@Param("pickedTime") Long pickedTime);
}
