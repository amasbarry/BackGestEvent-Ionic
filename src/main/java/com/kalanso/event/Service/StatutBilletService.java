package com.kalanso.event.Service;

import com.kalanso.event.Model.StatutBillet;

import java.util.List;

public interface StatutBilletService {

    StatutBillet Ajout(StatutBillet statutBillet);

    List<StatutBillet> Afficher();

    StatutBillet MAJ(Long Id, StatutBillet statutBillet);

    String Sup(Long Id);

}
