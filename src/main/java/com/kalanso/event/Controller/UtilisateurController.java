package com.kalanso.event.Controller;

import com.kalanso.event.Model.*;
import com.kalanso.event.Service.ContexHolder;
import com.kalanso.event.Service.Utilisateur_service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("gestEvent/user")
@AllArgsConstructor
public class UtilisateurController {

    @PostMapping("/CreerClient")
    public Client creerClient(@RequestBody Client client){
        return utilisateurService.creerClient(client);
    }


    @PostMapping(value = "/CreerCliente", consumes = "multipart/form-data")
    public ResponseEntity<String> creerClient(
            @RequestParam("nom") String nom,
            @RequestParam("prenom") String prenom,
            @RequestParam("email") String email,
            @RequestParam("motDePasse") String motDePasse,
            @RequestParam("telephone") String telephone,
            @RequestParam(value = "image", required = false) MultipartFile imageFile) throws IOException {

        // Validation des données du client
        if (nom == null || prenom == null || email == null || motDePasse == null || telephone == null) {
            return new ResponseEntity<>("Erreur : Tous les champs doivent être remplis.", HttpStatus.BAD_REQUEST);
        }

        Client client = new Client();
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setEmail(email);
        client.setMotDePasse(motDePasse);
        client.setTelephone(telephone);

        if (imageFile != null && !imageFile.isEmpty()) {
            // Vérifier le type MIME du fichier
            String contentType = imageFile.getContentType();
            if (contentType == null || (!contentType.equals("image/png") && !contentType.equals("image/jpeg"))) {
                return new ResponseEntity<>("Erreur : Format d'image non autorisé. Formats autorisés : PNG, JPG.", HttpStatus.BAD_REQUEST);
            }

            // Vérifier l'extension du fichier
            String filename = imageFile.getOriginalFilename();
            if (filename != null) {
                String extension = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
                if (!extension.equals("png") && !extension.equals("jpg") && !extension.equals("jpeg")) {
                    return new ResponseEntity<>("Erreur : Extension de fichier non autorisée. Formats autorisés : PNG, JPG.", HttpStatus.BAD_REQUEST);
                }
            }

            // Convertir le fichier en tableau de bytes et l'ajouter au client
            client.setImage(imageFile.getBytes());
        }

        // Appel du service pour créer le client
        utilisateurService.creerClient(client);

        return new ResponseEntity<>("Client créé avec succès !", HttpStatus.CREATED);
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        Client client = utilisateurService.getClientById(id);
        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/clients/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client updatedClient) {
        Client client = utilisateurService.getClientById(id);
        if (client != null) {

           client.setNom(updatedClient.getNom());
           client.setPrenom(updatedClient.getPrenom());
           client.setEmail(updatedClient.getEmail());
           client.setMotDePasse(updatedClient.getMotDePasse());
           client.setTelephone(updatedClient.getTelephone());


            Client savedClient = utilisateurService.updateClient(client);
            return ResponseEntity.ok(savedClient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        Client client = utilisateurService.getClientById(id);
        if (client != null) {
            utilisateurService.deleteClient(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }








    private Utilisateur_service utilisateurService;
    private ContexHolder contexHolder;

    @PostMapping("/CreerAdmin")
    public Admin CreerAdmin(@RequestBody Admin admin){
        return utilisateurService.createAdmin(admin);
    }

    @PostMapping("/CreerGest")
    public Gestionnaire CreerGest(@RequestBody Gestionnaire gestionnaire){
        return utilisateurService.CreerGestionnaire(gestionnaire);
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
