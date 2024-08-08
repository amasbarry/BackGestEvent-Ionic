package com.kalanso.event.Service;

import com.kalanso.event.Exception.ResourceNotFoundException;
import com.kalanso.event.Model.Lieu;
import com.kalanso.event.Repository.Lieu_repo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
@AllArgsConstructor
public class Lieu_service {

    private Lieu_repo lieu_repo;


    // Ajouter un lieu
    public Lieu ajouterLieu(Lieu lieu) {
        return lieu_repo.save(lieu);
    }

    public List<Lieu> getAllLieu() {
        return lieu_repo.findAll();
    }

    public Lieu getLieuById(Integer id) {
        return lieu_repo.findById(id).orElse(null);
    }

    public void deleteLieu(Integer id) {
        lieu_repo.deleteById(id);
    }

    @Transactional
    public Lieu updateLieu(Integer id, Lieu lieuDetails) {
        Lieu lieu = lieu_repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lieu not found with id " + id));

        lieu.setNom(lieuDetails.getNom());
        lieu.setAdresse(lieuDetails.getAdresse());
        lieu.setSalle(lieuDetails.getSalle());
        lieu.setCapacite(lieuDetails.getCapacite());

        return lieu_repo.save(lieu);
    }
}
