package com.kalanso.event.Service;

import com.kalanso.event.Model.StatutEnvoi;
import com.kalanso.event.Repository.StatutEnvoiRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StatutEnvoiServiceImpl implements StatutEnvoiService{
    private StatutEnvoiRepo statutEnvoiRepo;

    @Override
    public StatutEnvoi Ajout(StatutEnvoi statutEnvoi) {
        return statutEnvoiRepo.save(statutEnvoi);
    }

    @Override
    public List<StatutEnvoi> Affiche() {
        return statutEnvoiRepo.findAll();
    }

    @Override
    public StatutEnvoi MAJ(Long Id, StatutEnvoi statutEnvoi) {
        return statutEnvoiRepo.findById(Id)
                .map(p->{
                    p.setStatut(statutEnvoi.getStatut());
                    return statutEnvoiRepo.save(p);
                }).orElseThrow(()->new RuntimeException("Erreur lors de la mise à jour du statut d'envoi"));
    }

    @Override
    public String Supp(Long Id) {
        statutEnvoiRepo.deleteById(Id);
        return "Statut d'envoi supprimer";
    }
}
