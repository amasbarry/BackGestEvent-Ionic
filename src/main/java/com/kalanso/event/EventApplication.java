package com.kalanso.event;

import com.kalanso.event.Model.*;
import com.kalanso.event.Repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@SpringBootApplication (exclude = { SecurityAutoConfiguration.class , })
public class EventApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner1(RolePrestateurRepo rolePrestateurRepo,
												StatutReservationRepo statutReservationRepo, StatutEnvoiRepo statutEnvoiRepo,
												StatutBilletRepo statutBilletRepo) {
		return args -> {
			RolePrestateur rolePrestateurs= rolePrestateurRepo.findByRole("CONFERANCIER");
			if (rolePrestateurs == null) {
				RolePrestateur rolePrestateur = new RolePrestateur();
				rolePrestateur.setRole("CONFERANCIER");
				rolePrestateurRepo.save(rolePrestateur);
			}

			StatutReservation statutReservations1 = statutReservationRepo.findByStatut("ACTIVE");
			StatutReservation statutReservations2 = statutReservationRepo.findByStatut("INACTIVE");
			if (statutReservations1 == null || statutReservations2 == null) {
				StatutReservation statutReservation1 = new StatutReservation();
				statutReservation1.setStatut("ACTIVE");
				statutReservationRepo.save(statutReservation1);
				StatutReservation statutReservation2 = new StatutReservation();
				statutReservation2.setStatut("INACTIVE");
				statutReservationRepo.save(statutReservation2);
			}

			StatutEnvoi statutEnvois = statutEnvoiRepo.findByStatut("SUCCES");//Or ECHEC
			if (statutEnvois == null) {
				StatutEnvoi statutEnvoi  = new StatutEnvoi();
				statutEnvoi.setStatut("SUCCES");
				statutEnvoiRepo.save(statutEnvoi);
			}

			StatutBillet statutBillets = statutBilletRepo.findByStatut("Actif");//Or INACTIF
			if (statutBillets == null) {
				StatutBillet statutBillet = new StatutBillet();
				statutBillet.setStatut("Actif");
				statutBilletRepo.save(statutBillet);
			}

		};
	}

	@Bean
	public CommandLineRunner commandLineRunner2(RoleUserRepo roleuserRepo,MethoPaieRepo methoPaieRepo,TypeEventRepo typeEventRepo, PriorityTaskRepo priorityTaskRepo, CategorieBilletRepo categorieBilletRepo, CategorieEventRepo categorieEventRepo) {
		return args -> {
			PriorityTask priorityTask1 = priorityTaskRepo.findByPriority("ELEVEE");
			PriorityTask priorityTask2 = priorityTaskRepo.findByPriority("MOYENNE");
			PriorityTask priorityTask3 = priorityTaskRepo.findByPriority("FAIBLE");
			if (priorityTask1 == null || priorityTask3 == null || priorityTask2 == null) {
				PriorityTask priorityTaskA = new PriorityTask();
				priorityTaskA.setPriority("ELEVEE");
				priorityTaskRepo.save(priorityTaskA);
				PriorityTask priorityTaskB = new PriorityTask();
				priorityTaskB.setPriority("MOYENNE");
				priorityTaskRepo.save(priorityTaskB);
				PriorityTask priorityTaskC = new PriorityTask();
				priorityTaskC.setPriority("FAIBLE");
				priorityTaskRepo.save(priorityTaskC);
			}

			CategorieBillet categorieBillet1 = categorieBilletRepo.findByCategory("VIP");
			CategorieBillet categorieBillet2= categorieBilletRepo.findByCategory("PELOUSE");
			CategorieBillet categorieBillet3= categorieBilletRepo.findByCategory("GRATUIT");
			if (categorieBillet1 == null || categorieBillet2 == null || categorieBillet3 == null) {
				CategorieBillet categorieBilletA = new CategorieBillet();
				categorieBilletA.setCategory("VIP");
				categorieBilletRepo.save(categorieBilletA);
				CategorieBillet categorieBilletB = new CategorieBillet();
				categorieBilletB.setCategory("PELOUSE");
				categorieBilletRepo.save(categorieBilletB);
				CategorieBillet categorieBilletC = new CategorieBillet();
				categorieBilletC.setCategory("GRATUIT");
				categorieBilletRepo.save(categorieBilletC);
			}

			CategorieEvent categorieEvent2= categorieEventRepo.findByCategory("CONFERANCE");
			CategorieEvent categorieEvent1= categorieEventRepo.findByCategory("CONCERT");
			if (categorieEvent1 == null || categorieEvent2 == null) {
				CategorieEvent categorieEventA = new CategorieEvent();
				categorieEventA.setCategory("CONFERANCE");
				categorieEventRepo.save(categorieEventA);
				CategorieEvent categorieEventB = new CategorieEvent();
				categorieEventB.setCategory("CONCERT");
				categorieEventRepo.save(categorieEventB);
			}

			TypeEvent typeEvent1= typeEventRepo.findByType("PAYANT");
			TypeEvent typeEvent2= typeEventRepo.findByType("GRATUIT");
			if (typeEvent1 == null || typeEvent2 == null) {
				TypeEvent typeEventA = new TypeEvent();
				typeEventA.setType("PAYANT");
				typeEventRepo.save(typeEventA);
				TypeEvent typeEventB = new TypeEvent();
				typeEventB.setType("GRATUIT");
				typeEventRepo.save(typeEventB);
			}

			MethodePaiement methodepaie1= methoPaieRepo.findByMethodepaie("Orange_Money");
			MethodePaiement methodepaie2= methoPaieRepo.findByMethodepaie("Moov_money");
			if (typeEvent1 == null || typeEvent2 == null) {
				MethodePaiement methodepaieA = new MethodePaiement();
				methodepaieA.setMethodepaie("Orange_Money");
				methoPaieRepo.save(methodepaieA);
				MethodePaiement methodepaieB = new MethodePaiement();
				methodepaieB.setMethodepaie("Moov_money");
				methoPaieRepo.save(methodepaieB);
			}

            RoleUser roleUsers1 = roleuserRepo.findByRole("GESTIONNAIRE");
            RoleUser roleUsers2 = roleuserRepo.findByRole("ORGANISATEUR");
            if (roleUsers1 == null || roleUsers2 == null) {
                RoleUser roleUser1 = new RoleUser();
                roleUser1.setRole("GESTIONNAIRE");
                System.out.println("hello3");
                RoleUser roleUser2 = new RoleUser();
                roleUser2.setRole("ORGANISATEUR");
                roleuserRepo.save(roleUser1);
                roleuserRepo.save(roleUser2);
            }

		};
	}
	@Bean
	public CommandLineRunner commandLineRunnerAdmin(RoleUserRepo roleuserRepo, PasswordEncoder passwordEncoder, Utilisateur_repo utilisateurRepo, RoleUserRepo roleUserRepo) {
		return args -> {
			RoleUser roleUsers = roleuserRepo.findByRole("ADMIN");
			if (roleUsers == null) {
				RoleUser roleUser = new RoleUser();
				roleUser.setRole("ADMIN");
				System.out.println("hello3");
				roleuserRepo.save(roleUser);
			}

			Optional<Utilisateur> utilisateur = utilisateurRepo.findByEmail("houatt0@gmail.com");
			System.out.println("hello1");
			if(utilisateur.isEmpty()){
				System.out.println("hello2");
				Admin admin = new Admin();
				RoleUser roleUser = roleUserRepo.findByRole("ADMIN");
				admin.setEmail("houatt0@gmail.com");
				admin.setNom("Administrateur");
				admin.setPrenom("Administrateur");
				admin.setMotDePasse(passwordEncoder.encode("Admin@odk.com"));
				admin.setTelephone("+22372834301");
				admin.setRole(roleUser);
				utilisateurRepo.save(admin);
			}
		};
	}

}
