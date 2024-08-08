package com.kalanso.event.Controller;

import com.kalanso.event.Model.Equipement;
import com.kalanso.event.Service.EquipementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/gestEvent/equipements")
public class EquipementController {

    @Autowired
    private EquipementService equipementService;

    @GetMapping("/listEquipements")
    public List<Equipement> getAllEquipements() {
        return equipementService.findAll();
    }

    @GetMapping("unEquipement/{id}")
    public ResponseEntity<Equipement> getEquipementById(@PathVariable Long id) {
        Optional<Equipement> equipement = equipementService.findById(id);
        return equipement.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/ajoutEquipement")
    public Equipement createEquipement(@RequestBody Equipement equipement) {
        return equipementService.save(equipement);
    }

    @PutMapping("/modifEquipement/{id}")
    public ResponseEntity<Equipement> updateEquipement(@PathVariable Long id, @RequestBody Equipement equipementDetails) {
        Optional<Equipement> equipement = equipementService.findById(id);

        if (equipement.isPresent()) {
            Equipement equipementToUpdate = equipement.get();
            equipementToUpdate.setNom(equipementDetails.getNom());
            equipementToUpdate.setDescription(equipementDetails.getDescription());
            return ResponseEntity.ok(equipementService.save(equipementToUpdate));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/supEquipement/{id}")
    public ResponseEntity<Void> deleteEquipement(@PathVariable Long id) {
        Optional<Equipement> equipement = equipementService.findById(id);

        if (equipement.isPresent()) {
            equipementService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

