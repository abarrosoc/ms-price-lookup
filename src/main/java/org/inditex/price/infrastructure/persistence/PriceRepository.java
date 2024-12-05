package org.inditex.price.infrastructure.persistence;

import java.util.List;
import java.util.Optional;

import org.inditex.price.domain.model.Price;
import org.inditex.price.interfaces.rest.dtos.ProductPrice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends CrudRepository<Price, Long>
{

    Optional<List<Price>> findByBrandIdAndProductId(String brandId, String productId);

}
