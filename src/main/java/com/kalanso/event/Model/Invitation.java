package com.kalanso.event.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date = new Date();

    @ManyToOne
    @JoinColumn(name = "prestateur_id", nullable=false)
    private Presta prestateur;

    @ManyToOne
    @JoinColumn(name = "id_event", nullable=false)
    private Evenement evenement;

}
