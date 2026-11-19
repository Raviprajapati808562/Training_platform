/*

package org.txn.transactionapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SmsService {

    @Value("${twilio.account-sid}")
    private String accountSid;

    @Value("${twilio.auth-token}")
    private String authToken;

    @Value("${twilio.from-number}")
    private String fromNumber;

    public void sendOtp(String mobileNumber, String otp) {

        try {
            // Initialize Twilio
            Twilio.init(accountSid, authToken);

            // Mobile number must be in E.164 format
            String toNumber = mobileNumber.startsWith("+")
                    ? mobileNumber
                    : "+91" + mobileNumber;

            // Prepare OTP message
//            String messageBody =
//                    "Your verification OTP is " + otp
//                            + ". It is valid for 5 minutes. Do not share it.";
            String messageBody =
                    "sms_order_confirmation"+otp;
            // Send SMS
            Message message = Message.creator(
                    new PhoneNumber(toNumber),
                    new PhoneNumber(fromNumber),
                    messageBody
            ).create();

            System.out.println(
                    "SMS submitted successfully. SID: "
                            + message.getSid()
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to send OTP through Twilio: "
                            + e.getMessage(), e
            );
        }
    }
}
*/
