package com.kalanso.event.Service;


import com.kalanso.event.Model.Invitation;
import com.kalanso.event.Repository.Invitation_repository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class Invitation_service {

    private Invitation_repository invitationRepository;

    public List<Invitation> findAll() {
        return invitationRepository.findAll();
    }

    public Optional<Invitation> findById(Long id) {
        return invitationRepository.findById(id);
    }

    public Invitation save(Invitation invitation) {
        return invitationRepository.save(invitation);
    }

    public void deleteById(Long id) {
        invitationRepository.deleteById(id);
    }
}
