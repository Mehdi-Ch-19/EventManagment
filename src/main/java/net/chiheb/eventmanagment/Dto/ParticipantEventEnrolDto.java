package net.chiheb.eventmanagment.Dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParticipantEventEnrolDto {
    private Long participantId;
    private String type;
}
