package com.arpangroup.mapper;

import com.arpangroup.response.Cuisines;
import com.arpangroup.entity.master.CuisineEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CuisineMapper {
    @Mapping(target = "cuisineId", source = "entity.id")
    @Mapping(target = "cuisineName", source = "entity.name")
    public abstract Cuisines mapTo(CuisineEntity entity);
}
