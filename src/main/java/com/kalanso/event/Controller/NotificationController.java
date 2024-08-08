package com.kalanso.event.Controller;

import com.google.zxing.WriterException;
import com.kalanso.event.Model.Notification;
import com.kalanso.event.Service.Notification.Notif_service_Alert_impl;
import com.kalanso.event.Service.Notification.Notification_service;
import com.lowagie.text.DocumentException;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@CrossOrigin(origins="*")
@Controller
@RestController
@RequestMapping("/gestEvent/notif")
@AllArgsConstructor
public class NotificationController{

    private Notif_service_Alert_impl notificationService;

    @PostMapping("/send")
    public void SendMail(@RequestBody Notification notification) throws MessagingException, IOException, DocumentException, WriterException {
            notificationService.SendMail(notification);
    }

    @PostMapping("/add")
    public Notification Ajouter(Notification notification) {
        return notificationService.Ajouter(notification);
    }

    @GetMapping("/Afficher")
    public List<Notification> Afficher() {
        return notificationService.Afficher();
    }

    @DeleteMapping("/Delete")
    public String Delete(Integer id) {
        return notificationService.Delete(id);
    }

}
