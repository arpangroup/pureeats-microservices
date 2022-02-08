package com.arpangroup.mapper;

import com.arpangroup.entity.master.CityEntity;
import com.arpangroup.response.Cities;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityMapper {
    public abstract Cities mapTo(CityEntity entity);
}
