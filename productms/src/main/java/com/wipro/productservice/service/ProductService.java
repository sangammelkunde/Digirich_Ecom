package com.wipro.productservice.service;

import com.wipro.productservice.model.AuthenticationResponse;
import com.wipro.productservice.model.ProductEntity;

//FD

public interface ProductService {

    /**
     * @param token
     * @param Product
     * @return ProductEntity
     */
    public ProductEntity createProduct(String token, ProductEntity Product);

    /**
     * @param token
     * @param id
     * @return ProductEntity
     */
    public ProductEntity getProductDetail(String token, String id);

    /**
     * @param token
     * @return AuthenticationResponse
     */
    public AuthenticationResponse hasEmployeePermission(String token);

    /**
     * @param id
     * @return boolean
     */
    public boolean deleteProduct(String id);

    /**
     * @param token
     * @return AuthenticationResponse
     */
    AuthenticationResponse hasCustomerPermission(String token);

    /**
     * @param token
     * @return AuthenticationResponse
     */
    public AuthenticationResponse hasPermission(String token);

    /**
     * @param token
     * @param customer
     * @return ProductEntity
     */
    public ProductEntity saveProduct(String token, ProductEntity Product);

    /**
     * @param token
     * @param customer
     * @return ProductEntity
     */
    public ProductEntity updateProduct(String token, ProductEntity Product);

}
