package com.kalanso.event.Repository;

import com.kalanso.event.Model.Equipement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquiRepository extends JpaRepository<Equipement, Integer> {
}
