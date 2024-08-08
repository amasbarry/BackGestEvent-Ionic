package com.kalanso.event.Service;

import com.kalanso.event.Model.MethodePaiement;

import java.util.List;

public interface MethoPaiService {

    MethodePaiement Ajouter(MethodePaiement methodePaiement);

    List<MethodePaiement> AfficherMethode();

    MethodePaiement ModifierMethode(Long id, MethodePaiement methodePaiement);

    String SupMethodePaiement(Long id);

}
