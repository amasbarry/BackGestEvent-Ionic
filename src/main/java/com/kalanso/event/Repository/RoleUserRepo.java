package com.kalanso.event.Repository;


import com.kalanso.event.Model.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleUserRepo extends JpaRepository<RoleUser, Long> {
    RoleUser findByRole(String admin);
}