package net.chiheb.eventmanagment.Dto.mapper;

import net.chiheb.eventmanagment.Dto.EventDto;
import net.chiheb.eventmanagment.Dto.EventsOrganizatorDto;
import net.chiheb.eventmanagment.Entity.Event;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "Spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface EventMapper {
    EventMapper mapper = Mappers.getMapper(EventMapper.class);
    @Mapping(source = "eventStartTime", target = "EventStartTime")
    @Mapping(source = "eventEndTime",target = "EventEndTime")
    @Mapping(source = "maxCapacity",target = "MaxCapacity")
    EventDto toDto(Event event);





}
