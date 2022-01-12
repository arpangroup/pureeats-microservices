package com.arpangroup.onboarding.mapper;

import com.arpangroup.onboarding.entity.global.CollectionEntity;
import com.arpangroup.onboarding.schema.response.Collection;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CollectionMapper {
    public abstract Collection mapTo(CollectionEntity entity);
}
