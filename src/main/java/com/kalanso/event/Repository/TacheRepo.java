package com.kalanso.event.Repository;

import com.kalanso.event.Model.Taches;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TacheRepo extends JpaRepository<Taches, Integer> {
}
