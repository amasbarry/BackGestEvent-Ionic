package com.kalanso.event.Service;

import com.kalanso.event.Model.Presta;
import com.kalanso.event.Model.Utilisateur;
import com.kalanso.event.Repository.PrestateurRepository;
import jdk.jshell.execution.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestateurService implements PrestaService{
   @Autowired
   private PrestateurRepository prestateurRepository;
    @Autowired
    private ContexHolder contexHolder;

    @Override
    public Presta AjouterPresta(Presta presta) {
        Utilisateur utilisateur = contexHolder.utilisateur();
        presta.setUtilisateur(utilisateur);
        return prestateurRepository.save(presta) ;
    }

    @Override
    public List<Presta> ListPrestateurs() {
        return prestateurRepository.findAll();
    }

    @Override
    public String SupPrestateur(Long id) {
        prestateurRepository.deleteById(id);
        return "Prestateur Supprime avec succes";
    }

    @Override
    public Presta ModifPresta(Long id, Presta presta) {
        return prestateurRepository.findById(id)
                .map(p->{
                    p.setNom_presta(presta.getNom_presta());
                    p.setEmail(presta.getEmail());
                    p.setProfile(presta.getProfile());
                    p.setTel(presta.getTel());
                    return prestateurRepository.save(p);
                }).orElseThrow(()-> new RuntimeException("Erreur lors de la mise à jour"));

    }
}

