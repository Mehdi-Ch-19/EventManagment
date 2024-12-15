package net.chiheb.eventmanagment.Dto.mapper;

import net.chiheb.eventmanagment.Dto.EventDto;
import net.chiheb.eventmanagment.Entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "Spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface EventMapper {
    @Mapping(source = "eventStartTime", target = "EventStartTime")
    @Mapping(source = "eventEndTime",target = "EventEndTime")
    EventDto toDto(Event event);
}
