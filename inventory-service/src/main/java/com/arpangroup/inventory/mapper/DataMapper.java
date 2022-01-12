package com.arpangroup.inventory.mapper;

import java.util.List;

public abstract class DataMapper<D, E> {
    public abstract E toEntity(D dto);
    public abstract D toDto(E entity);
    public abstract List<E> toEntity(List<D> dtoList);
    public abstract List<D> toDto(List<E> entityList);
}
