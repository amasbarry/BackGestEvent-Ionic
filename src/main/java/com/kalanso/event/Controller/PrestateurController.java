package com.kalanso.event.Controller;

import com.kalanso.event.Model.Presta;
import com.kalanso.event.Service.PrestateurService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/gestEvent/prestateurs")
@AllArgsConstructor
public class PrestateurController {
   @Autowired
    private PrestateurService prestateurService;

    @GetMapping("/ListePresta")
    public List<Presta> getAllPrestateurs() {
        return prestateurService.ListPrestateurs();
    }



    @PostMapping("/CreerPresta")
    public Presta createPrestateur(@RequestBody Presta prestateur) {
        return prestateurService.AjouterPresta(prestateur);
    }

    @PutMapping("/ModifPresta/{id}")
       public Presta ModifPresta(@PathVariable Long id, @RequestBody Presta presta)
    {
        return prestateurService.ModifPresta(id, presta);
    }


    @DeleteMapping("/delete/{id}")
    public String deletePrestateur(@PathVariable Long id)
    {
        return prestateurService.SupPrestateur(id);
    }

}
