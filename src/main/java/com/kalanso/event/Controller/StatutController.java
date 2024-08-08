package com.kalanso.event.Controller;


import com.kalanso.event.Model.StatutBillet;
import com.kalanso.event.Model.StatutEnvoi;
import com.kalanso.event.Model.StatutReservation;
import com.kalanso.event.Service.SatutReservationService;
import com.kalanso.event.Service.StatutBilletService;
import com.kalanso.event.Service.StatutEnvoiService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/GestEven/Statut")
@AllArgsConstructor
public class StatutController {

    StatutBilletService statutBilletService;
    StatutEnvoiService statutEnvoiService;
    SatutReservationService satutReservationService;

    @PostMapping("/AjouterStatutBillet")
    public StatutBillet Ajout(@RequestBody StatutBillet statutBillet){
        return statutBilletService.Ajout(statutBillet);
    }

    @GetMapping("/AfficherStatutBillet")
    public List<StatutBillet> Afficher(){
        return statutBilletService.Afficher();
    }

    @PutMapping("/ModidierStatutBillet/{id}")
    public StatutBillet MAJ(@PathVariable Long id, @RequestBody StatutBillet statutBillet){
        return statutBilletService.MAJ(id, statutBillet);
    }

    @DeleteMapping("/SupprimerStatutBillet/{id}")
    public String Sup(@PathVariable Long id){
        return statutBilletService.Sup(id);
    }


    @PostMapping("/AjouterStatutEnvoi")
    public StatutEnvoi Ajout(@RequestBody StatutEnvoi statutEnvoi){
        return statutEnvoiService.Ajout(statutEnvoi);
    }


    @GetMapping("/AfficherStatutEnvoi")
    public List<StatutEnvoi> Affiche(){
        return statutEnvoiService.Affiche();
    }

    @PutMapping("/ModifierStatutEnvoi/{id}")
    public StatutEnvoi MAJ(@PathVariable Long id, @RequestBody StatutEnvoi statutEnvoi){
        return statutEnvoiService.MAJ(id, statutEnvoi);
    }


    @DeleteMapping("/SupprimerStatutEnvoi/{id}")
    public String Supp(@PathVariable Long id){
        return statutEnvoiService.Supp(id);
    }

    @PostMapping("/AjouterStatutReservation")
    public StatutReservation Ajout(@RequestBody StatutReservation statutReservation){
        return satutReservationService.Ajout(statutReservation);
    }


    @GetMapping("/AfficherStatutReservation")
    public List<StatutReservation> AfficherListeReservation(){
        return satutReservationService.AfficherListeReservation();
    }

    @PutMapping("/ModifierStatutReservation/{id}")
    public StatutReservation MAJ(@PathVariable Long id, @RequestBody StatutReservation statutReservation){
        return satutReservationService.MAJ(id, statutReservation);
    }

    @DeleteMapping("/SupprimerStatutReservation/{id}")
    public String SupStatutreservation(@PathVariable Long id){
        return satutReservationService.SupStatutreservation(id);
    }
}
