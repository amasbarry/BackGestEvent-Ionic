package com.kalanso.event.Service.Notification;



import com.kalanso.event.Model.Notification;
import com.kalanso.event.Repository.Notification_repo;
import com.kalanso.event.Service.ContexHolder;
import com.kalanso.event.Service.QRCodeService;
import com.lowagie.text.DocumentException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import com.google.zxing.WriterException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

@Service
@AllArgsConstructor
public class Notif_service_Reservation_impl {

    private Notification_repo notificationRepo;
    private JavaMailSender javaMailSender;
    private QRCodeService qrCodeService;
    private generatePdfFromHtml generatePdfFromHtml;
    private GenerateImageFromPdf generateImageFromPdf;
    private GenerateRandomString generateRandomString;
    private ContexHolder contexHolder;

    public void sendMailWelcome(Notification notification) throws MessagingException, IOException, DocumentException, WriterException{

        LocalDateTime debutEventDateTime = LocalDateTime.ofInstant(notification.getEvenement().getDatedebut().toInstant(), ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss"); // Définir le format souhaité ici
        String heureFormatee = debutEventDateTime.format(formatter);

        String content ="<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <style>\n" +
                "        body { font-family: 'Roboto', sans-serif; background-color: #ffffff; color: white; margin: 0; padding: 0; }\n" +
                "        .container { max-width: 600px; margin: auto; padding: 20px; background-color: #1f1f1f; color: white;border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.5); }\n" +
                "        .header { background-color: #ff7000; padding: 20px; border-radius: 10px 10px 0 0; text-align: center; }\n" +
                "        .header h1 { margin: 0; font-size: 24px; }\n" +
                "        .content { padding: 20px; }\n" +
                "        @page { width : 300px; height: 100px; }" +
                "        .content p { line-height: 1.6; }\n" +
                "        .footer { text-align: center; color: #888; font-size: 12px; padding: 10px; border-radius: 0 0 10px 10px; background-color: #2c2c2c; }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"header\">\n" +
                "            <h1>Bienvenue à Notre Événement, " + contexHolder.utilisateur().getNom() + "!</h1>\n" +
                "        </div>\n" +
                "        <div class=\"content\">\n" +
                "            <p>Nous sommes ravis de vous inviter à notre événement spécial. Préparez-vous pour une expérience inoubliable avec des sessions interactives, des conférenciers de renommée mondiale, et bien plus encore.</p>\n" +
                "            <p>Voici les détails de l'événement :</p>\n" +
                "            <ul>\n" +
                "                <li>Date : " + notification.getEvenement().getDatedebut() + "</li>\n" +
                "                <li>Lieu : Stade du 26 Mars</li>\n" +
                "                <li>Heure : "+ heureFormatee + "</li>\n" +
                "            </ul>\n" +
                "            <p>Assurez-vous de confirmer votre présence en répondant à cet e-mail. Nous avons hâte de vous voir!</p>\n" +
                "        </div>\n" +
                "        <div class=\"footer\">\n" +
                "            <p>&copy; 2024 ODK_P4_GP2. Tous droits réservés.</p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true) ;
        helper.setTo(notification.getDest_email());
        helper.setSubject(notification.getSujet());
        helper.setSentDate(notification.getDateEnvoi());
        helper.setText(content, true);
        System.out.println("hello");

        javaMailSender.send(message);
        notificationRepo.save(notification);
    }

    public void sendMailCancel(Notification notification) throws MessagingException{

        String content ="<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <style>\n" +
                "        body { font-family: 'Roboto', sans-serif; background-color: #ffffff; color: white; margin: 0; padding: 0; }\n" +
                "        .container { max-width: 600px; margin: auto; padding: 20px; background-color: #1f1f1f; color: white;border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.5); }\n" +
                "        .header { background-color: #ff7000; padding: 20px; border-radius: 10px 10px 0 0; text-align: center; }\n" +
                "        .header h1 { margin: 0; font-size: 24px; }\n" +
                "        .content { padding: 20px; }\n" +
                "        @page { width : 300px; height: 100px; }" +
                "        .content p { line-height: 1.6; }\n" +
                "        .footer { text-align: center; color: #888; font-size: 12px; padding: 10px; border-radius: 0 0 10px 10px; background-color: #2c2c2c; }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"header\">\n" +
                "            <h1>Annulation d'évènement !</h1>\n" +
                "        </div>\n" +
                "        <div class=\"content\">\n" +
                "            <p> Votre reservation a été annulé avec succès.</p>\n" +
                "        </div>\n" +
                "        <div class=\"footer\">\n" +
                "            <p>&copy; 2024 ODK_P4_GP2. Tous droits réservés.</p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true) ;
        helper.setTo(notification.getDest_email());
        helper.setSubject(notification.getSujet());
        helper.setSentDate(notification.getDateEnvoi());
        helper.setText(content, true);
        System.out.println("hello");

        javaMailSender.send(message);
        notificationRepo.save(notification);
    }

    /*public void SendMailTicket(Notification notification) throws MessagingException, IOException, DocumentException, WriterException{

        String qrCodeText = generateRandomString.generateRandomString();

        byte[] qrCodeImage = qrCodeService.generateQRCode(qrCodeText, 250, 250);
        InputStreamSource qrCodeSource = new ByteArrayResource(qrCodeImage);

        //String qrCodeFileName = "./uploads/qrcode.png"; // Remplacez par le chemin de votre dossier
        String qrCodeFileName = "D:/GestEvent_SRC/qrcode.png"; // Remplacez par le chemin de votre dossier
        Files.write(Paths.get(qrCodeFileName), qrCodeImage);

        String Content_Qrcode = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: 'Open Sans', Arial, sans-serif;\n" +
                "            background-color: #ffffff;\n" +
                "            color: white;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "        .container {\n" +
                "            max-width: 600px;\n" +
                "            margin: auto;\n" +
                "            padding: 20px;\n" +
                "            background-color: #1f1f1f;\n" +
                "            color: white;\n" +
                "            border-radius: 10px;\n" +
                "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);\n" +
                "            overflow: hidden;\n" +
                "        }\n" +
                "        .ticket-container {\n" +
                "            background-color: #2c2c2c;\n" +
                "            border-radius: 10px 10px 0 0;\n" +
                "            padding: 10px;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "        .ticket{\n" +
                "            display: flex;\n" +
                "            justify-content: center;\n" +
                "            align-items: center;\n" +
                "            margin-bottom: 10px;\n" +
                "        }\n" +
                "        .ticket-container img {\n" +
                "            max-width: 150px;\n" +
                "            height: auto;\n" +
                "            max-height: 150px;\n" +
                "            margin-right: 20px;\n" +
                "        }\n" +
                "        .ticket-details {\n" +
                "            display: block;\n" +
                "            text-align: left;\n" +
                "        }\n" +
                "        .footer {\n" +
                "            text-align: center;\n" +
                "            color: #888;\n" +
                "            font-size: 12px;\n" +
                "            padding: 5px;\n" +
                "            border-radius: 0 0 10px 10px;\n" +
                "            background-color: #2c2c2c;\n" +
                "        }\n" +
                "        @media (max-width: 600px) {\n" +
                "            .container {\n" +
                "                padding: 5px;\n" +
                "            }\n" +
                "        }\n" +
                "       @page {\n" +
                "           size: A8;\n " +
                "           margin: 2%; }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"ticket-container\">\n" +
                "            <h2>"+ notification.getEvenement().getNom() + "</h2>\n" +
                "            <div class=\"ticket\">\n" +
                "                <img src='D:/GestEvent_SRC/qrcode.png' alt=\"Code QR du Ticket\"></img>\n" +
                "                <div class=\"ticket-details\">\n" +
                "                    <p>Nom : "+ contexHolder.utilisateur().getNom() + "</p>\n" +
                "                    <p>Email : "+ contexHolder.utilisateur().getEmail() + "</p>\n" +
                "                    <p>Date : " + notification.getEvenement().getDate() + "</p>\n" +
                "                    <p>Numéro de Ticket : 123456789</p>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"footer\">\n" +
                "            <p>&copy; 2024 ODK_P4_GP2. Tous droits réservés.</p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true) ;
        helper.setTo(notification.getDest_email());
        helper.setSubject(notification.getSujet());
        helper.setSentDate(notification.getDateEnvoi());
        helper.setText(Content_Qrcode, true);
        helper.addInline("qrcode", qrCodeSource, "image/png");
        System.out.println("hello");

        byte[] pdfBytes = generatePdfFromHtml.generatePdfFromHtml(Content_Qrcode);

        // Convert PDF to image
        byte[] imageBytes = generateImageFromPdf.generateImageFromPdf(pdfBytes);

        InputStreamSource pdfSource = new ByteArrayResource(pdfBytes);
        helper.addAttachment("ticket.pdf", pdfSource);

        // Add image attachment
        InputStreamSource imageSource = new ByteArrayResource(imageBytes);
        helper.addAttachment("ticket.png", imageSource);

        javaMailSender.send(message);
        notificationRepo.save(notification);
    }*/

    public void SendMailTicket(Notification notification) throws MessagingException, IOException, DocumentException, WriterException {

        String qrCodeText = generateRandomString.generateRandomString();

        byte[] qrCodeImage = qrCodeService.generateQRCode(qrCodeText, 125, 125);
        InputStreamSource qrCodeSource = new ByteArrayResource(qrCodeImage);

        //String qrCodeFileName = "./uploads/qrcode.png"; // Remplacez par le chemin de votre dossier
        String qrCodeFileName = "D:/GestEvent_SRC/qrcode.png"; // Remplacez par le chemin de votre dossier

        // Convert QR code image to Base64 encoded string
        String qrCodeBase64 = Base64.getEncoder().encodeToString(qrCodeImage);
        String qrCodeDataUri = "data:image/png;base64," + qrCodeBase64;
        Files.write(Paths.get(qrCodeFileName), qrCodeImage);

        String Content_Qrcode1 = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: 'Open Sans', Arial, sans-serif;\n" +
                "            background-color: #ffffff;\n" +
                "            color: white;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "        .container {\n" +
                "            max-width: 600px;\n" +
                "            margin: auto;\n" +
                "            padding: 20px;\n" +
                "            background-color: #1f1f1f;\n" +
                "            color: white;\n" +
                "            border-radius: 10px;\n" +
                "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);\n" +
                "            overflow: hidden;\n" +
                "        }\n" +
                "        .ticket-container {\n" +
                "            background-color: #2c2c2c;\n" +
                "            border-radius: 10px 10px 0 0;\n" +
                "            padding: 10px;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "        .ticket {\n" +
                "            display: block;\n" +
                "            margin-bottom: 10px;\n" +
                "        }\n" +
                "        .ticket img {\n" +
                "            display: inline-block;\n" +
                "            max-width: 40%;\n" +
                "            height: auto;\n" +
                "            max-height: 100%;\n" +
                "            margin-right: 20px;\n" +
                "        }\n" +
                "        .ticket-details {\n" +
                "            max-width: 40%;\n" +
                "            display: inline-block;\n" +
                "            text-align: left;\n" +
                "        }\n" +
                "        .footer {\n" +
                "            text-align: center;\n" +
                "            color: #888;\n" +
                "            font-size: 12px;\n" +
                "            padding: 5px;\n" +
                "            border-radius: 0 0 10px 10px;\n" +
                "            background-color: #2c2c2c;\n" +
                "        }\n" +
                "        @media (max-width: 600px) {\n" +
                "            .container {\n" +
                "                padding: 5px;\n" +
                "            }\n" +
                "        }\n" +
                "       @page {\n" +
                "           size: A8;\n" +
                "           margin: 2%; }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"ticket-container\">\n" +
                "            <h2>" + notification.getEvenement().getNom() + "</h2>\n" +
                "            <div class=\"ticket\">\n" +
                "                <img src='" + qrCodeDataUri + "' alt=\"Code QR du Ticket\"></img>\n" +
                "                <div class=\"ticket-details\">\n" +
                "                    <p>Nom : " + contexHolder.utilisateur().getNom() + "</p>\n" +
                "                    <p>Email : " + contexHolder.utilisateur().getEmail() + "</p>\n" +
                "                    <p>Date : " + notification.getEvenement().getDate() + "</p>\n" +
                "                    <p>Numéro de Ticket : 123456789</p>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"footer\">\n" +
                "            <p>&copy; 2024 ODK_P4_GP2. Tous droits réservés.</p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";

        String Content_Qrcode = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: 'Open Sans', Arial, sans-serif;\n" +
                "            background-color: #ffffff;\n" +
                "            color: white;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "        .container {\n" +
                "            max-width: 600px;\n" +
                "            margin: auto;\n" +
                "            padding: 20px;\n" +
                "            background-color: #1f1f1f;\n" +
                "            color: white;\n" +
                "            border-radius: 10px;\n" +
                "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);\n" +
                "            overflow: hidden;\n" +
                "        }\n" +
                "        .ticket-container {\n" +
                "            background-color: #2c2c2c;\n" +
                "            border-radius: 10px 10px 0 0;\n" +
                "            padding: 10px;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "        .ticket{\n" +
                "            display: flex;\n" +
                "            justify-content: center;\n" +
                "            align-items: center;\n" +
                "            margin-bottom: 10px;\n" +
                "        }\n" +
                "        .ticket-container img {\n" +
                "            max-width: 150px;\n" +
                "            height: auto;\n" +
                "            max-height: 150px;\n" +
                "            margin-right: 20px;\n" +
                "        }\n" +
                "        .ticket-details {\n" +
                "            display: block;\n" +
                "            text-align: left;\n" +
                "        }\n" +
                "        .footer {\n" +
                "            text-align: center;\n" +
                "            color: #888;\n" +
                "            font-size: 12px;\n" +
                "            padding: 5px;\n" +
                "            border-radius: 0 0 10px 10px;\n" +
                "            background-color: #2c2c2c;\n" +
                "        }\n" +
                "        @media (max-width: 600px) {\n" +
                "            .container {\n" +
                "                padding: 5px;\n" +
                "            }\n" +
                "        }\n" +
                "       @page {\n" +
                "           size: A8;\n " +
                "           margin: 2%; }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"ticket-container\">\n" +
                "            <h2>"+ notification.getEvenement().getNom() + "</h2>\n" +
                "            <div class=\"ticket\">\n" +
                "                <img src='cid:qrcode' alt=\"Code QR du Ticket\"></img>\n" +
                "                <div class=\"ticket-details\">\n" +
                "                    <p>Nom : "+ contexHolder.utilisateur().getNom() + "</p>\n" +
                "                    <p>Email : "+ contexHolder.utilisateur().getEmail() + "</p>\n" +
                "                    <p>Date : " + notification.getEvenement().getDate() + "</p>\n" +
                "                    <p>Numéro de Ticket : 123456789</p>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"footer\">\n" +
                "            <p>&copy; 2024 ODK_P4_GP2. Tous droits réservés.</p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(notification.getDest_email());
        helper.setSubject(notification.getSujet());
        helper.setSentDate(notification.getDateEnvoi());
        helper.setText(Content_Qrcode, true);
        helper.addInline("qrcode", qrCodeSource, "image/png");

        byte[] pdfBytes = generatePdfFromHtml.generatePdfFromHtml(Content_Qrcode1);

        // Convert PDF to image
        byte[] imageBytes = generateImageFromPdf.generateImageFromPdf(pdfBytes);

        // Add image attachment
        InputStreamSource imageSource = new ByteArrayResource(imageBytes);
        helper.addAttachment("ticket.png", imageSource);

        javaMailSender.send(message);
        notificationRepo.save(notification);
    }


}

