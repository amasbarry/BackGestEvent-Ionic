package com.kalanso.event.Service;

import com.kalanso.event.Model.PriorityTask;

import java.util.List;

public interface PriorityService {

    PriorityTask Ajout(PriorityTask priorityTask);

    List<PriorityTask> Afficher();

    PriorityTask MAJ(Long id, PriorityTask priorityTask);

    String Sup(Long id);

}
