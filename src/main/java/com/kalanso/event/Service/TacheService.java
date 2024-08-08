package com.kalanso.event.Service;

import com.kalanso.event.Model.Taches;
import com.kalanso.event.Model.Utilisateur;

import java.util.List;

public interface TacheService {
    Taches CreerTache(Taches tache);
    List<Taches> TACHES_LIST();
    Taches AfficheTache(Integer id);
    Taches updateTaches(Integer id, Taches taches);
    String deleteTaches(Integer id);}
