package com.kalanso.event.Repository;

import com.kalanso.event.Model.RolePrestateur;
import com.kalanso.event.Model.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePrestateurRepo extends JpaRepository<RolePrestateur, Long> {
    RolePrestateur findByRole(String role);
}
