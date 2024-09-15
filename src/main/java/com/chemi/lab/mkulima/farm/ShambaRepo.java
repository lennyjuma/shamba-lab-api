package com.chemi.lab.mkulima.farm;

import com.chemi.lab.generics.GenericRepository;

import java.util.List;
import java.util.Optional;

public interface ShambaRepo extends GenericRepository<Shamba> {
    Optional<List<Shamba>> findShambasByCustomer_Id(String user_id);
    Optional<Shamba> findShambaByStmName(String stm_name);
}
