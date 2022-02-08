package com.arpangroup.mapper;

import com.arpangroup.response.Establishment;
import com.arpangroup.entity.master.EstablishmentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EstablishmentMapper {
    public abstract Establishment mapTo(EstablishmentEntity entity);
}
