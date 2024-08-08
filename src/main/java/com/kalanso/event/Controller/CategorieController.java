package com.kalanso.event.Controller;

import com.kalanso.event.Model.CategorieBillet;
import com.kalanso.event.Model.CategorieEvent;
import com.kalanso.event.Service.CategorieBilletServiceImpl;
import com.kalanso.event.Service.CategorieEvent_service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins="*")
@RestController
@Controller
@RequestMapping("/gestEvent/categories")
@AllArgsConstructor
public class CategorieController {

    CategorieEvent_service categorieEventService;
    CategorieBilletServiceImpl categorieBilletService;

    @PostMapping("/AjouterEventCats")
    public CategorieEvent AjouterEventCat(@RequestBody CategorieEvent Cat){
        return categorieEventService.AjouterCategorie(Cat);
    }

    @GetMapping("/listeEventCat")
    public List<CategorieEvent> ListeEventcat(){
        return categorieEventService.ListeCategorie();
    }

    @DeleteMapping("/supEventCat/{id}")
    public String SupEventCat(@PathVariable Long id){
        return categorieEventService.supprimer(id);
    }

    @PutMapping("/modifEventCat/{id}")
    public CategorieEvent modifierEventCat(@PathVariable Long id, @RequestBody CategorieEvent Cat){
        return categorieEventService.ModifierCategorie(id, Cat);
    }

    @PostMapping("/AjouterCATBillet")
    public CategorieBillet Ajout(@RequestBody  CategorieBillet categorieBillet){
        return categorieBilletService.Ajout(categorieBillet);
    }

    @GetMapping("/AfficherBillet")
    public List<CategorieBillet> Afficher(){
        return categorieBilletService.Afficher();
    }

    @PutMapping("/ModifierCategorieBillet/{id}")
    public CategorieBillet MAJ(@PathVariable Long id, @RequestBody CategorieBillet categorieBillet){
        return categorieBilletService.MAJ(id, categorieBillet);
    }

    @DeleteMapping("/SupprimerCtaegorieBillet/{id}")
    public String Sup(@PathVariable Long id){
        return categorieBilletService.Sup(id);
    }

}
