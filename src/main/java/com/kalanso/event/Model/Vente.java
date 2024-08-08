package com.kalanso.event.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Vente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "billet_id")
    private Billet billet;

/*  @ManyToOne
    @JoinColumn(name = "participant_id")
    private Participant participant;*/

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private LocalDateTime dateVente;
    private BigDecimal montant;
    // Getters, setters, constructeurs
}
