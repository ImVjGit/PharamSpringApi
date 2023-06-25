package com.example.pharma.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
//import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PharmaProduct  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    @Column(name="Name",length=10,nullable = false)
    private String productName;

    @Column(name="Description",length=100,nullable = false)
    private String productDescription;

    @Column(name="Category")
    private String productCategory;
    //general or prescribed

    @Column(name="Price",nullable = false)
    private Integer productPrice;

    @Column(name="Quantity",nullable = false)
    private Integer productQuantity;

    //import date from java.sql.Date instead of java.util.Date
    @Column(name="Manufactured Date")
    private Date mgfDate;

    @Column(name="Expiration Date")
    private Date expDate;

}
