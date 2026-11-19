package org.txn.transactionapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "org.txn.transactionapp.model")
public class TransactionAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionAppApplication.class, args);
    }

}
