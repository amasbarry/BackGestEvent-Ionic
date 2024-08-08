package com.kalanso.event.Controller;

import com.kalanso.event.Model.MethodePaiement;
import com.kalanso.event.Service.MethoPaiService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/gestEvent/paie")
@AllArgsConstructor
public class MethodePaiementController {

    private MethoPaiService methoPaiService;

    @PostMapping("/AjouterMethodePaiement")
    public MethodePaiement Ajouter(@RequestBody MethodePaiement methodePaiement){
        return methoPaiService.Ajouter(methodePaiement);
    }

    @GetMapping("/AfficherMethodePaiement")
    public List<MethodePaiement> AfficherMethode(){
        return methoPaiService.AfficherMethode();
    }

    @PutMapping("/ModifierMethodePaiement/{id}")
    public MethodePaiement ModifierMethode(@PathVariable Long id,@RequestBody MethodePaiement methodePaiement){
        return methoPaiService.ModifierMethode(id, methodePaiement);
    }

    @DeleteMapping("/SupprimerMethodePaiement/{id}")
    public String SupMethodePaiement(@PathVariable Long id){
        return methoPaiService.SupMethodePaiement(id);
    }

}
