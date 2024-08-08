package com.kalanso.event.Repository;

import com.kalanso.event.Model.StatutReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatutReservationRepo extends JpaRepository<StatutReservation, Long> {
    StatutReservation findByStatut(String Statut);
}
