package com.arpangroup.mapper;

import com.arpangroup.response.Collection;
import com.arpangroup.entity.master.CollectionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CollectionMapper {
    public abstract Collection mapTo(CollectionEntity entity);
}
