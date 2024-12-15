package net.chiheb.eventmanagment.Dto.mapper;

import net.chiheb.eventmanagment.Dto.ParticipantDto;
import net.chiheb.eventmanagment.Entity.Participant;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "Spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE

)
public interface ParticipantDtoMapper {
    ParticipantDto toParticipantDto(Participant participant);

}
