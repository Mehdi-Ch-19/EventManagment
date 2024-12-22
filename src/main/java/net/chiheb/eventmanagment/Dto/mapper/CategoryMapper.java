package net.chiheb.eventmanagment.Dto.mapper;

import net.chiheb.eventmanagment.Dto.CategoryDto;
import net.chiheb.eventmanagment.Entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "Spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CategoryMapper {
    CategoryDto todto(Category category);
}
