package com.arpangroup.onboarding.mapper;

import com.arpangroup.onboarding.entity.global.CityEntity;
import com.arpangroup.onboarding.schema.response.Cities;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityMapper {
    public abstract Cities mapTo(CityEntity entity);
}
