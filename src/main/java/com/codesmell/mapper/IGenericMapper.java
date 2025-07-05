package com.codesmell.mapper;

import java.util.Collection;

public interface IGenericMapper<E, D> {

    D toDto(E entity);

    E toEntity(D dto);

    Collection<D> toDtos(Collection<E> entities);

    Collection<E> toEntities(Collection<D> dtos);

}
