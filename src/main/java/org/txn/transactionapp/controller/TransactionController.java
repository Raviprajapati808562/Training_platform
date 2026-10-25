package org.txn.transactionapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.txn.transactionapp.model.TransactionModel;
import org.txn.transactionapp.service.Transactionservice;

import java.util.List;

@RestController
@RequestMapping("v1/transaction")
@CrossOrigin(origins = "http://localhost:4200")
public class TransactionController {
    @Autowired
    private Transactionservice transactionservice;

    @PostMapping("saveTransactions")
    public ResponseEntity<String> pushTransaction(@RequestBody TransactionModel transactionModel) {
        String message = transactionservice.saveTransactionData(transactionModel);
        System.out.println(message);
        return ResponseEntity.ok(message);
    }


    @GetMapping("/getAllTransactions")
    public ResponseEntity< List<TransactionModel>> getAllTransaction() {
        List<TransactionModel> transactions  = transactionservice.getTransactionData();
        return ResponseEntity.accepted().body(transactions);
    }

}
