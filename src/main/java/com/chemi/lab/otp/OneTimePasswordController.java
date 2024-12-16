package com.chemi.lab.otp;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

    @PostMapping
    public ResponseEntity<String> verifyOTP(@RequestParam("otp") String otp,
                                    @RequestParam("channel") String channel
    ) {
        oneTimePasswordService.verifyOTP(otp,channel);
        return new ResponseEntity<>("OTP verified", HttpStatus.OK);
    }

}
