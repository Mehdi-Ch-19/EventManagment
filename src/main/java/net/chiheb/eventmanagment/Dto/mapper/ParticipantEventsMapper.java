package net.chiheb.eventmanagment.Dto.mapper;

import net.chiheb.eventmanagment.Dto.ParticipantEvents;
import net.chiheb.eventmanagment.Entity.EventParticipant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "Spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ParticipantEventsMapper {
    @Mapping(source = "purchaseat",target = "purchaseat",dateFormat = "yyyy-MM-dd HH:mm")
    @Mapping(source = "confirmad",target = "isConfirmad")
    ParticipantEvents toParticipantEvents(EventParticipant eventParticipant);
}
