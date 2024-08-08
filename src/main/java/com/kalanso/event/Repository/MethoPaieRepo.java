package com.kalanso.event.Repository;

import com.kalanso.event.Model.MethodePaiement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MethoPaieRepo extends JpaRepository<MethodePaiement, Long> {
    MethodePaiement findByMethodepaie(String methodepaie);
}
