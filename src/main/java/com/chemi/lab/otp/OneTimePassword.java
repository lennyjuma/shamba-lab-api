package com.chemi.lab.otp;


import com.chemi.lab.utils.PriKey;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class OneTimePassword extends PriKey {

    @NonNull
    private Integer oneTimePasswordCode;
    @NonNull
    private Date sms_otp_expires;
    private Date email_otp_expires;
    private String user_id;

}


