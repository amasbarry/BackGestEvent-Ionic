package com.kalanso.event.Controller;

import com.kalanso.event.Model.Lieu;
import com.kalanso.event.Service.Lieu_service;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/gestEvent/lieu")
@AllArgsConstructor
public class LieuController {

    private Lieu_service lieu_service;

    @PostMapping("/Ajouter")
    public ResponseEntity<Lieu> ajouterLieu(@RequestBody Lieu lieu) {
        Lieu nouveauLieu = lieu_service.ajouterLieu(lieu);
        return ResponseEntity.ok(nouveauLieu);
    }

    @GetMapping("/ListeLieu")
    public List<Lieu> getAllLieux() {
        return lieu_service.getAllLieu();
    }

    @GetMapping("/Afficher/{id}")
    public ResponseEntity<Lieu> getLieuById(@PathVariable Integer id) {
        Lieu lieu = lieu_service.getLieuById(id);
        if (lieu != null) {
            return ResponseEntity.ok(lieu);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteLieu(@PathVariable Integer id) {
        lieu_service.deleteLieu(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Lieu> updateLieu(@PathVariable Integer id, @RequestBody Lieu lieuDetails) {
        Lieu updatedLieu = lieu_service.updateLieu(id, lieuDetails);
        return ResponseEntity.ok(updatedLieu);
    }
}
