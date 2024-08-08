package com.kalanso.event.Controller;

import com.kalanso.event.Model.Taches;
import com.kalanso.event.Service.TachesServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/gestEvent/tasks")
@AllArgsConstructor
public class TachesController {
    @Autowired
    private TachesServiceImpl tachesService;

    @PostMapping("/CreerTache")
    public Taches CreerTache(@RequestBody Taches taches){
        return tachesService.CreerTache(taches);
    }
    @GetMapping("/ListerTaches")
    public List<Taches> ListeTaches()
    {
        return tachesService.TACHES_LIST();
    }
    @PutMapping("/ModifTaches/{id}")
    public Taches ModifTaches(@PathVariable Integer id, @RequestBody Taches taches)
    {
        return tachesService.updateTaches(id,taches);
    }
    @DeleteMapping("/SupTask/{id}")
    public String SupprimerTache(@PathVariable Integer id)
    {
        return tachesService.deleteTaches(id);
    }
}
