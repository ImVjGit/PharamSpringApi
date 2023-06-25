package com.example.pharma.model;

import lombok.Data;

import java.util.Date;

@Data
public class PharmaProductDto {

    private Long productId;
    private String productName;
    private String productDescription;
    private String productCategory;
    //general or prescribied
    private Integer productPrice;
    private Integer productQuantity;
    private Date mgfDate;
    private Date expDate;

}
