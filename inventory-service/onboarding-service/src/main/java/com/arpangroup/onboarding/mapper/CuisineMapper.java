package com.arpangroup.onboarding.mapper;

import com.arpangroup.onboarding.entity.global.CuisineEntity;
import com.arpangroup.onboarding.schema.response.Cuisines;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CuisineMapper {

    @Mapping(target = "cuisineId", source = "entity.id")
    @Mapping(target = "cuisineName", source = "entity.name")
    public abstract Cuisines mapTo(CuisineEntity entity);
}
