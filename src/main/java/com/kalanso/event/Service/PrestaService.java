package com.kalanso.event.Service;

import com.kalanso.event.Model.Presta;

import java.util.List;

public interface PrestaService {
    Presta AjouterPresta(Presta presta);
    List<Presta> ListPrestateurs();
    String SupPrestateur(Long id);
    Presta ModifPresta(Long id,Presta presta);
}
