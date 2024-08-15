package com.kalanso.event.Controller;

import com.kalanso.event.Service.ContexHolder;
import com.kalanso.event.Service.AuditService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditAspect {

    private final AuditService auditService;

    @AfterReturning("execution(* com.kalanso.event.Controller..*(..))")
    public void logAfterMethod(JoinPoint joinPoint) {
        String action = "Méthode exécutée : " + joinPoint.getSignature().getName();
        String username = getCurrentUsername();

        // Enregistrez le log d'audit
        auditService.logAction(action, username);

        // Log pour la vérification
        System.out.println(action + " - Utilisateur : " + username);
    }
    // recuperer utilisateur courament connecté
    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.User) {
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
            return user.getUsername();
        }
        return "Utilisateur inconnu";
    }
}
