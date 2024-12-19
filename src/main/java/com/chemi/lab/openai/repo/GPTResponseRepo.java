package com.chemi.lab.openai.repo;

import com.chemi.lab.openai.models.response.GPTResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GPTResponseRepo extends JpaRepository<GPTResponse, String> {
}
