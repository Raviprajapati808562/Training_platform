
package org.txn.transactionapp.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.txn.transactionapp.model.LoginOtp;
import org.txn.transactionapp.repo.LoginOtpRepository;


@Service
public class OtpService {
    private final LoginOtpRepository otpRepository;

    private final SecureRandom random = new SecureRandom();

    public OtpService(LoginOtpRepository otpRepository) {
        this.otpRepository = otpRepository;
       // this.smsService = smsService;
    }
    //private final SmsService smsService;

    public String generateOtp() {

        int number = 100000 + random.nextInt(900000);

        return String.valueOf(number);
    }

    public String createOtp(String userId,String mobileNumber) {

        String otp = generateOtp();

        String challengeId = UUID.randomUUID().toString();

        LoginOtp loginOtp = new LoginOtp();

        loginOtp.setUserId(userId);
        loginOtp.setChallengeId(challengeId);
        loginOtp.setOtp(otp);
        loginOtp.setExpiryTime(LocalDateTime.now().plusMinutes(5));
        loginOtp.setVerified(false);
        loginOtp.setAttempts(0);

        otpRepository.save(loginOtp);

        // Development testing only: do not log OTP in production
        System.out.println("Generated OTP: " + otp);

        // Send OTP using Twilio
//        smsService.sendOtp(mobileNumber, otp);
        return challengeId;
    }
    public boolean verifyOtp(String challengeId, String enteredOtp) {

        LoginOtp loginOtp = otpRepository
                .findByChallengeId(challengeId)
                .orElseThrow(() ->
                        new RuntimeException("Invalid challenge ID"));

        if (loginOtp.isVerified()) {
            throw new RuntimeException("OTP already used");
        }

        if (loginOtp.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("OTP expired");
        }

        if (loginOtp.getAttempts() >= 5) {
            throw new RuntimeException("Maximum OTP attempts exceeded");
        }

        loginOtp.setAttempts(loginOtp.getAttempts() + 1);

        if (!loginOtp.getOtp().equals(enteredOtp)) {
            otpRepository.save(loginOtp);
            throw new RuntimeException("Invalid OTP");
        }

        loginOtp.setVerified(true);
        otpRepository.save(loginOtp);

        return true;
    }
}
