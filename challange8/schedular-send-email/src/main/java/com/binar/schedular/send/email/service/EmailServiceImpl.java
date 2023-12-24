package com.binar.schedular.send.email.service;

import com.binar.schedular.send.email.model.Email;
import com.binar.schedular.send.email.model.Users;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
@Log4j2
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserService userService;

    @Value("${spring.mail.username}")
    private String senderEmail;
    private static final String SENDER_NAME = "Binarfood Promotion";

    @Override
    public void sendPromotionFood(Email email) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(new InternetAddress(senderEmail, SENDER_NAME));
            helper.setTo(email.getTo());
            helper.setSubject(email.getSubject());
            helper.setText(getPromotionEmail(email.getUsername()), true); // Menggunakan setText dengan parameter true untuk mendukung HTML
            mailSender.send(message);
        } catch (MessagingException | UnsupportedEncodingException e) {
            log.error(e.getMessage());
        }
    }

    private String getPromotionEmail(String username) {
        String htmlMessage = "<html><body>";
        htmlMessage += "<h2>Halo " + username + ",</h2>";
        htmlMessage += "<p>Jangan lewatkan kesempatan untuk menikmati promo spesial kami!</p>";
        htmlMessage += "<p>Dapatkan diskon 20% untuk setiap pemesanan makan siang di Binarfood hari ini.</p>";
        htmlMessage += "<p>Segera pesan makanan favoritmu sekarang juga!</p>";
        htmlMessage += "<p><b>Salam, Tim Binarfood</b></p>";
        htmlMessage += "</body></html>";
        return htmlMessage;
    }
}
