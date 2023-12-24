package com.binar.schedular.send.email.schedular;

import com.binar.schedular.send.email.model.Email;
import com.binar.schedular.send.email.model.Users;
import com.binar.schedular.send.email.service.EmailService;
import com.binar.schedular.send.email.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
@Component
public class PromotionSchedular {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @Scheduled(cron = "0 0 12 * * ?")
    void sendPromotionFood() {
        try {
            List<Users> usersList = userService.getListUser();
            for (Users users : usersList) {
                if (users.getEmailAddress().isEmpty() || users.getEmailAddress().equalsIgnoreCase("")) {
                    log.info("email address tidak ditemukan");
                    System.exit(0);
                }
                if (users.getRole().getRoleName().equalsIgnoreCase("client")){
                    Email email = new Email();
                    email.setTo(users.getEmailAddress());
                    email.setUsername(users.getUsername());
                    email.setSubject("Halo " + users.getUsername() + " Jangan lupa Order Makan Siang !");
                    emailService.sendPromotionFood(email);
                    Thread.sleep(5000); // delay 5 second/sending to avoid detected as spam email
                    log.info("sukses send email");
                }

            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
