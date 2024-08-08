package com.kalanso.event.Service;

import com.kalanso.event.Model.Equipement;
import com.kalanso.event.Repository.EquiRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EquiServiceImpl implements EquiService{

    EquiRepository equiRepository;

    @Override
    public Equipement AjouterEqui(Equipement equipement) {
        return equiRepository.save(equipement);
    }

    @Override
    public List<Equipement> AfficherEqui() {
        return equiRepository.findAll();
    }

    @Override
    public Equipement ModifierEqui(Integer id, Equipement equipement) {
        return equiRepository.findById(id)
                .map(p->{
                    p.setNom(equipement.getNom());
                    return equiRepository.save(p);
                }).orElseThrow(()->new RuntimeException("Erreur de jour de l'equipement"));
    }

    @Override
    public String SupEqui(Integer id) {
        equiRepository.deleteById(id);
        return "Equipement supprimé";
    }
}
