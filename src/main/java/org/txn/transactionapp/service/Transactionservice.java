package org.txn.transactionapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.txn.transactionapp.model.TransactionModel;
import org.txn.transactionapp.repo.TransactionRepo;

import java.util.List;

@Service
public class Transactionservice {

    @Autowired
    private TransactionRepo transactionRepo;

    public String saveTransactionData(TransactionModel transactionModel) {
        try {
            transactionRepo.save(transactionModel);
            return "Transaction saved successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to save transaction";
        }
    }

    public List<TransactionModel> getTransactionData() {

        return transactionRepo.findAll();

    }
}
