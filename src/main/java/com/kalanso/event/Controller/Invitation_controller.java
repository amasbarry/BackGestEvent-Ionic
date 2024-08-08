package com.kalanso.event.Controller;

import com.kalanso.event.Model.Invitation;
import com.kalanso.event.Service.Invitation_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/gestEvent/invitations")
public class Invitation_controller {

    @Autowired
    private Invitation_service invitationService;

    @GetMapping
    public List<Invitation> getAllInvitations() {
        return invitationService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invitation> getInvitationById(@PathVariable Long id) {
        Optional<Invitation> invitation = invitationService.findById(id);
        return invitation.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Invitation createInvitation(@RequestBody Invitation invitation) {
        return invitationService.save(invitation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invitation> updateInvitation(@PathVariable Long id, @RequestBody Invitation invitationDetails) {
        Optional<Invitation> invitation = invitationService.findById(id);

        if (invitation.isPresent()) {
            Invitation updatedInvitation = invitation.get();
            updatedInvitation.setPrestateur(invitationDetails.getPrestateur());
            updatedInvitation.setEvenement(invitationDetails.getEvenement());
            return ResponseEntity.ok(invitationService.save(updatedInvitation));
        } else {
            return ResponseEntity.notFound().build();

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvitation(@PathVariable Long id) {
        Optional<Invitation> invitation = invitationService.findById(id);

        if (invitation.isPresent()) {
            invitationService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

