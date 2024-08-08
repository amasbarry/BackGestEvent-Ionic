package com.kalanso.event.Service;

import com.kalanso.event.Model.StatutReservation;

import java.util.List;

public interface SatutReservationService {

    StatutReservation Ajout(StatutReservation statutReservation);

    List<StatutReservation> AfficherListeReservation();

    StatutReservation MAJ(Long Id, StatutReservation statutReservation);

    String SupStatutreservation(Long Id);
}
