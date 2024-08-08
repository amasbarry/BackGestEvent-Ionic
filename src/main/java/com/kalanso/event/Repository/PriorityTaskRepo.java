package com.kalanso.event.Repository;

import com.kalanso.event.Model.PriorityTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriorityTaskRepo extends JpaRepository<PriorityTask, Long> {
    PriorityTask findByPriority(String priority);
}
