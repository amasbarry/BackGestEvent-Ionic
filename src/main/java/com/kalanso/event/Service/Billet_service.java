package com.kalanso.event.Service;

import com.kalanso.event.Model.Billet;
import com.kalanso.event.Repository.Billet_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class Billet_service {

    private final Billet_repository billetRepository;

    @Autowired
    public Billet_service(Billet_repository billetRepository) {
        this.billetRepository = billetRepository;
    }

    public List<Billet> getAllBillets() {
        return billetRepository.findAll();
    }

    public Optional<Billet> getBilletById(Long id) {
        return billetRepository.findById(id);
    }

    public Billet createBillet(Billet billet) {
        return billetRepository.save(billet);
    }

    public Billet updateBillet(Long id, Billet updatedBillet) {
        Optional<Billet> existingBilletOptional = billetRepository.findById(id);
        if (existingBilletOptional.isPresent()) {
            updatedBillet.setId(id);
            return billetRepository.save(updatedBillet);
        }
        return null; // Handle not found scenario as needed
    }

    public void deleteBillet(Long id) {
        billetRepository.deleteById(id);
    }
}
