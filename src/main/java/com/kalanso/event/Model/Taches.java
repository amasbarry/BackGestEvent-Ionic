package com.kalanso.event.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Taches {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String Title;

    @ManyToOne
    @JoinColumn(name = "priority_id")
    private PriorityTask priority;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Evenement evenement;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Utilisateur utilisateur;



}
