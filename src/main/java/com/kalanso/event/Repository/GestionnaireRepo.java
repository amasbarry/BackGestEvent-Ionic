package com.kalanso.event.Repository;

import com.kalanso.event.Model.Gestionnaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GestionnaireRepo extends JpaRepository<Gestionnaire, Integer> {
}
