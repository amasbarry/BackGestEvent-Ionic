package com.kalanso.event.Controller;

import com.kalanso.event.Model.Billet;
import com.kalanso.event.Service.Billet_service;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/gestEvent/billets")
@AllArgsConstructor
public class Billet_controller {

    private Billet_service billetService;


    @GetMapping
    public List<Billet> getAllBillets() {
        return billetService.getAllBillets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Billet> getBilletById(@PathVariable Long id) {
        Optional<Billet> billetOptional = billetService.getBilletById(id);
        return billetOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("AjoutBillet")
    public ResponseEntity<Billet> createBillet(@RequestBody Billet billet) {
        Billet createdBillet = billetService.createBillet(billet);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBillet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Billet> updateBillet(@PathVariable Long id, @RequestBody Billet updatedBillet) {
        Billet updated = billetService.updateBillet(id, updatedBillet);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBillet(@PathVariable Long id) {
        billetService.deleteBillet(id);
        return ResponseEntity.noContent().build();
    }
}
