package com.chemi.lab.openai.repo;

import com.chemi.lab.openai.models.response.GPTResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GPTResponseRepo extends JpaRepository<GPTResponse, String> {
    Optional<GPTResponse> findBySoilReadingId(String soilReadingId);
}
