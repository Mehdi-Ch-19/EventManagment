package net.chiheb.eventmanagment.Dto.mapper;

import net.chiheb.eventmanagment.Dto.EventParticipnantsResponce;
import net.chiheb.eventmanagment.Entity.EventParticipant;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface EventPartcipantMapper {
    EventParticipnantsResponce toEventParticipnantsResponce(EventParticipant eventParticipant);

}
