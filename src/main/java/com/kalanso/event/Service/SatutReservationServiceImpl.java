package com.kalanso.event.Service;

import com.kalanso.event.Model.StatutReservation;
import com.kalanso.event.Repository.StatutReservationRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class SatutReservationServiceImpl implements SatutReservationService{
    @Autowired
    private StatutReservationRepo statutReservationRepo;

    @Override
    public StatutReservation Ajout(StatutReservation statutReservation) {
        return statutReservationRepo.save(statutReservation) ;
    }

    @Override
    public List<StatutReservation> AfficherListeReservation() {
        return statutReservationRepo.findAll();
    }

    @Override
    public StatutReservation MAJ(Long Id, StatutReservation statutReservation) {
        return statutReservationRepo.findById(Id)
                .map(p->{
                    p.setStatut(statutReservation.getStatut());
                    return statutReservationRepo.save(p);
                }).orElseThrow(()->new RuntimeException("Erreur lors de la mise à jour du statut reservation"));
    }

    @Override
    public String SupStatutreservation(Long Id) {
        statutReservationRepo.deleteById(Id);
        return "Statut reservation supprimer";
    }
}
