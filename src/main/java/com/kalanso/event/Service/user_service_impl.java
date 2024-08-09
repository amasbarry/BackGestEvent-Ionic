package com.kalanso.event.Service;


import com.kalanso.event.Model.*;
import com.kalanso.event.Repository.FileDataRepository;
import com.kalanso.event.Repository.RoleUserRepo;
import com.kalanso.event.Repository.StorageRepository;
import com.kalanso.event.Repository.Utilisateur_repo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class user_service_impl implements Utilisateur_service {

    private PasswordEncoder passwordEncoder;
    private Utilisateur_repo utilisateurRepo;
    private RoleUserRepo roleUserRepo;
    private StorageRepository repository;

    @Override
    public Client creerClient(Client client) {
        client.setMotDePasse(passwordEncoder.encode(client.getMotDePasse()));
        return utilisateurRepo.save(client);
    }

    @Override
    public Gestionnaire CreerGestionnaire(Gestionnaire gestionnaire) {
        RoleUser roleUser = roleUserRepo.findByRole("GESTIONNAIRE");
        gestionnaire.setRole(roleUser);
        gestionnaire.setMotDePasse(passwordEncoder.encode(gestionnaire.getMotDePasse()));
        return utilisateurRepo.save(gestionnaire);
    }

    @Override
    public Admin createAdmin(Admin admin) {
        RoleUser roleUser = roleUserRepo.findByRole("ADMIN");
        admin.setRole(roleUser);
        admin.setMotDePasse(passwordEncoder.encode(admin.getMotDePasse()));
        return utilisateurRepo.save(admin);
    }

    @Override
    public Organisateur creerOrganisateur(Organisateur organisateur) {
        RoleUser roleUser = roleUserRepo.findByRole("ORGANISATEUR");
        organisateur.setRole(roleUser);
        organisateur.setMotDePasse(passwordEncoder.encode(organisateur.getMotDePasse()));
        return utilisateurRepo.save(organisateur);
    }

    @Override
    public List<Utilisateur> displayAll() {
        return utilisateurRepo.findAll();
    }

    @Override
    public Utilisateur display(Integer id) {
        return utilisateurRepo.findById(id).get();
    }

    @Override
    public Utilisateur update(Integer id, Utilisateur utilisateur) {
        return utilisateurRepo.findById(id)
                .map(p->{
                    p.setNom(utilisateur.getNom());
                    p.setPrenom(utilisateur.getPrenom());
                    p.setEmail(utilisateur.getEmail());
                    p.setTelephone(utilisateur.getTelephone());
                    p.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
                    p.setRole(utilisateur.getRole());
                    return utilisateurRepo.save(p);
        }).orElseThrow(()-> new RuntimeException("Erreur lors de la mise à jour"));
    }

    @Override
    public String delete(Integer id) {
        utilisateurRepo.deleteById(id);
        return "Utilisateur éffacé avec succès";
    }

    @Override
    public List<Utilisateur> listeParNom(String nom) {
        return utilisateurRepo.findByNom(nom);
    }

    @Override
    public Utilisateur findByEmail(String password) {
        return utilisateurRepo.findByEmail(password).orElse(null);
    }

    @Override
    public Client getClientById(Long id) {
        return null;
    }

    @Override
    public Client updateClient(Client client) {
        return null;
    }

    @Override
    public void deleteClient(Long id) {

    }


    private FileDataRepository fileDataRepository;

    private final String FOLDER_PATH="C:\\Users\\saran.soumbounou\\Desktop\\MyFIles\\";

    public String uploadImage(MultipartFile file) throws IOException {
        ImageData imageData = repository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if (imageData != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }



    public byte[] downloadImage(String fileName) {
        Optional<ImageData> dbImageData = repository.findByName(fileName);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }


    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        String filePath=FOLDER_PATH+file.getOriginalFilename();

        FileData fileData=fileDataRepository.save(FileData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath).build());

        file.transferTo(new File(filePath));

        if (fileData != null) {
            return "file uploaded successfully : " + filePath;
        }
        return null;
    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<FileData> fileData = fileDataRepository.findByName(fileName);
        String filePath=fileData.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }



}
