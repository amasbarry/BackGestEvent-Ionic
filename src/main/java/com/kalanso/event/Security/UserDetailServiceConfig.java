package com.kalanso.event.Security;

import com.kalanso.event.Model.Utilisateur;
import com.kalanso.event.Repository.Utilisateur_repo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserDetailServiceConfig implements UserDetailsService {

    private Utilisateur_repo utilisateur_repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("hello");
        Optional<Utilisateur> utilisateur = utilisateur_repo.findByEmail(email);
        System.out.println("helloAlpha");
        if (utilisateur.isPresent()) {
            System.out.println("helloAlpha2");
            System.out.println(utilisateur.get().getRole().getRole());
            System.out.println(utilisateur.get().getEmail());
            System.out.println(utilisateur.get().getMotDePasse());
        }
        System.out.println("helloAlpha1");
        return User
                .withUsername(utilisateur.get().getEmail())
                .password(utilisateur.get().getMotDePasse())
                .roles(utilisateur.get().getRole().getRole())
                .build();
    }
}
