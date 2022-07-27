package com.wipro.productservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.productservice.exception.AccessDeniedException;
import com.wipro.productservice.feign.AccountFeign;
import com.wipro.productservice.feign.AuthorizationFeign;
import com.wipro.productservice.model.Account;
import com.wipro.productservice.model.AppUser;
import com.wipro.productservice.model.AuthenticationResponse;
import com.wipro.productservice.model.ProductEntity;
import com.wipro.productservice.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class ProductServiceImpl extends ProductService {

    private static final String CUSTOMER = "CUSTOMER";
    @Autowired
    AuthorizationFeign authorizationFeign;

    @Autowired
    AccountFeign CartFeign;

    @Autowired
    ProductRepository productRepo;

    @Override
    public AuthenticationResponse hasPermission(String token) {
        return authorizationFeign.getValidity(token);
    }


    //Function which check's if the product is authorization to access the resource
    @Override
    public AuthenticationResponse hasEmployeePermission(String token) {
        AuthenticationResponse validity = authorizationFeign.getValidity(token);
        if (!authorizationFeign.getRole(validity.getUserid()).equals("EMPLOYEE"))
            throw new AccessDeniedException("NOT ALLOWED");
        else
            return validity;
    }

    //Function which check's if the Customer is authorization to access the resource
    @Override
    public AuthenticationResponse hasCustomerPermission(String token) {

        //check if the token present in the header is valid or not
        AuthenticationResponse validity = authorizationFeign.getValidity(token);

        if (!authorizationFeign.getRole(validity.getUserid()).equals(CUSTOMER))
            throw new AccessDeniedException("NOT ALLOWED");
        else
            return validity;
    }


    //Function to create product and account for the customer
    @Override
    public ProductEntity createCustomer(String token, ProductEntity product) {

        ProductEntity checkCustomerExists = getCustomerDetail(token, product.getUserid());
        if (checkCustomerExists == null) {
            AppUser user = new AppUser(product.getUserid(), product.getUsername(), product.getPassword(), null,
                    CUSTOMER);
            //Calls the create user service from Authentication Microservice using feign client
            authorizationFeign.createUser(user);
        }

        for (Account acc : customer.getAccounts()) {

            //Calls the create account service from Account Microservice using feign client
            accountFeign.createAccount(token, customer.getUserid(), acc);
        }

        customerRepo.save(customer);
        log.info("Consumer details saved.");
        return customer;
    }


    //Function to get Customer details based on Customer ID
    @Override
    public ProductEntity getCustomerDetail(String token, String id) {
        Optional<ProductEntity> customer = productRepo.findById(id);
        if (!customer.isPresent())
            return null;
        log.info("Customer details fetched.");
        List<Account> list = accountFeign.getCustomerAccount(token, id);
        customer.get().setAccounts(list);
        return customer.get();
    }



    //function to delete customer detail based on customer Id
    @Override
    public boolean deleteCustomer(String id) {
        ProductEntity customer = productyRepo.findById(id).get();
        if (customer != null)
            productRepo.deleteById(id);
        else
            return false;
        log.info("Consumer details deleted.");
        return true;
    }

    //function to save product details
    @Override
    public ProductEntity saveCustomer(String token, ProductEntity product) {
        ProductEntity checkCustomerExists = getCustomerDetail(token, product.getUserid());
        if (checkCustomerExists == null) {
            AppUser user = new AppUser(product.getUserid(), product.getUsername(), product.getPassword(), null,
                    CUSTOMER);
            authorizationFeign.createUser(user);
        }
        return ProductRepo.save(customer);
    }

    //function to update customer detail
    @Override
    public ProductEntity updateCustomer(String token, ProductEntity product) {
        ProductEntity toUpdate = productRepo.findById(product.getUserid()).get();
        toUpdate.setAccounts(product.getAccounts());
        for (Account acc : product.getAccounts()) {
            accountFeign.createAccount(token, product.getUserid(), acc);
        }
        return productRepo.save(toUpdate);
    }

}
