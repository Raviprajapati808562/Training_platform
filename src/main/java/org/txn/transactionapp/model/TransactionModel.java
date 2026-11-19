package org.txn.transactionapp.model;

import io.github.jpaadmin.annotations.AdminField;
import io.github.jpaadmin.annotations.AdminResource;
import jakarta.persistence.*;

@Entity
@Table(name = "transaction_table")
@AdminResource(group = "Transactions",
        displayName = "Transaction",
        exportable = true)
public class TransactionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reference_number;
    private String account_number;
    private String amount;
    @AdminField(searchable = true,sortable = true,filterable = true)
    private String direction;
    @AdminField(searchable = true,sortable = true,filterable = true)
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference_number() {
        return reference_number;
    }

    public void setReference_number(String reference_number) {
        this.reference_number = reference_number;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
