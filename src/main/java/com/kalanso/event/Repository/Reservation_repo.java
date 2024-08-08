package com.kalanso.event.Repository;

import com.kalanso.event.Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;


public interface Reservation_repo extends JpaRepository<Reservation,Long> {
}
