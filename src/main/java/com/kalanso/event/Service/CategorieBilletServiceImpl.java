package com.kalanso.event.Service;

import com.kalanso.event.Model.CategorieBillet;
import com.kalanso.event.Repository.CategorieBilletRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class CategorieBilletServiceImpl implements CategorieBilletService{
  @Autowired
    private CategorieBilletRepo categorieBilletRepo;

    @Override
    public CategorieBillet Ajout(CategorieBillet categorieBillet) {
        return categorieBilletRepo.save(categorieBillet);
    }

    @Override
    public List<CategorieBillet> Afficher() {
        return categorieBilletRepo.findAll();
    }

    @Override
    public CategorieBillet MAJ(Long Id, CategorieBillet categorieBillet) {
        return categorieBilletRepo.findById(Id)
                .map(p->{
                    p.setCategory(categorieBillet.getCategory());
                    return categorieBilletRepo.save(p);
                }).orElseThrow(()-> new RuntimeException("Erreur lors de la mise à jour du categorie billet"));
    }

    @Override
    public String Sup(Long Id) {
        categorieBilletRepo.deleteById(Id);
        return "CATEGORIE BILLET SUPPRIMER";
    }
}
