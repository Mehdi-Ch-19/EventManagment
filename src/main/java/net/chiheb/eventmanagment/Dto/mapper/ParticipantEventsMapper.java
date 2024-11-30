package net.chiheb.eventmanagment.Dto.mapper;

import net.chiheb.eventmanagment.Dto.ParticipantEvents;
import net.chiheb.eventmanagment.Entity.EventParticipant;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "Spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ParticipantEventsMapper {
    ParticipantEvents toParticipantEvents(EventParticipant eventParticipant);
}
