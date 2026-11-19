package org.txn.transactionapp.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.txn.transactionapp.model.User;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserId(String userId);

}