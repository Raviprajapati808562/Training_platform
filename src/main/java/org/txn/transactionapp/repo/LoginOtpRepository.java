package org.txn.transactionapp.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.txn.transactionapp.model.LoginOtp;


public interface LoginOtpRepository
        extends JpaRepository<LoginOtp, Long> {

    Optional<LoginOtp> findByChallengeId(String challengeId);
}