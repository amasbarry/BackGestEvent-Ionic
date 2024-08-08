package com.kalanso.event.Service;

import com.kalanso.event.Model.Equipement;

import java.util.List;

public interface EquiService {
    Equipement  AjouterEqui(Equipement equipement);

    List<Equipement> AfficherEqui();

    Equipement ModifierEqui(Integer id, Equipement equipement);

    String SupEqui(Integer id);
}
