package com.chemi.lab.otp;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/otp")
@RequiredArgsConstructor
public class OneTimePasswordController {

    private final OneTimePasswordService oneTimePasswordService;

    @PostMapping("verify/email")
    public ResponseEntity<String> verifyEmailOTP(@RequestParam("otp_id") String otp_id
    ) {
        oneTimePasswordService.verifyEmailOTP(otp_id);
        return new ResponseEntity<>("OTP verified", HttpStatus.OK);
    }
    @PostMapping("verify/sms")
    public ResponseEntity<String> verifySMSOTP(@RequestParam("otp") String otp
    ) {
        oneTimePasswordService.verifySMSOTP(otp);
        return new ResponseEntity<>("OTP verified", HttpStatus.OK);
    }

    @PostMapping("generate")
    public ResponseEntity<String> RegenerateOTP() {
        oneTimePasswordService.generateOTP();
        return new ResponseEntity<>("OTP Generated", HttpStatus.OK);
    }

}
