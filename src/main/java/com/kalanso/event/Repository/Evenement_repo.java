package com.kalanso.event.Repository;

import com.kalanso.event.Model.Evenement;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Evenement_repo extends JpaRepository<Evenement, Integer> {
}
