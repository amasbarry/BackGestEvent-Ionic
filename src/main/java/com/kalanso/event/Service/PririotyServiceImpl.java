package com.kalanso.event.Service;

import com.kalanso.event.Model.PriorityTask;
import com.kalanso.event.Repository.PriorityTaskRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor

public class PririotyServiceImpl implements PriorityService {

    private final PriorityTaskRepo priorityTaskRepo;

    @Override
    public PriorityTask Ajout(PriorityTask priorityTask) {

        return priorityTaskRepo.save(priorityTask);
    }

    @Override
    public List<PriorityTask> Afficher() {
        return priorityTaskRepo.findAll();
    }

    @Override
    public PriorityTask MAJ(Long id, PriorityTask priorityTask) {
        return priorityTaskRepo.findById(id)
                .map(p->{
                   // p.setId(priorityTask.getId());
                    p.setPriority(priorityTask.getPriority());
                    return priorityTaskRepo.save(p);
                }).orElseThrow(()->new RuntimeException("Erreur lors de la mise à jour"));
    }

    @Override
    public String Sup(Long id) {
        priorityTaskRepo.deleteById(id);
        return "Priorité effacer avec succès";
    }
}
