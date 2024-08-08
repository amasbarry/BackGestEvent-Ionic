package com.kalanso.event.Model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Billet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_reservation", nullable = false)
    private Reservation reservation;

    private String typeBillet;
    private int prix;
    private int quantiteDisponible;
    private LocalDate dateDebutVente;
    private LocalDate dateFinVente;
    private String description;
    private String lienQrCode;
    private String categoryBillet;


    @ManyToOne
    @JoinColumn(name = "Statuts")
    private StatutBillet status;



}
