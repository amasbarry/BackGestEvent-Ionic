package com.kalanso.event.Service;

import com.kalanso.event.Model.Evenement;

import java.util.List;

public interface Evenement_service{

    //Methode pour ajouter un evenement
    String Ajout(Evenement evenement, Integer id);

    //Methode pour afficher un evenement
    List<Evenement> Afficher();

    //Methode pour supprimer un evenement
    String Delete(Integer id);

    //Methode pour mettre à jour un evenement
    Evenement update(Integer id, Evenement evenement);

}
