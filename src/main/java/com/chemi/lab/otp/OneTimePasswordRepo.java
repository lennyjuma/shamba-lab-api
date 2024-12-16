package com.chemi.lab.otp;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OneTimePasswordRepo extends JpaRepository<OneTimePassword, String> {
}
