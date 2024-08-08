package com.kalanso.event.Repository;

import com.kalanso.event.Model.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Invitation_repository extends JpaRepository<Invitation,Long> {
}
