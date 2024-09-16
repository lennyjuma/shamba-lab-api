package com.chemi.lab.mkulima.farm;

import com.chemi.lab.generics.GenericRepository;

import java.util.List;
import java.util.Optional;

public interface ShambaRepo extends GenericRepository<Shamba> {
    Optional<List<Shamba>> findShambasByCustomer_IdOrderByCreatedAtDesc(String user_id);
    Optional<Shamba> findShambaByStmName(String stm_name);
    Optional<Shamba> findShambaByNameAndCustomer_PhoneNumber(String p_number,String name);
}
