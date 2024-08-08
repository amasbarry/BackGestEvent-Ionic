package com.kalanso.event.Service;

import com.google.zxing.WriterException;
import com.kalanso.event.Model.*;
import com.kalanso.event.Repository.*;
import com.kalanso.event.Service.Notification.*;
import com.lowagie.text.DocumentException;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class Event_service_impl implements Evenement_service {

    private Lieu_repo lieu_repo;
    private Evenement_repo evenement_repo;
    private Notif_service_Evenement_impl notifServiceEvenementImpl;
    private Utilisateur_repo utilisateur_repo;
    private RoleUserRepo roleUserRepo;
    private Derouler_repo derouler_repo;

    @Override
    public String Ajout(Evenement evenement, Integer id) {
        Lieu lieu = lieu_repo.findById(id).get();
        RoleUser roleUser = roleUserRepo.findByRole("ADMIN");
        List<Utilisateur> utilisateurs = utilisateur_repo.findByRole(roleUser);
        evenement_repo.save(evenement);
        Derouler derouler = new Derouler();
        derouler.setEvenement(evenement);
        derouler.setLieu(lieu);
        derouler_repo.save(derouler);
        utilisateurs.forEach(p->{
            Notification notification = new Notification();
            notification.setEvenement(evenement);
            notification.setSujet("Ajout d'évènement");
            notification.setDest_email(p.getEmail());
            notification.setUtilisateur(p);

            try {
                notifServiceEvenementImpl.SendMail(notification);

            } catch (MessagingException | WriterException | DocumentException | IOException e) {
                throw new RuntimeException(e);
            }
        });
        return "Evenement ajouter avec success";
    }

    @Override
    public List<Evenement> Afficher() {
        return evenement_repo.findAll();
    }

    @Override
    public String Delete(Integer id) {
        evenement_repo.deleteById(id);
        return "Event deleted with succes";
    }

    @Override
    public Evenement update(Integer id, Evenement evenement) {
        return evenement_repo.findById(id)
                .map(p->{
                    p.setNom(evenement.getNom());
                    p.setDescription(evenement.getDescription());
                    p.setCategory(evenement.getCategory());
                    p.setDatedebut(evenement.getDatedebut());
                    p.setDatefin(evenement.getDatefin());
                    p.setDerouler(evenement.getDerouler());
                    p.setNombrePlace(evenement.getNombrePlace());
                    return evenement_repo.save(p);
                }).orElseThrow(()-> new RuntimeException("The event couldn't be deleted "));
    }
}
