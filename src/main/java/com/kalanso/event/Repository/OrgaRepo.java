package com.kalanso.event.Repository;

import com.kalanso.event.Model.Organisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrgaRepo extends JpaRepository<Organisateur, Integer> {
}
