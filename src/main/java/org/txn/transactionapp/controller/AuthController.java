
package org.txn.transactionapp.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import org.txn.transactionapp.dto.LoginRequest;
import org.txn.transactionapp.dto.VerifyOtpRequest;
import org.txn.transactionapp.model.User;
import org.txn.transactionapp.service.AuthService;
import org.txn.transactionapp.service.OtpService;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthService authService;
    private final OtpService otpService;
   // private SmsService smsService;

    public AuthController(
            AuthService authService,
            OtpService otpService) {

        this.authService = authService;
        this.otpService = otpService;
    }

    // STEP 1: Validate email/password and generate OTP
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request) {

        // Validate email and password
        User user = authService.validateCredentials(request);

        // Generate and save OTP
//        String challengeId = otpService.createOtp(
//                user.getUserId(),
//                user.getMobileNumber()
//        );

        String challengeId = otpService.createOtp(user.getUserId(),"");

        // Mask registered mobile number
        String mobile = user.getMobileNumber();
        String maskedMobile = "******" +
                mobile.substring(Math.max(0, mobile.length() - 4));

        return ResponseEntity.ok(
                Map.of(
                        "message", "OTP generated successfully",
                        "challengeId", challengeId,
                        "maskedMobile", maskedMobile
                )
        );
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(
            @RequestBody VerifyOtpRequest request) {

        boolean verified = otpService.verifyOtp(
                request.getChallengeId(),
                request.getOtp()
        );

        if (verified) {
            return ResponseEntity.ok(
                    Map.of("message", "OTP verified successfully")
            );
        }

        return ResponseEntity.badRequest().body(
                Map.of("message", "OTP verification failed")
        );
    }
}
