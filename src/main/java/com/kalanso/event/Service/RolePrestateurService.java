package com.kalanso.event.Service;

import com.kalanso.event.Model.RolePrestateur;

import java.util.List;

public interface RolePrestateurService {

    RolePrestateur Ajout(RolePrestateur rolePrestateur);

    List<RolePrestateur> Afficher();

    RolePrestateur MAJ(Long id, RolePrestateur rolePrestateur);

    String sup(Long id);
}
