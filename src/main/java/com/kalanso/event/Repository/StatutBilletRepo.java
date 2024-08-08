package com.kalanso.event.Repository;



import com.kalanso.event.Model.StatutBillet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatutBilletRepo extends JpaRepository<StatutBillet, Long> {
    StatutBillet findByStatut(String statut);
}
