package com.kalanso.event.Model;/*package com.kalanso.event.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

//@Entity
@Data
@Table(name = "inscriptions")
public class Inscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "participant_id")
    private Participant participant;
    @ManyToOne
    @JoinColumn(name = "evenement_id")
    private Evenement evenement;
    private LocalDateTime dateInscription;
    private String statut;
    // Getters, setters, constructeurs
}*/
