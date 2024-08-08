package com.kalanso.event.Repository;

import com.kalanso.event.Model.StatutEnvoi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatutEnvoiRepo extends JpaRepository<StatutEnvoi, Long> {
    StatutEnvoi findByStatut(String statut);
}
