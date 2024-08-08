package com.kalanso.event.Service;

import com.kalanso.event.Model.CategorieBillet;

import java.util.List;

public interface CategorieBilletService {

    CategorieBillet Ajout(CategorieBillet categorieBillet);

    List<CategorieBillet> Afficher();

    CategorieBillet MAJ(Long Id, CategorieBillet categorieBillet);

    String Sup(Long Id);
}
