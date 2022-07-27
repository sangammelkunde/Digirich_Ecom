package com.wipro.productservice.repository;

import com.wipro.productservice.model.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, String> {

}
