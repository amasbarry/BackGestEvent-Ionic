package com.kalanso.event.Service;


import com.kalanso.event.Model.RoleUser;
import com.kalanso.event.Repository.RoleUserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class Role_Service {
    private  RoleUserRepo roleUserRepo;

    // Ajouter un nouveau rôle
    public RoleUser AjouterRole(RoleUser role) {
        return roleUserRepo.save(role);
    }

    // Obtenir la liste de tous les rôles
    public List<RoleUser> ListeRole() {
        return roleUserRepo.findAll();
    }

    // Supprimer un rôle par son ID
    public String supprimer(Long id) {
        Optional<RoleUser> roleOptional = roleUserRepo.findById(id);
        if (roleOptional.isPresent()) {
            roleUserRepo.deleteById(id);
            return "Role supprimé avec succès";
        } else {
            return "Role non trouvé";
        }
    }

    // Modifier un rôle existant
    public RoleUser ModifierCategorie(Long id, RoleUser role) {
        if (roleUserRepo.existsById(id)) {
            role.setId(id);
            return roleUserRepo.save(role);
        } else {
            throw new RuntimeException("Role non trouvé avec l'ID: " + id);
        }
    }
}
