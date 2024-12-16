package com.chemi.lab.otp;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OneTimePasswordRepo extends JpaRepository<OneTimePassword, String> {
    Optional<OneTimePassword> findOneTimePasswordByUserIdAndOneTimePasswordCode(String userId, @NonNull Integer oneTimePasswordCode);
}
