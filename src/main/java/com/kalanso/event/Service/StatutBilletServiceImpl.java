package com.kalanso.event.Service;

import com.kalanso.event.Model.StatutBillet;
import com.kalanso.event.Repository.StatutBilletRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class StatutBilletServiceImpl implements StatutBilletService{

    private StatutBilletRepo statutBilletRepo;

    @Override
    public StatutBillet Ajout(StatutBillet statutBillet) {
        return statutBilletRepo.save(statutBillet);
    }

    @Override
    public List<StatutBillet> Afficher() {
        return statutBilletRepo.findAll();
    }

    @Override
    public StatutBillet MAJ(Long Id, StatutBillet statutBillet) {
        return statutBilletRepo.findById(Id)
                .map(p->{
                    p.setStatut(statutBillet.getStatut());
                    return statutBilletRepo.save(p);
                }).orElseThrow(()->new RuntimeException("Erreur lors de la modification de l'etat du billet"));
    }

    @Override
    public String Sup(Long Id) {
        statutBilletRepo.deleteById(Id);
        return "L'etat du billet supprimer";
    }
}
