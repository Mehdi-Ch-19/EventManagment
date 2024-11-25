package net.chiheb.eventmanagment.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import net.chiheb.eventmanagment.Entity.EventParticipant;
import net.chiheb.eventmanagment.Entity.mail.ConfirmationToken;
import net.chiheb.eventmanagment.Repository.ConfirmationTokenRepository;
import net.chiheb.eventmanagment.Repository.EventParticipantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmailService {
    private JavaMailSender javaMailSender;
    private ConfirmationTokenRepository confirmationTokenRepository;
    private EventParticipantRepository eventParticipantRepository;
    private final static Logger logger = LoggerFactory.getLogger(EmailService.class);

    public EmailService(JavaMailSender javaMailSender, ConfirmationTokenRepository confirmationTokenRepository, EventParticipantRepository eventParticipantRepository) {
        this.javaMailSender = javaMailSender;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.eventParticipantRepository = eventParticipantRepository;
    }

    public String saveConfirmationToken(EventParticipant eventParticipant){
        ConfirmationToken confirmationToken = new ConfirmationToken();
        String token = UUID.randomUUID().toString();
        confirmationToken.setCreatedDate(LocalDateTime.now());
        confirmationToken.setExpiresAt(LocalDateTime.now().plusHours(24));
        confirmationToken.setConfirmationToken(token);
        confirmationToken.setEventParticipant(eventParticipant);
        confirmationTokenRepository.save(confirmationToken);
        return token;
    }
    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }

    @Async
    public void sendEmail(String to,String  email) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Confirm your Participation to the event");
            helper.setFrom(System.getenv("email"));
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            logger.error("Failed to send email for: " + email + "\n" + e);
            throw new IllegalArgumentException("Failed to send email for: " + email);
        }
    }

    public ConfirmationToken getToken(String token) {
        return confirmationTokenRepository.findByConfirmationToken(token);
    }

    public EventParticipant getEventParticipantFromToken(String token){
        ConfirmationToken confirmationToken = getToken(token);
        return confirmationToken.getEventParticipant();
    }
    @Transactional
    public boolean confirmToken(String token) {
        ConfirmationToken  confirmToken = getToken(token);

        if (confirmToken == null) {
            throw new IllegalStateException("Token not found!");
        }

        if (confirmToken.getConfirmedAt() != null) {
            throw new IllegalStateException("Email is already confirmed");
        }

        LocalDateTime expiresAt = confirmToken.getExpiresAt();

        if (expiresAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token is already expired!");
        }
        EventParticipant eventParticipant = confirmToken.getEventParticipant();
        eventParticipant.setConfirmad(true);
        setConfirmedAt(token);
        eventParticipantRepository.save(eventParticipant);
        //Returning confirmation message if the token matches

        return true;
    }
}


