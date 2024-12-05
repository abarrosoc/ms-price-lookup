package org.inditex.price.infrastructure.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.inditex.price.domain.model.Price;

public class PriceRepositoryImplInMemory implements PriceRepository
{
    Map<Long, Price> pricesMap = new HashMap<>();

    @Override
    public Optional<List<Price>> findByBrandIdAndProductId(String brandId,
                                                           String productId)
    {
        return Optional.of(pricesMap.values()
                                    .stream()
                                    .filter(price -> price.getBrandId().equals(brandId)
                                            && price.getProductId().equals(productId))
                                    .collect(Collectors.toList()));
    }

    @Override
    public <S extends Price> S save(S entity)
    {
        Price price = entity.withId(entity.getId());

        pricesMap.put(price.getId(), price);

        return entity;
    }

    @Override
    public <S extends Price> Iterable<S> saveAll(Iterable<S> entities)
    {
        StreamSupport.stream(entities.spliterator(),
                             false)
                     .forEach(this::save);

        return entities;
    }

    @Override
    public Optional<Price> findById(Long id)
    {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long id)
    {
        return false;
    }

    @Override
    public Iterable<Price> findAll()
    {
        return null;
    }

    @Override
    public Iterable<Price> findAllById(Iterable<Long> ids)
    {
        return null;
    }

    @Override
    public long count()
    {
        return 0;
    }

    @Override
    public void deleteById(Long id)
    {

    }

    @Override
    public void delete(Price entity)
    {
        pricesMap.remove(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids)
    {

    }


    @Override
    public void deleteAll(Iterable<? extends Price> entities)
    {

    }

    @Override
    public void deleteAll()
    {
        pricesMap.clear();
    }
}
