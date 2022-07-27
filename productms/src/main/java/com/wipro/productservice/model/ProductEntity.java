package com.wipro.productservice.model;


import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "product_name")
    @NotNull
    private String productName;

    @Column (name = "price")
    @NotNull
    private BigDecimal price;

    @Column (name = "discription")
    private String discription;

    @Column (name = "category")
    @NotNull
    private String category;

    @Column (name = "availability")
    @NotNull
    private int availability;

//    @Id
//    @Column(name = "productid", length = 15,unique=true)
//    @Pattern(regexp = "^[A-Za-z0-9_-]*$")
//    private String userid;
//
//    @Column(name = "productname", length = 20)
//    @NotBlank
//    private String productname;

//    @Column(name = "price")
//    @NotBlank
//    private String price;
//
//    @Column(name = "dateOfBirth")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//    private Date dateOfBirth;
//
//    @Pattern(regexp = "^[A-Z]{5}+[0-9]{4}+[A-Z]{1}$")
//    @Column(name = "pan", length = 10)
//    @NotBlank
//    private String pan;
//
//
//    @Column(name = "address")
//    @NotBlank
//    private String address;


    @Transient
    private List<Account> accounts = new ArrayList<>();

}
