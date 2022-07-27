package com.wipro.productservice.repository;

import com.wipro.productservice.model.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, String> {

}
