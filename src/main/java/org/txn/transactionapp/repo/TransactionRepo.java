package org.txn.transactionapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.txn.transactionapp.model.TransactionModel;

public interface TransactionRepo  extends JpaRepository<TransactionModel,Long> {
}
