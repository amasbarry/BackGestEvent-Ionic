package com.kalanso.event.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kalanso.event.Model.*;
import com.kalanso.event.Service.ContexHolder;
import com.kalanso.event.Service.StorageService;
import com.kalanso.event.Service.Utilisateur_service;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins="http://localhost:8100")
@RestController
@RequestMapping("gestEvent/user")
@AllArgsConstructor
public class UtilisateurController {

    private Utilisateur_service utilisateurService;
    private ContexHolder contexHolder;
    private StorageService storageService;
    @PostMapping("/CreerAdmin")
    public Admin CreerAdmin(@RequestBody Admin admin, @RequestParam("image") MultipartFile image ){
        return utilisateurService.createAdmin(admin);
    }


    @PostMapping("/Cree")
    public ResponseEntity<Admin> CreerAdmin(
            @RequestParam("admin") String adminJson,
            @RequestParam("image") MultipartFile image) throws IOException {


        System.out.println("je suis la: " + adminJson);
        System.out.println("Received Image: " + image.getOriginalFilename());
        // Deserialize the admin JSON string into an Admin object
        ObjectMapper objectMapper = new ObjectMapper();
        Admin admin = objectMapper.readValue(adminJson, Admin.class);

        // Upload the image and get the ImageData object
        ImageData imageData = storageService.uploadImage(image);

        // Set the ImageData in the Admin entity
        admin.setImageData(imageData);

        // Create the Admin with image
        Admin createdAdmin = utilisateurService.createAdmin(admin);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdAdmin);
    }

    @PostMapping("/CreerGest")
    public Gestionnaire CreerGest(@RequestBody Gestionnaire gestionnaire){
        return utilisateurService.CreerGestionnaire(gestionnaire);
    }

    @PostMapping("/CreerCliente")
    public Client creerClient(@RequestBody Client client){
        return utilisateurService.creerClient(client);
    }

    @PostMapping("/CreerClient")
    public ResponseEntity<Client> CreerClient(
            @RequestParam("client") String clientJson,
            @RequestParam("image") MultipartFile image) throws IOException {


        System.out.println("je suis la: " + clientJson);
        System.out.println("Received Image: " + image.getOriginalFilename());
        // Deserialize the admin JSON string into an Admin object
        ObjectMapper objectMapper = new ObjectMapper();
        Client client = objectMapper.readValue(clientJson, Client.class);

        // Upload the image and get the ImageData object
        ImageData imageData = storageService.uploadImage(image);

        // Set the ImageData in the Admin entity
        client.setImageData(imageData);

        // Create the Admin with image
        Client createdClient = utilisateurService.creerClient(client);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
    }



    @PostMapping("/CreerOrga")
    public Organisateur creerClient(@RequestBody Organisateur organisateur){
        return utilisateurService.creerOrganisateur(organisateur);
    }

    @GetMapping("/Users")
    List<Utilisateur> displayUsers(){
        return utilisateurService.displayAll();
    }

    @GetMapping("/User/{id}")
    Utilisateur displayUser(@PathVariable Integer id){
        return utilisateurService.display(id);
    }

    @GetMapping("/TriParNom")
    List<Utilisateur> TrierByname(String nom){
        return utilisateurService.listeParNom(nom);
    }


    @PutMapping("/UpdateUser/{id}")
    public Utilisateur UpdateUser(@PathVariable Integer id, @RequestBody Utilisateur utilisateur){
        return utilisateurService.update(id, utilisateur);
    }

    @DeleteMapping("/deleteUser/{id}")
    public  String supprimerAdmin( @PathVariable Integer id){
        return utilisateurService.delete(id);
    }

    @GetMapping("/currentSession")
    public Utilisateur getCurrentUser() {
        /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && ((org.springframework.security.core.Authentication) authentication).getPrincipal() instanceof org.springframework.security.core.userdetails.User) {
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
            return utilisateurService.findByEmail(user.getPassword()); // Assurez-vous que cette méthode existe dans votre service
        }
        return null; // ou une réponse appropriée en cas d'absence d'utilisateur connecté*/
        return contexHolder.utilisateur();
    }



}
