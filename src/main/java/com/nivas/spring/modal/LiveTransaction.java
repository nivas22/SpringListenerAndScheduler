package com.nivas.spring.modal;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "currenttransaction")
public class LiveTransaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cardNumber")
    @NotNull(message = "*Please provide your cardNumber")
    private String cardNumber;

    @Column(name = "amount")
    @NotNull(message = "*Please provide your amount")
    private Long amount;

    @Column(name = "status")
    @NotNull(message = "*Please provide your status")
    private String status;

    @Column(name = "date")
    @NotNull(message = "*Please provide your date")
    private Date date;

    @Column(name = "created", nullable = false)
    private Long created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LiveTransaction)) return false;
        LiveTransaction that = (LiveTransaction) o;
        return Objects.equals(cardNumber, that.cardNumber) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(status, that.status) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, amount, status, date);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", date=" + date +
                ", created=" + created +
                '}';
    }
}
