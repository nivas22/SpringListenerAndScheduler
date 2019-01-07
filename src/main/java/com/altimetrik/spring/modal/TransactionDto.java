package com.altimetrik.spring.modal;

import java.io.Serializable;

public class TransactionDto implements Serializable {

    private String cardNumber;
    private Long amount;
    private String status;
    private String date;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TransactionDto{" +
                "cardNumber='" + cardNumber + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
