package net.chiheb.eventmanagment.Entity.mail;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.chiheb.eventmanagment.Entity.Event;
import net.chiheb.eventmanagment.Entity.EventParticipant;

import java.time.LocalDateTime;
import java.util.Date;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="token_id")
    private Long tokenId;

    @Column(name="confirmation_token")
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;


    @OneToOne
    private EventParticipant eventParticipant;


}
