package com.kalanso.event.Service;

import com.kalanso.event.Model.Taches;
import com.kalanso.event.Repository.TacheRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class TachesServiceImpl implements TacheService{
  @Autowired
    private TacheRepo tacheRepo;
    @Override
    public Taches CreerTache(Taches tache) {
        return tacheRepo.save(tache);
    }

    @Override
    public List<Taches> TACHES_LIST() {
        return tacheRepo.findAll();
    }

    @Override
    public Taches AfficheTache(Integer id) {
        return null;
    }

    @Override
    public Taches updateTaches(Integer id, Taches taches) {
        return tacheRepo.findById(id)
                .map(taches1->{
                    taches1.setTitle(taches.getTitle());
                    taches1.setPriority(taches.getPriority());
                    return tacheRepo.save(taches1);
                }).orElseThrow(()-> new RuntimeException("Erreur lors de la mise à jour"));
    }

    @Override
    public String deleteTaches(Integer id) {
        tacheRepo.deleteById(id);
        return "Taches Supprimer avec succes";
    }
}
