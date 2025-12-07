package com.codesmell.service;

import com.codesmell.domain.api.PageRequest;
import com.codesmell.domain.api.RPage;
import com.codesmell.domain.api.SortDirection;
import com.codesmell.domain.dto.BaseDto;
import com.codesmell.domain.entity.BaseEntity;
import com.codesmell.mapper.IGenericMapper;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Sort;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public abstract class AGenericService<
        E extends BaseEntity<ID>,
        D extends BaseDto<ID>,
        ID> implements IGenericService<E, D, ID> {

    public PanacheRepositoryBase<E, ID> repository;
    public IGenericMapper<E, D> mapper;

    public AGenericService(PanacheRepositoryBase<E, ID> repository,
                           IGenericMapper<E, D> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<D> get(ID id) {
        E entity = repository.findById(id);
        return Optional.of(mapper.toDto(entity));
    }

    @Transactional
    @Override
    public D save(D dto) {
        E entity = mapper.toEntity(dto);
        entity = repository.getEntityManager().merge(entity);
        return mapper.toDto(entity);
    }

    @Transactional
    @Override
    public Collection<D> saveAll(Collection<D> dtos) {
        Collection<E> entities = mapper.toEntities(dtos);
        Collection<E> entitiesUpdated = entities.stream()
                .map(entity -> repository.getEntityManager().merge(entity))
                .toList();
        return mapper.toDtos(entitiesUpdated);
    }

    @Transactional
    @Override
    public void delete(Collection<ID> ids) {
        ids.forEach(id -> {
            repository.deleteById(id);
        });
    }

    @Override
    public RPage<D> list(PageRequest pageRequest) {
        Sort.Direction direction = Sort.Direction.Descending;
        if (SortDirection.ASC.equals(pageRequest.getSort())) {
            direction = Sort.Direction.Ascending;
        }
        Sort sort = Sort.by(pageRequest.getSortField()).direction(direction);
        PanacheQuery<E> page = repository.findAll(sort).page(pageRequest.getPageNum() - 1, pageRequest.getPageSize());
        List<D> content = page.list().stream().map(e -> mapper.toDto(e)).toList();
        long totalElements = page.count();
        return new RPage<>(content, pageRequest.getPageNum(), pageRequest.getPageSize(), totalElements);
    }
}
