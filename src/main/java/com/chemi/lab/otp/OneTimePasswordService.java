package com.chemi.lab.otp;

import com.chemi.lab.auth.config.SecurityContextMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class OneTimePasswordService {
    private final OneTimePasswordHelper otpHelper;
    private final OneTimePasswordRepo otpRepo;
    private final SecurityContextMapper securityContextMapper;

    public OneTimePassword returnOneTimePassword() {
        OneTimePassword oneTimePassword = new OneTimePassword();

        oneTimePassword.setOneTimePasswordCode(OneTimePasswordHelper.createRandomOneTimePassword().get());
        long expiryInterval_sms = 1000L *  60 * 5 ; // 5 minutes
        long expiryInterval_email =  1000L *  60 * 60 * 24 ; // 5 minutes
        Date date_email = new Date(System.currentTimeMillis() + expiryInterval_email);
        Date date_sms = new Date(System.currentTimeMillis() + expiryInterval_sms);
        oneTimePassword.setSms_otp_expires(date_sms);
        oneTimePassword.setEmail_otp_expires(date_email);
        String user_id = securityContextMapper.getLoggedInCustomer().getId();
        log.info("user_id: {}", user_id);
        oneTimePassword.setUser_id(user_id);

        return otpRepo.save(oneTimePassword);
    }


}
