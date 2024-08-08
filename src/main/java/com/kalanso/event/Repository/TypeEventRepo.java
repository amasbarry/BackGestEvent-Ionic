package com.kalanso.event.Repository;

import com.kalanso.event.Model.RoleUser;
import com.kalanso.event.Model.TypeEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeEventRepo extends JpaRepository<TypeEvent, Integer> {
    TypeEvent findByType(String type);
}