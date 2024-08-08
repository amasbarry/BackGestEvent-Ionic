package com.kalanso.event.Service;

import com.kalanso.event.Model.RolePrestateur;
import com.kalanso.event.Repository.RolePrestateurRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class RolePrestateurServiceImpl implements RolePrestateurService{
        @Autowired
        private RolePrestateurRepo rolePrestateurRepo;

    @Override
    public RolePrestateur Ajout(RolePrestateur rolePrestateur) {
        return rolePrestateurRepo.save(rolePrestateur);
    }

    @Override
    public List<RolePrestateur> Afficher() {
        return rolePrestateurRepo.findAll();
    }

    @Override
    public RolePrestateur MAJ(Long id, RolePrestateur rolePrestateur) {
        return rolePrestateurRepo.findById(id)
                .map(p->{
                    p.setRole(rolePrestateur.getRole());
                    return rolePrestateurRepo.save(p);
                }).orElseThrow(()->new RuntimeException("Erreur lors de la mise à jour du role du prestateur"));
    }

    @Override
    public String sup(Long id) {
        rolePrestateurRepo.deleteById(id);
        return "Role du prestateur supprimer";
    }
}
