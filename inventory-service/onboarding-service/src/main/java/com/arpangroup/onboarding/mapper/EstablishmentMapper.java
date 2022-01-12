package com.arpangroup.onboarding.mapper;

import com.arpangroup.onboarding.entity.global.CityEntity;
import com.arpangroup.onboarding.entity.global.EstablishmentEntity;
import com.arpangroup.onboarding.schema.response.Cities;
import com.arpangroup.onboarding.schema.response.Establishment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EstablishmentMapper {
    public abstract Establishment mapTo(EstablishmentEntity entity);
}
