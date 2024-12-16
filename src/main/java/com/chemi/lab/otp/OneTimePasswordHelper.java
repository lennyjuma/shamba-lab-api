package com.chemi.lab.otp;

import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.function.Supplier;

@Service
public class OneTimePasswordHelper {
    private final static Integer LENGTH = 6;

    public static Supplier<Integer> createRandomOneTimePassword() {
        return () -> {
            Random random = new Random();
            StringBuilder oneTimePassword = new StringBuilder();
            for (int i = 0; i < LENGTH; i++) {
                int randomNumber = random.nextInt(10);
                oneTimePassword.append(randomNumber);
            }
            return Integer.parseInt(oneTimePassword.toString().trim());
        };
    }

//    public static void main(String[] args) {
//        Supplier<Integer> oneTimePassword = createRandomOneTimePassword();
//        System.out.println(oneTimePassword.get());
//    }
}
