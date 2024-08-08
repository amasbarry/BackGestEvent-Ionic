package com.kalanso.event.Service;

import com.kalanso.event.Model.StatutBillet;
import com.kalanso.event.Model.StatutEnvoi;

import java.util.List;

public interface StatutEnvoiService {

    StatutEnvoi Ajout(StatutEnvoi statutEnvoi);

    List<StatutEnvoi> Affiche();

    StatutEnvoi MAJ(Long Id, StatutEnvoi statutEnvoi);

    String Supp(Long Id);

}
