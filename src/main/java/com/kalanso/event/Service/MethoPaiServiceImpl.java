package com.kalanso.event.Service;

import com.kalanso.event.Model.MethodePaiement;
import com.kalanso.event.Repository.MethoPaieRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MethoPaiServiceImpl implements MethoPaiService{

    MethoPaieRepo methoPaieRepo;

    @Override
    public MethodePaiement Ajouter(MethodePaiement methodePaiement) {
        return methoPaieRepo.save(methodePaiement);
    }

    @Override
    public List<MethodePaiement> AfficherMethode() {
        return methoPaieRepo.findAll();
    }

    @Override
    public MethodePaiement ModifierMethode(Long id, MethodePaiement methodePaiement) {
        return methoPaieRepo.findById(id)
                .map(p->{
                    p.setMethodepaie(methodePaiement.getMethodepaie());
                    return methoPaieRepo.save(p);
                }).orElseThrow(()-> new RuntimeException("Erreur de la mise à jour du méthode de paiement"));
    }

    @Override
    public String SupMethodePaiement(Long id) {
        methoPaieRepo.deleteById(id);
        return "Methode supprimer avec succès";
    }
}
