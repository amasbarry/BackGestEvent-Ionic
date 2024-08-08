package com.kalanso.event.Controller;

import com.kalanso.event.Model.PriorityTask;
import com.kalanso.event.Service.PririotyServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gestEvent/PriorityTask")
@AllArgsConstructor
public class PriorityTaskControler {
    private PririotyServiceImpl pririotyService;
    @PostMapping("/AddPriority")
    public PriorityTask AddPriorityTask(@RequestBody PriorityTask priorityTask)
    {
        return pririotyService.Ajout(priorityTask);
    }
    @GetMapping("/ListPriority")
     public List<PriorityTask> ListPriority()
    {
        return pririotyService.Afficher();
    }
    @PutMapping("/Modifier/{id}")
     public PriorityTask ModifPriority(@PathVariable Long id,@RequestBody PriorityTask priorityTask)
    {
        return pririotyService.MAJ(id,priorityTask);
    }
    @DeleteMapping("/SupPriority/{id}")
    public String SupPrioTask(@PathVariable Long id)
    {
        return pririotyService.Sup(id);
    }
}
