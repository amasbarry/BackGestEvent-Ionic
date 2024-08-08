package com.kalanso.event.Service.Notification;

import com.google.zxing.WriterException;
import com.kalanso.event.Model.Notification;
import com.lowagie.text.DocumentException;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.util.List;

public interface Notification_service {

    //Methode pour envoyer un message;
    void SendMail (Notification notification) throws MessagingException, IOException, DocumentException, WriterException;

    //Methode pour ajouter une notification à la BD.
    Notification Ajouter (Notification notification);

    //Methode pour Afficher une notification à la BD.
    List<Notification> Afficher ();

    //Methode pour supprimer une notification à la BD.
    String Delete (Integer id);

}
