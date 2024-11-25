package net.chiheb.eventmanagment.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class EventPartcipantPk {

    @Column(name = "event_id")
    private Long eventId;
    @Column(name = "participant_id")
    private Long participantId;
}
