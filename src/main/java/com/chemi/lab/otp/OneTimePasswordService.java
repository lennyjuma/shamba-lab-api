package com.chemi.lab.otp;

import com.chemi.lab.auth.config.SecurityContextMapper;
import com.chemi.lab.exceptions.ApiResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;


@Service
@RequiredArgsConstructor
@Slf4j
public class OneTimePasswordService {
    private final OneTimePasswordHelper otpHelper;
    private final OneTimePasswordRepo otpRepo;
    private final KafkaTemplate<String,String> kafkaTemplate;
    private final SecurityContextMapper securityContextMapper;

    public OneTimePassword generateOneTimePassword(String userID) {
        OneTimePassword otp = new OneTimePassword();
        otp.setOneTimePasswordCode(otpHelper.createRandomOneTimePassword().get());
        long expiryInterval_sms = 1000L *  60 * 5 ; // 5 minutes
        long expiryInterval_email =  1000L *  60 * 60 * 24 ; // 5 minutes
        Date date_email = new Date(System.currentTimeMillis() + expiryInterval_email);
        Date date_sms = new Date(System.currentTimeMillis() + expiryInterval_sms);
        otp.setSmsOtpExpires(date_sms);
        otp.setEmailOtpExpires(date_email);
        otp.setUserId(userID);
        otp.setEmailOTPVerified(Boolean.FALSE);
        otp.setSmsOTPVerified(Boolean.FALSE);
        kafkaTemplate.send("sms-otp", otp.getOneTimePasswordCode().toString()); // send sms otp
        return otpRepo.save(otp);
    }


    public void verifySMSOTP(String otp_code) {
        String user_id = securityContextMapper.getLoggedInCustomer().getId();

        OneTimePassword otp = otpRepo.findOneTimePasswordByUserIdAndOneTimePasswordCode(user_id, Integer.valueOf(otp_code)).orElseThrow(
                () -> new ApiResourceNotFoundException("OTP code incorrect.")
        );
        Date smsOtpExpires = otp.getSmsOtpExpires();
        long lapsed_sms_time =  Math.abs(System.currentTimeMillis() -  smsOtpExpires.getTime());
        long elapsed_minutes = lapsed_sms_time / (1000L * 60 );
        if(elapsed_minutes > 5L){ //this is in minutes
            throw new ApiResourceNotFoundException("SMS OTP code expired.");
        }
        if(otp.getSmsOTPVerified()){ // check if already verified
            throw new ApiResourceNotFoundException("Phone number already verified.");
        }
        otp.setSmsOTPVerified(Boolean.TRUE);
        otpRepo.save(otp);

    }
    public void verifyEmailOTP(String otp_id) {
        OneTimePassword otp = otpRepo.findById(otp_id).orElseThrow(
                () -> new ApiResourceNotFoundException("OTP code not registered.")
        );
        Date emailOtpExpires = otp.getEmailOtpExpires();
        long lapsed_email_time =  Math.abs(System.currentTimeMillis() -  emailOtpExpires.getTime());
        long elapsed_hours = lapsed_email_time / (1000L * 60 * 60);
        if(elapsed_hours > 24L){
            throw new ApiResourceNotFoundException("email token expired.");
        }
        if(otp.getEmailOTPVerified() == Boolean.TRUE){
            throw new ApiResourceNotFoundException("email already verified.");
        }
        otp.setEmailOTPVerified(Boolean.TRUE);
        otpRepo.save(otp);

    }

    public void generateOTP() {
        String user_id = securityContextMapper.getLoggedInCustomer().getId();
        OneTimePassword timePassword = generateOneTimePassword(user_id);
        kafkaTemplate.send("sms-otp", timePassword.getOneTimePasswordCode().toString());


    }
}
